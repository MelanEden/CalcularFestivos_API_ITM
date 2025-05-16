package com.example.festivos.controller;

import com.example.festivos.service.FestivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/festivos")
public class FestivoController {

    @Autowired
    private FestivoService festivoService;

    @GetMapping("/validar")
    public ResponseEntity<String> validar(@RequestParam String fecha) {
        return ResponseEntity.ok(festivoService.validarFecha(fecha));
    }
}