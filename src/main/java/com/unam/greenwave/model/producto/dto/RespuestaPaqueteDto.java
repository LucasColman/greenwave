package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.ProductoIndividual;

import java.util.List;

public record RespuestaPaqueteDto(
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria,
        Double descuento,
        List<ProductoIndividual> productos
) {
}
