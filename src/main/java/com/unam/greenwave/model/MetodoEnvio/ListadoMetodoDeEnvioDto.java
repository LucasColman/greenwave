package com.unam.greenwave.model.MetodoEnvio;

import java.math.BigDecimal;

public record ListadoMetodoDeEnvioDto(
        Long id,
        TipoEnvio tipo,
        String tiempoEstimado,
        BigDecimal tarifaBase,
        BigDecimal costoPorKm
) {

    public ListadoMetodoDeEnvioDto(MetodoDeEnvio metodoDeEnvio){
        this(metodoDeEnvio.getId(), metodoDeEnvio.getTipo(), metodoDeEnvio.getTiempoEstimado(),
                metodoDeEnvio.getTarifaBase(), metodoDeEnvio.getCostoPorKm());
    }
}
