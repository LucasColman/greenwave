package com.unam.greenwave.model.MetodoDePago.dto;

import com.unam.greenwave.model.MetodoDePago.MetodoDePago;
import com.unam.greenwave.model.MetodoDePago.TipoPago;

public record ListadoMetodoDePagoDto(
        Long id,
        String titular,
        TipoPago tipoPago
) {
    public ListadoMetodoDePagoDto(MetodoDePago metodoDePago) {
        this(metodoDePago.getId(), metodoDePago.getTitular(), metodoDePago.getTipoPago());
    }


}
