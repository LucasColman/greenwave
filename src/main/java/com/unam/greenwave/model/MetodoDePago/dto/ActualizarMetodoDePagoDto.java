package com.unam.greenwave.model.MetodoDePago.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ActualizarMetodoDePagoDto(
        String titular,
        String tipoPago,
        String numeroTarjeta,
        String fechaExpiracion,
        String marca,
        String numeroCuenta,
        String banco,
        String email
) {
}
