package com.unam.greenwave.services;

import com.unam.greenwave.model.MetodoEnvio.ActualizarMetodoDeEnvioDto;
import com.unam.greenwave.model.MetodoEnvio.MetodoDeEnvio;
import com.unam.greenwave.model.MetodoEnvio.MetodoDeEnvioDto;
import com.unam.greenwave.model.MetodoEnvio.RespuestaMetodoDeEnvioDto;
import com.unam.greenwave.repository.MetodoDeEnvioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class MetodoDeEnvioService {

    @Autowired
    private MetodoDeEnvioRepository metodoDeEnvioRepository;


    public RespuestaMetodoDeEnvioDto registrarMetodoDeEnvio(@RequestBody @Valid MetodoDeEnvioDto datos) {
        MetodoDeEnvio metodoDeEnvio = MetodoDeEnvio.builder()
                .tipo(datos.tipo())
                .tiempoEstimado(datos.tiempoEstimado())
                .tarifaBase(datos.tarifaBase())
                .costoPorKm(datos.costoPorKm())
                .build();

        RespuestaMetodoDeEnvioDto respuestaMetodoDeEnvioDto = new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getTipo(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getTarifaBase(),
                metodoDeEnvio.getCostoPorKm()
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

        if(datos.tipo()!=null) metodoDeEnvio.setTipo(datos.tipo());
        if(datos.tiempoEstimado()!=null) metodoDeEnvio.setTiempoEstimado(datos.tiempoEstimado());
        if(datos.tarifaBase()!=null) metodoDeEnvio.setTarifaBase(datos.tarifaBase());
        if(datos.costoPorKm()!=null) metodoDeEnvio.setCostoPorKm(datos.costoPorKm());

        return new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getTipo(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getTarifaBase(),
                metodoDeEnvio.getCostoPorKm()
        );
    }

    @Transactional(readOnly = true)
    public RespuestaMetodoDeEnvioDto buscarMetodoDeEnvio(Long id){
        MetodoDeEnvio metodoDeEnvio = metodoDeEnvioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        return new RespuestaMetodoDeEnvioDto(
                metodoDeEnvio.getId(),
                metodoDeEnvio.getTipo(),
                metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getTarifaBase(),
                metodoDeEnvio.getCostoPorKm()
        );
    }

    public void eliminarMetodoDeEnvio(Long id){
        MetodoDeEnvio metodoDeEnvio = metodoDeEnvioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        metodoDeEnvio.desactivarMetodoDeEnvio();
    }

}
