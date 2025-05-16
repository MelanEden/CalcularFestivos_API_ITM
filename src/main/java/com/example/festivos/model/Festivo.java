package com.example.festivos.model;

import jakarta.persistence.*;

@Entity
public class Festivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dia;
    private Integer mes;
    private String nombre;
    private Integer tipo;
    private Integer diasPascua;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getDia() { return dia; }
    public void setDia(Integer dia) { this.dia = dia; }
    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getTipo() { return tipo; }
    public void setTipo(Integer tipo) { this.tipo = tipo; }
    public Integer getDiasPascua() { return diasPascua; }
    public void setDiasPascua(Integer diasPascua) { this.diasPascua = diasPascua; }
}