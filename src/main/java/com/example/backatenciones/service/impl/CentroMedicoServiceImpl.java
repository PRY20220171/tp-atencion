package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.CentroMedico;
import com.example.backatenciones.repository.CentroMedicoRepository;
import com.example.backatenciones.service.CentroMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CentroMedicoServiceImpl implements CentroMedicoService {
    @Autowired
    private CentroMedicoRepository centromedicoRepository;

    @Override
    public List<CentroMedico> findCentroMedicoAll() {
        return (List<CentroMedico>) centromedicoRepository.findAll();
    }

    @Override
    public CentroMedico getCentroMedico(UUID id) {
        return centromedicoRepository.findById(id).orElse(null);
    }

    @Override
    public CentroMedico createCentroMedico(CentroMedico centromedico) {
        //Aquí irian las validaciones al crear el centromedico de ser necesario
        return centromedicoRepository.save(centromedico);
    }

    @Override
    public CentroMedico updateCentroMedico(CentroMedico centromedico) {
        CentroMedico centromedicoDB = this.getCentroMedico(centromedico.getId());
        if (centromedicoDB == null) {
            return null;
        }
        //Actualizamos los valores del centromedico:
        centromedicoDB.setIdubicacion(centromedico.getIdubicacion());
        centromedicoDB.setNombre(centromedico.getNombre());
        centromedicoDB.setSector(centromedico.getSector());
        return centromedicoRepository.save(centromedico);
    }

    @Override
    public String deleteCentroMedico(UUID id) {
        CentroMedico centromedicoDB = this.getCentroMedico(id);
        if (centromedicoDB == null) {
            return null;
        }
        try{
            centromedicoRepository.delete(centromedicoDB);
        }catch (Exception e){
            return "ERROR INTERNO";
        }
        return "ELIMINADO CON EXITO";
    }

}
