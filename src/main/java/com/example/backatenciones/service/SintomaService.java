package com.example.backatenciones.service;

import com.example.backatenciones.entity.Sintoma;

import java.util.List;
import java.util.UUID;

public interface SintomaService {
    List<Sintoma> findSintomaAll();
    Sintoma getSintoma(UUID id);
    Sintoma createSintoma(Sintoma sintoma);
    Sintoma updateSintoma(Sintoma sintoma);
    String deleteSintoma(UUID id);
}
