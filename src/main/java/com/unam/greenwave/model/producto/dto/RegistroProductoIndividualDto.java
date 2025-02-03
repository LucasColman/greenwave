package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.Tipo;
import jakarta.validation.constraints.NotBlank;

public record RegistroProductoIndividualDto(
        @NotBlank
        String nombre,
        @NotBlank
        Integer stock,
        @NotBlank
        Double precio,
        @NotBlank
        String descripcion,
        @NotBlank
        Categoria categoria,
        @NotBlank
        Long vendedor,
        Tipo tipo


) {

}
