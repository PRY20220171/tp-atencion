package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Acompanante;
import com.example.backatenciones.repository.AcompananteRepository;
import com.example.backatenciones.service.AcompananteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AcompananteServiceImpl implements AcompananteService {
    @Autowired
    private AcompananteRepository acompananteRepository;

    @Override
    public List<Acompanante> findAcompananteAll() {
        return (List<Acompanante>) acompananteRepository.findAll();
    }

    @Override
    public Acompanante getAcompanante(UUID id) {
        return acompananteRepository.findById(id).orElse(null);
    }

    @Override
    public Acompanante createAcompanante(Acompanante acompanante) {
        //Aquí irian las validaciones al crear el acompanante de ser necesario
        return acompananteRepository.save(acompanante);
    }

    @Override
    public Acompanante updateAcompanante(Acompanante acompanante) {
        Acompanante acompananteDB = this.getAcompanante(acompanante.getId());
        if (acompananteDB == null) {
            return null;
        }
        //Actualizamos los valores del acompanante:
        acompananteDB.setNombres(acompanante.getNombres());
        acompananteDB.setApellidos(acompanante.getApellidos());
        acompananteDB.setDocnum(acompanante.getDocnum());
        acompananteDB.setDoctipo(acompanante.getDoctipo());
        acompananteDB.setFecnac(acompanante.getFecnac());
        acompananteDB.setTelefono(acompanante.getTelefono());
        acompananteDB.setGradoinstruccion(acompanante.getGradoinstruccion());
        acompananteDB.setOcupacion(acompanante.getOcupacion());
        acompananteDB.setParentezco(acompanante.getParentezco());
        return acompananteRepository.save(acompanante);
    }

    @Override
    public String deleteAcompanante(UUID id) {
        Acompanante acompananteDB = this.getAcompanante(id);
        if (acompananteDB == null) {
            return null;
        }
        try{
            acompananteRepository.delete(acompananteDB);
        }catch (Exception e){
            return "ERROR INTERNO";
        }
        return "ELIMINADO CON EXITO";
    }

}
