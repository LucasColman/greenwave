package com.unam.greenwave.model.producto.dto;

import com.unam.greenwave.model.producto.Categoria;
import com.unam.greenwave.model.producto.Paquete;
import com.unam.greenwave.model.producto.ProductoIndividual;

import java.util.List;

public record ListadoPaqueteDto(
        Long id,
        String nombre,
        Integer stock,
        Double precio,
        String descripcion,
        Categoria categoria,
        List<ProductoIndividual> productos,
        Double descuento,
        Boolean activo
) {
    public ListadoPaqueteDto(Paquete paquete){
        this(paquete.getId(), paquete.getNombre(), paquete.getStock(), paquete.getPrecio(), paquete.getDescripcion(),
                paquete.getCategoria(), paquete.getProductos(), paquete.getDescuento(),paquete.getActivo());
    }

}
