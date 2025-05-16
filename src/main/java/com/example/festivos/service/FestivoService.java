package com.example.festivos.service;

import com.example.festivos.model.Festivo;
import com.example.festivos.repository.FestivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class FestivoService {

    @Autowired
    private FestivoRepository festivoRepository;

    public String validarFecha(String fechaInput) {
        try {
            LocalDate fecha = LocalDate.parse(fechaInput);
            int anio = fecha.getYear();
            LocalDate pascua = calcularDomingoPascua(anio);

            List<Festivo> festivos = festivoRepository.findAll();
            for (Festivo festivo : festivos) {
                LocalDate festivoCalculado = calcularFechaFestivo(festivo, pascua, anio);
                if (fecha.equals(festivoCalculado)) {
                    return "La fecha " + fecha + " es festivo por " + festivo.getNombre();
                }
            }

            return "La fecha " + fecha + " no es festivo.";

        } catch (DateTimeParseException e) {
            return "La fecha ingresada no es válida.";
        }
    }

    private LocalDate calcularDomingoPascua(int anio) {
        int a = anio % 19;
        int b = anio % 4;
        int c = anio % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + ((2 * b + 4 * c + 6 * d + 5) % 7);
        return LocalDate.of(anio, 3, 15).plusDays(dias + 7);
    }

    private LocalDate calcularFechaFestivo(Festivo festivo, LocalDate pascua, int anio) {
        switch (festivo.getTipo()) {
            case 1:
            case 2:
                if (festivo.getMes() == null || festivo.getDia() == null)
                    throw new IllegalArgumentException("Fecha inválida para festivo tipo 1 o 2: " + festivo.getNombre());

                LocalDate base = LocalDate.of(anio, festivo.getMes(), festivo.getDia());
                return festivo.getTipo() == 1 ? base : moverAlLunes(base);

            case 3:
                if (festivo.getDiasPascua() == null)
                    throw new IllegalArgumentException("Faltan días desde Pascua para festivo tipo 3: " + festivo.getNombre());

                return pascua.plusDays(festivo.getDiasPascua());

            case 4:
                if (festivo.getDiasPascua() == null)
                    throw new IllegalArgumentException("Faltan días desde Pascua para festivo tipo 4: " + festivo.getNombre());

                return moverAlLunes(pascua.plusDays(festivo.getDiasPascua()));

            default:
                throw new IllegalArgumentException("Tipo desconocido: " + festivo.getTipo());
        }
    }

    private LocalDate moverAlLunes(LocalDate fecha) {
        while (fecha.getDayOfWeek() != DayOfWeek.MONDAY) {
            fecha = fecha.plusDays(1);
        }
        return fecha;
    }
}