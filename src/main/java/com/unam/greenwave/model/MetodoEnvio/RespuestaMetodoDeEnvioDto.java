package com.unam.greenwave.model.MetodoEnvio;

import java.math.BigDecimal;

public record RespuestaMetodoDeEnvioDto(
        Long id,
        String nombre,
        TipoEnvio tipoEnvio,
        String  tiempoEstimado,
        Double costo

) {
}
