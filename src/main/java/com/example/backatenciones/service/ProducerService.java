package com.example.backatenciones.service;

import com.example.backatenciones.entity.Atencion;

public interface ProducerService {
    //Object sendMsg(Long proId) throws Exception;
    Atencion sendMsg(String idAtencion);
}
