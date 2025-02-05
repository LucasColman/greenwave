package com.unam.greenwave.model;

import java.util.List;

import com.unam.greenwave.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity(name = "Cliente")
@Table(name = "clientes")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Cliente {
    @Id
    private Long id;

    @OneToOne
    @MapsId // Mapea el id de la entidad Cliente con el id de la entidad Usuario
    @JoinColumn(name = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

    @Embedded
    private DireccionDeEnvio direccion;

}
