package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Atencion;
import com.example.backatenciones.repository.AtencionRepository;
import com.example.backatenciones.service.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtencionServiceImpl implements AtencionService {
    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public List<Atencion> findAtencionAll() {
        return (List<Atencion>) atencionRepository.findAll();
    }

    @Override
    public Atencion getAtencion(UUID id) {
        return atencionRepository.findById(id).orElse(null);
    }

    @Override
    public Atencion createAtencion(Atencion atencion) {
        //Aquí irian las validaciones al crear el atencion de ser necesario
        return atencionRepository.save(atencion);
    }

    @Override
    public Atencion updateAtencion(Atencion atencion) {
        Atencion atencionDB = this.getAtencion(atencion.getId());
        if (atencionDB == null) {
            return null;
        }
        //Actualizamos los valores del atencion:
        atencionDB.setIdpaciente(atencion.getIdpaciente());
        atencionDB.setIdusuarioregistro(atencion.getIdusuarioregistro());
        atencionDB.setFecharegistro(atencion.getFecharegistro());
        atencionDB.setMotivoconsulta(atencion.getMotivoconsulta());
        atencionDB.setObservaciones(atencion.getObservaciones());
        atencionDB.setTiempoenfermedad(atencion.getTiempoenfermedad());
        atencionDB.setFormainicio(atencion.getFormainicio());
        atencionDB.setCurso(atencion.getCurso());
        atencionDB.setIdacompanante(atencion.getIdacompanante());
        atencionDB.setIdfacriesgo(atencion.getIdfacriesgo());
        atencionDB.setIdcentromedic(atencion.getIdcentromedic());
        return atencionRepository.save(atencion);
    }

    @Override
    public String deleteAtencion(UUID id) {
        Atencion atencionDB = this.getAtencion(id);
        if (atencionDB == null) {
            return null;
        }
        try {
            atencionRepository.delete(atencionDB);
        } catch (Exception e) {
            return "ERROR INTERNO";
        }
        return "ELIMINADO CON EXITO";
    }

}
