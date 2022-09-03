package com.example.backatenciones.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.backatenciones.entity.FactoresRiesgo;
import com.example.backatenciones.service.FactoresRiesgoService;
import com.example.backatenciones.service.ProducerService;
import com.example.backatenciones.util.ErrorMessage;
import com.example.backatenciones.util.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/factoresriesgos")
public class FactoresRiesgoController {
    @Autowired
    private FactoresRiesgoService factoresriesgoService;

    @ApiOperation(value="Obtener un producto por su ID", notes="Provee un mecanismo para obtener todos los factores de riesgo por su ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK", response=FactoresRiesgo.class),
            @ApiResponse(code=404, message="Not Found", response= ErrorMessage.class),
            @ApiResponse(code=500, message="Internal Server Error", response=ErrorMessage.class)
    })
    @GetMapping
    public ResponseEntity<List<FactoresRiesgo>> listFactoresRiesgo(@RequestParam(name="idfacriesgo",required = false) String idFacriesgo){
        List<FactoresRiesgo> factoresriesgos=new ArrayList<>();
        if(null==idFacriesgo){
            factoresriesgos=factoresriesgoService.findFactoresRiesgoAll();
            if(factoresriesgos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            factoresriesgos = Collections.singletonList(factoresriesgoService.getFactoresRiesgo(UUID.fromString(idFacriesgo)));
        }
        return ResponseEntity.ok(factoresriesgos);
    }

    @PostMapping
    public ResponseEntity<FactoresRiesgo> createFactoresRiesgo(@Valid @RequestBody FactoresRiesgo factoresriesgo, BindingResult result){
        factoresriesgo.setId(Uuids.timeBased());
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        FactoresRiesgo factoresriesgocreate = factoresriesgoService.createFactoresRiesgo(factoresriesgo);
        return ResponseEntity.status(HttpStatus.CREATED).body(factoresriesgocreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactoresRiesgo> updateFactoresRiesgo(@PathVariable("id") String id, @RequestBody FactoresRiesgo factoresriesgo){
        factoresriesgo.setId(UUID.fromString(id));
        FactoresRiesgo factoresriesgoDB=factoresriesgoService.updateFactoresRiesgo(factoresriesgo);
        if(factoresriesgoDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factoresriesgoDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFactoresRiesgo(@PathVariable("id") String id){
        String factoresriesgoDelete=factoresriesgoService.deleteFactoresRiesgo(UUID.fromString(id));
        if(factoresriesgoDelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factoresriesgoDelete);
    }

    @Autowired
    ProducerService rabbitMQSender;

    @GetMapping(value = "/test")
    public String producer() {
        rabbitMQSender.sendMsg(new FactoresRiesgo());
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }



}
