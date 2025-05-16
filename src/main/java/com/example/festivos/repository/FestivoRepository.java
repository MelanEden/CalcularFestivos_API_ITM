package com.example.festivos.repository;

import com.example.festivos.model.Festivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivoRepository extends JpaRepository<Festivo, Long> {
}