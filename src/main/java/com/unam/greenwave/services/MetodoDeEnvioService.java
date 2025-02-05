package com.unam.greenwave.services;

import com.unam.greenwave.model.MetodoEnvio.*;
import com.unam.greenwave.repository.MetodoDeEnvioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
public class MetodoDeEnvioService {

    @Autowired
    private MetodoDeEnvioRepository metodoDeEnvioRepository;


    public RespuestaMetodoDeEnvioDto registrarMetodoDeEnvio(@RequestBody @Valid MetodoDeEnvioDto datos) {
        MetodoDeEnvio metodoDeEnvio = MetodoDeEnvio.builder()
                .nombre(datos.nombre())
                .tipoEnvio(datos.tipo())
                .tiempoEstimado(datos.tiempoEstimado())
                .costo(datos.costo())
                .build();

        RespuestaMetodoDeEnvioDto respuestaMetodoDeEnvioDto = new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getNombre(),
                metodoDeEnvio.getTipoEnvio(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getCosto()
        );

        metodoDeEnvioRepository.save(metodoDeEnvio);

        return respuestaMetodoDeEnvioDto;
    }


    public Page<MetodoDeEnvio> listarMetodosDeEnvio(Pageable paginacion) {
        return metodoDeEnvioRepository.findByActivoTrue(paginacion);
    }

    @Transactional
    public RespuestaMetodoDeEnvioDto actualizarMetodoDeEnvio(Long id, ActualizarMetodoDeEnvioDto datos){
        MetodoDeEnvio metodoDeEnvio = metodoDeEnvioRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        if(datos.nombre()!=null) metodoDeEnvio.setNombre(datos.nombre());
        if(datos.tipoEnvio()!=null) metodoDeEnvio.setTipoEnvio(datos.tipoEnvio());
        if(datos.tiempoEstimado()!=null) metodoDeEnvio.setTiempoEstimado(datos.tiempoEstimado());
        if(datos.costo()!=null) metodoDeEnvio.setCosto(datos.costo());


        return new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getNombre(),
                metodoDeEnvio.getTipoEnvio(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getCosto()
        );
    }

    @Transactional(readOnly = true)
    public RespuestaMetodoDeEnvioDto buscarMetodoDeEnvio(Long id){
        MetodoDeEnvio metodoDeEnvio = metodoDeEnvioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        return new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getNombre(),
                metodoDeEnvio.getTipoEnvio(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getCosto()
        );
    }

    public void eliminarMetodoDeEnvio(Long id){
        MetodoDeEnvio metodoDeEnvio = metodoDeEnvioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        metodoDeEnvio.desactivarMetodoDeEnvio();
    }


}
