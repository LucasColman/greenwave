package com.unam.greenwave.model.producto;

import com.unam.greenwave.model.producto.dto.RegistroPaqueteProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Paquete")
@Table(name = "paquetes")
@NoArgsConstructor
@Getter @Setter
public class Paquete extends Producto{

    @ManyToMany
    @JoinTable(
            name = "paquete_productos",
            joinColumns = @JoinColumn(name = "paquete_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<ProductoIndividual> productos = new ArrayList<>();


    public void agregarProducto(ProductoIndividual producto) {
        productos.add(producto);
    }


    // Calcular el precio total del paquete
    public Double calcularPrecioPaquete() {
        return productos.stream()
                .mapToDouble(ProductoIndividual::getPrecioUnitario)
                .sum();
    }
}
