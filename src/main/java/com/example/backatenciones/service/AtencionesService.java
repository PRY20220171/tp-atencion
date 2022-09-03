package com.example.backatenciones.service;

import com.example.backatenciones.entity.Atencion;

import java.util.List;
import java.util.UUID;

public interface AtencionService {
    List<Atencion> findAtencionAll();
    Atencion getAtencion(UUID id);
    Atencion createAtencion(Atencion atencion);
    Atencion updateAtencion(Atencion atencion);
    String deleteAtencion(UUID id);
}
