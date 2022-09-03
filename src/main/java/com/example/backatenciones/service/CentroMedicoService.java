package com.example.backatenciones.service;

import com.example.backatenciones.entity.CentroMedico;

import java.util.List;
import java.util.UUID;

public interface CentroMedicoService {
    List<CentroMedico> findCentromedicoAll();
    CentroMedico getCentromedico(UUID id);
    CentroMedico createCentromedico(CentroMedico centroMedico);
    CentroMedico updateCentromedico(CentroMedico centroMedico);
    String deleteCentromedico(UUID id);
    CentroMedico getByNombre(Long nombre);
}
