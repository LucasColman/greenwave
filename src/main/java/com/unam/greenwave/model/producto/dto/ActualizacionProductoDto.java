package com.unam.greenwave.model.producto.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unam.greenwave.model.producto.Categoria;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) //Solo se incluiran los valores no nulos
public record ActualizacionProductoDto(
        @NotNull(message = "El id no puede ser nulo")
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria,
        List<Long> productos,
        Double descuento
) {
}
