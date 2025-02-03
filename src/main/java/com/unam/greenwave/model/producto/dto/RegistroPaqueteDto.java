package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.ProductoIndividual;
import com.unam.greenwave.model.producto.Tipo;

import java.util.List;

public record RegistroPaqueteDto(
        String nombre,
        Integer stock,
        String descripcion,
        Categoria categoria,
        Long vendedor,
        List<Long> productos,
        Double descuento,
        Tipo tipo
) {
}
