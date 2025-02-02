package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.producto.Categoria;
import jakarta.validation.constraints.NotNull;

public record ActualizacionProductoDto(
        @NotNull
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria
) {
}
