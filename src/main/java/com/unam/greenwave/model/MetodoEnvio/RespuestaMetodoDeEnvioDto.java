package com.unam.greenwave.model.MetodoEnvio;

import java.math.BigDecimal;

public record RespuestaMetodoDeEnvioDto(
        Long id,
        TipoEnvio tipo,
        String  tiempoEstimado,
        BigDecimal tarifaBase,
        BigDecimal costoPorKm
) {
}
