package com.unam.greenwave.model.producto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unam.greenwave.model.producto.dto.RegistroProductoIndividualDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity(name = "ProductoIndividual")
@Table(name = "productos_individuales")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductoIndividual extends Producto {

    @ManyToMany(mappedBy = "productos")
    @JsonIgnore
    private List<Paquete> paquetes;

    public ProductoIndividual(RegistroProductoIndividualDto registroProductoDto){
        super(registroProductoDto); // Llamada al constructor de la clase padre

    }

}
