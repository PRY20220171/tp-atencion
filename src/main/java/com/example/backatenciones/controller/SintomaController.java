package com.example.backatenciones.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.backatenciones.entity.Sintoma;
import com.example.backatenciones.service.SintomaService;
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
@RequestMapping("/sintomas")
public class SintomaController {
    @Autowired
    private SintomaService sintomaService;

    @ApiOperation(value = "Obtener un sintoma por su ID", notes = "Provee un mecanismo para obtener todos los datos de un sintoma por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Sintoma.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorMessage.class)
    })
    @GetMapping
    public ResponseEntity<List<Sintoma>> listSintoma(@RequestParam(name = "idsintoma", required = false) String idSintoma) {
        List<Sintoma> sintomas = new ArrayList<>();
        if (null == idSintoma) {
            sintomas = sintomaService.findSintomaAll();
            if (sintomas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            sintomas = Collections.singletonList(sintomaService.getSintoma(UUID.fromString(idSintoma)));
        }
        return ResponseEntity.ok(sintomas);
    }

    @PostMapping
    public ResponseEntity<Sintoma> createSintoma(@Valid @RequestBody Sintoma sintoma, BindingResult result) {
        sintoma.setId(Uuids.timeBased());
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Sintoma sintomacreate = sintomaService.createSintoma(sintoma);
        return ResponseEntity.status(HttpStatus.CREATED).body(sintomacreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sintoma> updateSintoma(@PathVariable("id") String id, @RequestBody Sintoma sintoma) {
        sintoma.setId(UUID.fromString(id));
        Sintoma sintomaDB = sintomaService.updateSintoma(sintoma);
        if (sintomaDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sintomaDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSintoma(@PathVariable("id") String id) {
        String sintomaDelete = sintomaService.deleteSintoma(UUID.fromString(id));
        if (sintomaDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sintomaDelete);
    }
/*
    @Autowired
    ProducerService rabbitMQSender;

    @GetMapping(value = "/test")
    public String producer() {
        rabbitMQSender.sendMsg(new Sintoma());
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

 */


}
