package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.producto.Categoria;

public record RegistroProductoDto(
        String nombre,
        Integer stock,
        Double precioUnitario,
        String descripcion,
        Categoria categoria,

        String imagen
) {
}
