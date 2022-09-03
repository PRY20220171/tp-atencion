package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.FactoresRiesgo;
import com.example.backatenciones.repository.FactoresRiesgoRepository;
import com.example.backatenciones.service.FactoresRiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FactoresRiesgoServiceImpl implements FactoresRiesgoService {
    @Autowired
    private FactoresRiesgoRepository factoresriesgoRepository;

    @Override
    public List<FactoresRiesgo> findFactoresRiesgoAll() {
        return (List<FactoresRiesgo>) factoresriesgoRepository.findAll();
    }

    @Override
    public FactoresRiesgo getFactoresRiesgo(UUID id) {
        return factoresriesgoRepository.findById(id).orElse(null);
    }

    @Override
    public FactoresRiesgo createFactoresRiesgo(FactoresRiesgo factoresriesgo) {
        //Aquí irian las validaciones al crear el factoresriesgo de ser necesario
        return factoresriesgoRepository.save(factoresriesgo);
    }

    @Override
    public FactoresRiesgo updateFactoresRiesgo(FactoresRiesgo factoresriesgo) {
        FactoresRiesgo factoresriesgoDB = this.getFactoresRiesgo(factoresriesgo.getId());
        if (factoresriesgoDB == null) {
            return null;
        }
        //Actualizamos los valores del factoresriesgo:
        factoresriesgoDB.setCuidador(factoresriesgo.getCuidador());
        factoresriesgoDB.setApetito(factoresriesgo.getApetito());
        factoresriesgoDB.setSed(factoresriesgo.getSed());
        factoresriesgoDB.setSueno(factoresriesgo.getSueno());
        factoresriesgoDB.setOrina(factoresriesgo.getOrina());
        factoresriesgoDB.setDeposiciones(factoresriesgo.getDeposiciones());
        factoresriesgoDB.setEstadoanimo(factoresriesgo.getEstadoanimo());
        factoresriesgoDB.setFecultregla(factoresriesgo.getFecultregla());
        return factoresriesgoRepository.save(factoresriesgo);
    }

    @Override
    public String deleteFactoresRiesgo(UUID id) {
        FactoresRiesgo factoresriesgoDB = this.getFactoresRiesgo(id);
        if (factoresriesgoDB == null) {
            return null;
        }
        try{
            factoresriesgoRepository.delete(factoresriesgoDB);
        }catch (Exception e){
            return "ERROR INTERNO";
        }
        return "ELIMINADO CON EXITO";
    }

}
