package com.example.backatenciones.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.backatenciones.entity.Atencion;
import com.example.backatenciones.service.AtencionService;
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
@RequestMapping("/atenciones")
public class AtencionController {
    @Autowired
    private AtencionService atencionService;

    @ApiOperation(value = "Obtener un producto por su ID", notes = "Provee un mecanismo para obtener todos los datos de una atencion por su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Atencion.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorMessage.class)
    })
    @GetMapping
    public ResponseEntity<List<Atencion>> listAtencion(@RequestParam(name = "idatencion", required = false) String idAtencion) {
        List<Atencion> atenciones = new ArrayList<>();
        if (null == idAtencion) {
            atenciones = atencionService.findAtencionAll();
            if (atenciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        } else {
            atenciones = Collections.singletonList(atencionService.getAtencion(UUID.fromString(idAtencion)));
        }
        return ResponseEntity.ok(atenciones);
    }

    @PostMapping
    public ResponseEntity<Atencion> createAtencion(@Valid @RequestBody Atencion atencion, BindingResult result) {
        atencion.setId(Uuids.timeBased());
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Atencion atencioncreate = atencionService.createAtencion(atencion);
        return ResponseEntity.status(HttpStatus.CREATED).body(atencioncreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atencion> updateAtencion(@PathVariable("id") String id, @RequestBody Atencion atencion) {
        atencion.setId(UUID.fromString(id));
        Atencion atencionDB = atencionService.updateAtencion(atencion);
        if (atencionDB == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atencionDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAtencion(@PathVariable("id") String id) {
        String atencionDelete = atencionService.deleteAtencion(UUID.fromString(id));
        if (atencionDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atencionDelete);
    }

    /*@Autowired
    ProducerService rabbitMQSender;

    @GetMapping(value = "/test/{id}")
    public ResponseEntity<Atencion> producer(@PathVariable("id") String id) {
        Atencion atencion = rabbitMQSender.sendMsg(id);
        if (atencion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atencion);
    }*/

}