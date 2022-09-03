package com.example.backatenciones.repository;

import com.example.backatenciones.entity.FactoresRiesgo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//import org.springframework.data.cassandra.repository.Query;
//import org.springframework.data.repository.Repository;
@Repository
public interface FactoresRiesgoRepository extends CrudRepository<FactoresRiesgo, UUID> {
    //FactoresRiesgo findOneById(String id);
    //FactoresRiesgo findAllByDoctipoAndDocnum(String doctipo, String docnum);
    //@Query("SELECT * from pizza_orders WHERE orderdate = ?0 and zoneid = ?1 ALLOW FILTERING")
    //Order findOrderByOrderDateAndZoneId(LocalDate orderDate, ZoneId zoneId);
}
