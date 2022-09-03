package com.example.backatenciones.repository;

import com.example.backatenciones.entity.CentroMedico;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

//import org.springframework.data.cassandra.repository.Query;
//import org.springframework.data.repository.Repository;
@Repository
public interface CentroMedicoRepository extends CrudRepository<CentroMedico, UUID> {
    //Centromedico findOneById(String id);
    //CentroMedico findAllByDoctipoAndDocnum(String doctipo, String docnum);
    List<CentroMedico> findAllByNombre(String nombre);
    //@Query("SELECT * from pizza_orders WHERE orderdate = ?0 and zoneid = ?1 ALLOW FILTERING")
    //Order findOrderByOrderDateAndZoneId(LocalDate orderDate, ZoneId zoneId);
}
