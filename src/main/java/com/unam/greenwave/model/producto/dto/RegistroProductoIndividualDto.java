package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.Tipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroProductoIndividualDto(
        @NotBlank
        String nombre,
        @NotNull
        Integer stock,
        @NotNull
        Double precio,
        @NotBlank
        String descripcion,
        @NotNull
        Categoria categoria,
        @NotNull
        Long vendedor,
        @NotNull
        Tipo tipo


) {

}
