package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.Categoria;

public record RespuestaProductoIndividualDto(
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria

) {
}
