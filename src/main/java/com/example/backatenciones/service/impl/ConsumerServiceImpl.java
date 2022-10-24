package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Atencion;
import com.example.backatenciones.service.ConsumerService;
import com.example.backatenciones.service.AtencionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.AmqpIOException;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConsumerServiceImpl{
    /*@Autowired
    private AtencionService atencionService;

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "${spring.rabbitmq.queue}", durable = "true"),
                    exchange = @Exchange(value = "${spring.rabbitmq.exchange}"),
                    key = "${spring.rabbitmq.routingkey}")
    )
    //@RabbitListener(queues = "${spring.rabbitmq.queue}")
    //@SendTo("amq.rabbitmq.reply-to")
    public Object consumerMessage(String objId) throws AmqpIOException {
        System.out.println("=============== Message ==================");
        System.out.println(objId);
        System.out.println("==========================================");
        UUID atencionId;
        try {
            atencionId = UUID.fromString(objId);
        } catch (Exception e) {
            ObjectMapper obj = new ObjectMapper();
            try {
                return obj.writeValueAsString("Error: El id de la atención no es un UUID válido");
            } catch (JsonProcessingException ex) {
                return null;
            }
        }
        Atencion obj = atencionService.getAtencion(atencionId);
        if (obj == null) {
            return null;
        } else {
            ObjectMapper obj_m = new ObjectMapper();
            try {
                return obj_m.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }*/
}