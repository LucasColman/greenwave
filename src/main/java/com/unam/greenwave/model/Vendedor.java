package com.unam.greenwave.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "Vendedor")
@Table(name = "vendedores")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@ToString
public class Vendedor{

    @Id
    private Long id;

    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore  // Evita la recursión infinita
    private List<Producto> productos = new ArrayList<>();


    @OneToOne
    @MapsId // Mapea el id de la entidad Vendedor con el id de la entidad Usuario
    @JoinColumn(name = "id")
    @JsonIgnore
    private Usuario usuario;



}