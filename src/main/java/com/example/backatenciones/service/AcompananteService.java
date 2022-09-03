package com.example.backatenciones.service;

import com.example.backatenciones.entity.Acompanante;

import java.util.List;
import java.util.UUID;

public interface AcompananteService {
    List<Acompanante> findAcompananteAll();
    Acompanante getAcompanante(UUID id);
    Acompanante createAcompanante(Acompanante acompanante);
    Acompanante updateAcompanante(Acompanante acompanante);
    String deleteAcompanante(UUID id);
    Acompanante getByDni(Long dni);
    Acompanante getByDocExtranjeria(Long docnum);
}
