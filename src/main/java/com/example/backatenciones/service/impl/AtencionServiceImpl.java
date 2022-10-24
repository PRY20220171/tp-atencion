package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Atencion;
import com.example.backatenciones.entity.Paciente;
import com.example.backatenciones.repository.AtencionRepository;
import com.example.backatenciones.service.AtencionService;
import com.example.backatenciones.service.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtencionServiceImpl implements AtencionService {
    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private ProducerService producerService;

    @Override
    public List<Atencion> findAtencionAll() {
        return (List<Atencion>) atencionRepository.findAll();
    }

    @Override
    public Atencion getAtencion(UUID id) {
        Atencion atencionDB = atencionRepository.findById(id).orElse(null);

        if (atencionDB == null){
            return null;
        }

        String pacienteDB = producerService.sendMsg(atencionDB.getIdpaciente().toString());

        if (pacienteDB == null){
            return null;
        }

        System.out.println(pacienteDB);

        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            Paciente paciente = mapper.readValue(pacienteDB, Paciente.class);
            atencionDB.setPaciente(paciente);
        } catch (Exception e){
            System.out.println(e.toString());
            return null;
        }

        return atencionDB;

    }

    @Override
    public Atencion createAtencion(Atencion atencion) {
        //Aquí irian las validaciones al crear el atencion de ser necesario

        //System.out.println(producerService.sendMsg(atencion.getIdpaciente().toString()));

        String pacienteDB = producerService.sendMsg(atencion.getIdpaciente().toString());

        System.out.println(pacienteDB);

        if (pacienteDB == null){
            return null;
        }

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
        atencionDB.setIdcentromedico(atencion.getIdcentromedico());
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
