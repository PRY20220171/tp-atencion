package com.example.backatenciones.service;

import com.example.backatenciones.entity.CentroMedico;

import java.util.List;
import java.util.UUID;

public interface CentroMedicoService {
    List<CentroMedico> findCentroMedicoAll();
    CentroMedico getCentroMedico(UUID id);
    CentroMedico createCentroMedico(CentroMedico centroMedico);
    CentroMedico updateCentroMedico(CentroMedico centroMedico);
    String deleteCentroMedico(UUID id);
    List<CentroMedico> findAllByNombre(String nombre);
}
