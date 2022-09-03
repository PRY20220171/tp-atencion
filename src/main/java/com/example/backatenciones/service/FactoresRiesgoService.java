package com.example.backatenciones.service;

import com.example.backatenciones.entity.FactoresRiesgo;

import java.util.List;
import java.util.UUID;

public interface FactoresRiesgoService {
    List<FactoresRiesgo> findFactoresRiesgoAll();
    FactoresRiesgo getFactoresRiesgo(UUID id);
    FactoresRiesgo createFactoresRiesgo(FactoresRiesgo factoresRiesgo);
    FactoresRiesgo updateFactoresRiesgo(FactoresRiesgo factoresRiesgo);
    String deleteFactoresRiesgo(UUID id);
}
