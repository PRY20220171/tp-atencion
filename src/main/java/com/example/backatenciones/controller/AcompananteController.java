package com.example.backatenciones.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.backatenciones.entity.Acompanante;
import com.example.backatenciones.service.AcompananteService;
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
@RequestMapping("/acompanantes")
public class AcompananteController {
    @Autowired
    private AcompananteService acompananteService;

    @ApiOperation(value="Obtener un producto por su ID", notes="Provee un mecanismo para obtener todos los datos de un acompanante por su ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK", response=Acompanante.class),
            @ApiResponse(code=404, message="Not Found", response= ErrorMessage.class),
            @ApiResponse(code=500, message="Internal Server Error", response=ErrorMessage.class)
    })
    @GetMapping
    public ResponseEntity<List<Acompanante>> listAcompanante(@RequestParam(name="idacompanante",required = false) String idAcompanante){
        List<Acompanante> acompanantes=new ArrayList<>();
        if(null==idAcompanante){
            acompanantes=acompananteService.findAcompananteAll();
            if(acompanantes.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            acompanantes = Collections.singletonList(acompananteService.getAcompanante(UUID.fromString(idAcompanante)));
        }
        return ResponseEntity.ok(acompanantes);
    }

    @PostMapping
    public ResponseEntity<Acompanante> createAcompanante(@Valid @RequestBody Acompanante acompanante, BindingResult result){
        acompanante.setId(Uuids.timeBased());
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Acompanante acompanantecreate = acompananteService.createAcompanante(acompanante);
        return ResponseEntity.status(HttpStatus.CREATED).body(acompanantecreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acompanante> updateAcompanante(@PathVariable("id") String id, @RequestBody Acompanante acompanante){
        acompanante.setId(UUID.fromString(id));
        Acompanante acompananteDB=acompananteService.updateAcompanante(acompanante);
        if(acompananteDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acompananteDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAcompanante(@PathVariable("id") String id){
        String acompananteDelete=acompananteService.deleteAcompanante(UUID.fromString(id));
        if(acompananteDelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(acompananteDelete);
    }
/*
    @Autowired
    ProducerService rabbitMQSender;

    @GetMapping(value = "/test")
    public String producer() {
        rabbitMQSender.sendMsg(new Acompanante());
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

 */



}
