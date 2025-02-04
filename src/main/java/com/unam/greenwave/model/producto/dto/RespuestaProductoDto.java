package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.Tipo;
import com.unam.greenwave.model.usuario.Rol;

public record RespuestaProductoDto(
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria,
        Tipo tipo

) {
}
