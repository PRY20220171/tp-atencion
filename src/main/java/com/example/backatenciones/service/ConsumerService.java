package com.example.backatenciones.service;

import com.example.backatenciones.entity.Atencion;

import java.util.UUID;

public interface ConsumerService {
    Object consumerMessage(UUID proId) throws Exception;
}
