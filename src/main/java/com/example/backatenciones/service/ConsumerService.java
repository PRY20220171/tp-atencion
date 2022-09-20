package com.example.backatenciones.service;

import com.example.backatenciones.entity.Atencion;

import java.util.UUID;

public interface ConsumerService {
    Object consumerMessage(String objId) throws Exception;
}
