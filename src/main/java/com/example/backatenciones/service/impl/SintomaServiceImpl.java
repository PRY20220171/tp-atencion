package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Sintoma;
import com.example.backatenciones.repository.SintomaRepository;
import com.example.backatenciones.service.SintomaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SintomaServiceImpl implements SintomaService {
    @Autowired
    private SintomaRepository sintomaRepository;

    @Override
    public List<Sintoma> findSintomaAll() {
        return (List<Sintoma>) sintomaRepository.findAll();
    }

    @Override
    public Sintoma getSintoma(UUID id) {
        return sintomaRepository.findById(id).orElse(null);
    }

    @Override
    public Sintoma createSintoma(Sintoma sintoma) {
        //Aquí irian las validaciones al crear el sintoma de ser necesario
        return sintomaRepository.save(sintoma);
    }

    @Override
    public Sintoma updateSintoma(Sintoma sintoma) {
        Sintoma sintomaDB = this.getSintoma(sintoma.getId());
        if (sintomaDB == null) {
            return null;
        }
        //Actualizamos los valores del sintoma:
        sintomaDB.setIdatencion(sintoma.getIdatencion());
        sintomaDB.setSignossintomas(sintoma.getSignossintomas());
        return sintomaRepository.save(sintoma);
    }

    @Override
    public String deleteSintoma(UUID id) {
        Sintoma sintomaDB = this.getSintoma(id);
        if (sintomaDB == null) {
            return null;
        }
        try {
            sintomaRepository.delete(sintomaDB);
        } catch (Exception e) {
            return "ERROR INTERNO";
        }
        return "ELIMINADO CON EXITO";
    }

}
