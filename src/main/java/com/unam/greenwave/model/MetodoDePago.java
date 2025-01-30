package com.unam.greenwave.model;


import jakarta.persistence.*;

@Entity(name = "MetodoDePago")
@Table(name = "metodos_de_pago")
public class MetodoDePago {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean estado;

    @Enumerated(EnumType.STRING)
    private TipoMetodoPago tipo; // Enum para diferenciar tipos de pago

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario; // Opcional, si un usuario tiene métodos guardados

}
