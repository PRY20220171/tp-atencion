package com.example.backatenciones.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.backatenciones.entity.CentroMedico;
import com.example.backatenciones.service.CentroMedicoService;
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
@RequestMapping("/centrosMedicos")
public class CentroMedicoController {
    @Autowired
    private CentroMedicoService centroMedicoService;

    @ApiOperation(value="Obtener un producto por su ID", notes="Provee un mecanismo para obtener todos los datos de un centro medico por su ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message="OK", response=CentroMedico.class),
            @ApiResponse(code=404, message="Not Found", response= ErrorMessage.class),
            @ApiResponse(code=500, message="Internal Server Error", response=ErrorMessage.class)
    })
    @GetMapping
    public ResponseEntity<List<CentroMedico>> listCentroMedico(@RequestParam(name="idcentromedic",required = false) String idcentromedic){
        List<CentroMedico> centrosMedicos=new ArrayList<>();
        if(null==idcentromedic){
            centrosMedicos=centroMedicoService.findCentroMedicoAll();
            if(centrosMedicos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        else{
            centrosMedicos = Collections.singletonList(centroMedicoService.getCentroMedico(UUID.fromString(idcentromedic)));
        }
        return ResponseEntity.ok(centrosMedicos);
    }

    @PostMapping
    public ResponseEntity<CentroMedico> createCentroMedico(@Valid @RequestBody CentroMedico centroMedico, BindingResult result){
        centroMedico.setId(Uuids.timeBased());
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        CentroMedico centroMedicocreate = centroMedicoService.createCentroMedico(centroMedico);
        return ResponseEntity.status(HttpStatus.CREATED).body(centroMedicocreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentroMedico> updateCentroMedico(@PathVariable("id") String id, @RequestBody CentroMedico centroMedico){
        centroMedico.setId(UUID.fromString(id));
        CentroMedico centroMedicoDB=centroMedicoService.updateCentroMedico(centroMedico);
        if(centroMedicoDB==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(centroMedicoDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCentroMedico(@PathVariable("id") String id){
        String centroMedicoDelete=centroMedicoService.deleteCentroMedico(UUID.fromString(id));
        if(centroMedicoDelete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(centroMedicoDelete);
    }
/*
    @Autowired
    ProducerService rabbitMQSender;

    @GetMapping(value = "/test")
    public String producer() {
        rabbitMQSender.sendMsg(new CentroMedico());
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

 */



}
