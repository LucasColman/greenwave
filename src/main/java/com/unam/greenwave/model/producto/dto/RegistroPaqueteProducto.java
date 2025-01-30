package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.producto.ProductoIndividual;

import java.util.List;

public record RegistroPaqueteProducto(
        List<ProductoIndividual> productos
) {
}
