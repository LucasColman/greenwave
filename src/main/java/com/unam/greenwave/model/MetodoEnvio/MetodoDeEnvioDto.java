package com.unam.greenwave.model.MetodoEnvio;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record MetodoDeEnvioDto(
        @NotBlank
        String nombre,
        @NotNull
        TipoEnvio tipo,
        @NotBlank
        String tiempoEstimado,
        @NotNull
        Double costo
) {
}
