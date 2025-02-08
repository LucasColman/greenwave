package com.unam.greenwave.services;

import com.unam.greenwave.model.MetodoDePago.MetodoDePago;
import com.unam.greenwave.model.MetodoDePago.TipoPago;
import com.unam.greenwave.model.MetodoDePago.dto.ActualizarMetodoDePagoDto;
import com.unam.greenwave.model.MetodoDePago.dto.MetodoDePagoDto;
import com.unam.greenwave.model.MetodoDePago.dto.RespuestaMetodoDePagoDto;
import com.unam.greenwave.repository.MetodoDePagoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetodoDePagoService {

    @Autowired
    private MetodoDePagoRepository metodoDePagoRepository;



    public RespuestaMetodoDePagoDto registrarMetodoDePago(MetodoDePagoDto metodoDePagoDto){
        MetodoDePago metodoDePago = MetodoDePago.builder()
                .titular(metodoDePagoDto.titular())
                .tipoPago(metodoDePagoDto.tipoPago())
                .build();

        if(metodoDePago.getTipoPago() == TipoPago.TARJETA_CREDITO || metodoDePago.getTipoPago() == TipoPago.TARJETA_DEBITO){
            metodoDePago.setNumeroTarjeta(metodoDePagoDto.numeroTarjeta());
            metodoDePago.setFechaExpiracion(metodoDePagoDto.fechaExpiracion());
            metodoDePago.setMarca(metodoDePagoDto.marca());
        } else if(metodoDePago.getTipoPago() == TipoPago.CUENTA_BANCARIA){
            metodoDePago.setNumeroCuenta(metodoDePagoDto.numeroCuenta());
            metodoDePago.setBanco(metodoDePagoDto.banco());
        } else if(metodoDePago.getTipoPago() == TipoPago.MERCADO_PAGO){
            metodoDePago.setEmail(metodoDePagoDto.email());
        } else{
            throw new IllegalArgumentException("Tipo de pago no soportado");
        }

        metodoDePagoRepository.save(metodoDePago);

        return new RespuestaMetodoDePagoDto(
                metodoDePago.getId(),
                metodoDePago.getTitular(),
                metodoDePago.getTipoPago().name()
        );
    }

    public Page<MetodoDePago> listarMetodosDePago(Pageable paginacion){
        return metodoDePagoRepository.findByActivoTrue(paginacion);
    }

    @Transactional(readOnly = true)
    public RespuestaMetodoDePagoDto buscarMetodoDePago(Long id){
        MetodoDePago metodoDePago = metodoDePagoRepository.findByActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        return new RespuestaMetodoDePagoDto(
                metodoDePago.getId(),
                metodoDePago.getTitular(),
                metodoDePago.getTipoPago().name()
        );

    }

    @Transactional
    public RespuestaMetodoDePagoDto actualizarMetodoDePago(Long id, ActualizarMetodoDePagoDto actualizacionMetodoDePagoDto){
        MetodoDePago metodoDePago = metodoDePagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        if(actualizacionMetodoDePagoDto.titular() != null) metodoDePago.setTitular(actualizacionMetodoDePagoDto.titular());
        if(actualizacionMetodoDePagoDto.tipoPago() != null) metodoDePago.setTipoPago(TipoPago.valueOf(actualizacionMetodoDePagoDto.tipoPago()));
        if(actualizacionMetodoDePagoDto.numeroTarjeta() != null) metodoDePago.setNumeroTarjeta(actualizacionMetodoDePagoDto.numeroTarjeta());
        if(actualizacionMetodoDePagoDto.fechaExpiracion() != null) metodoDePago.setFechaExpiracion(actualizacionMetodoDePagoDto.fechaExpiracion());
        if(actualizacionMetodoDePagoDto.marca() != null) metodoDePago.setMarca(actualizacionMetodoDePagoDto.marca());
        if(actualizacionMetodoDePagoDto.numeroCuenta() != null) metodoDePago.setNumeroCuenta(actualizacionMetodoDePagoDto.numeroCuenta());
        if(actualizacionMetodoDePagoDto.banco() != null) metodoDePago.setBanco(actualizacionMetodoDePagoDto.banco());
        if(actualizacionMetodoDePagoDto.email() != null) metodoDePago.setEmail(actualizacionMetodoDePagoDto.email());

        return new RespuestaMetodoDePagoDto(
                metodoDePago.getId(),
                metodoDePago.getTitular(),
                metodoDePago.getTipoPago().name()
        );
    }

    public void eliminarMetodoDePago(Long id){
        MetodoDePago metodoDePago = metodoDePagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El metodo de pago no existe"));

        metodoDePago.desactivarMetodoDePago();
    }


}
