package com.example.backatenciones.service.impl;

import com.example.backatenciones.entity.Atenciones;
import com.example.backatenciones.service.ProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProducerServiceImpl implements ProducerService {
     @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMsg(Atencion object) {
        amqpTemplate.convertSendAndReceive("salud.atenciones.exchange","salud.atenciones.routingkey",object);
    }
}