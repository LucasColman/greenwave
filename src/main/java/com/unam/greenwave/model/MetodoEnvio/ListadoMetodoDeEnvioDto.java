package com.unam.greenwave.model.MetodoEnvio;

import java.math.BigDecimal;

public record ListadoMetodoDeEnvioDto(
        Long id,
        String nombre,
        TipoEnvio tipoEnvio,
        String tiempoEstimado,
        Double costo
) {

    public ListadoMetodoDeEnvioDto(MetodoDeEnvio metodoDeEnvio){
        this(metodoDeEnvio.getId(), metodoDeEnvio.getNombre(),metodoDeEnvio.getTipoEnvio(),
                metodoDeEnvio.getTiempoEstimado(), metodoDeEnvio.getCosto());
    }
}
