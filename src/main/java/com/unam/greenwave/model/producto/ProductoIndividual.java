package com.unam.greenwave.model.producto;

import com.unam.greenwave.model.producto.dto.RegistroProductoIndividualDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity(name = "ProductoIndividual")
@Table(name = "productos_individuales")
public class ProductoIndividual extends Producto {

    @ManyToMany(mappedBy = "productos")
    private List<Paquete> paquetes;


    public ProductoIndividual(){
    }

    public ProductoIndividual(RegistroProductoIndividualDto registroProductoDto){
        super(registroProductoDto); // Llamada al constructor de la clase padre

    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }
}
