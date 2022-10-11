package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Atencion;
import com.example.backatenciones.service.ProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private DirectExchange exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Atencion sendMsg(String idAtencion) {
        try {
            Object response = amqpTemplate.convertSendAndReceive(exchange.getName(), routingkey, idAtencion);
            if (response != null) {
                return objectMapper.readValue(response.toString(), Atencion.class);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}

