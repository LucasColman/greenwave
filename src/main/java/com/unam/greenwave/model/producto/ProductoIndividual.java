package com.unam.greenwave.model.producto;

import com.unam.greenwave.model.producto.dto.RegistroProductoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "ProductoIndividual")
@Table(name = "productos_individuales")
@Setter @Getter
@NoArgsConstructor
public class ProductoIndividual extends Producto {
    private String descripcion;

    public ProductoIndividual(RegistroProductoDto registroProductoDto){
        super(registroProductoDto); // Llamada al constructor de la clase padre
        this.descripcion = registroProductoDto.descripcion();
    }



}
