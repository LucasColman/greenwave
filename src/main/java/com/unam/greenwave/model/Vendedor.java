package com.unam.greenwave.model;


import com.unam.greenwave.model.producto.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "Vendedor")
@Table(name = "vendedores")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
public class Vendedor{

    @Id
    private Long id;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Producto> productos = new ArrayList<>();

    @Embedded
    private Direccion direccion;

    @OneToOne
    @MapsId // Mapea el id de la entidad Vendedor con el id de la entidad Usuario
    @JoinColumn(name = "id")
    private Usuario usuario;

}