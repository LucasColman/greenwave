package com.unam.greenwave.model.MetodoDePago.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unam.greenwave.model.MetodoDePago.TipoPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MetodoDePagoDto(
        @NotBlank
        String titular,
        @NotNull
        TipoPago tipoPago,

        //Tarjetas
        @NotBlank
        String numeroTarjeta,
        @NotBlank
        String fechaExpiracion,
        @NotBlank
        String marca,

        //banco
        @NotBlank
        String numeroCuenta,
        @NotBlank
        String banco,

        //MP
        @NotBlank
        String email
) {
}
