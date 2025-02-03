package com.unam.greenwave.model.producto;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unam.greenwave.model.DetallePedido;
import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.dto.RegistroPaqueteDto;
import com.unam.greenwave.model.producto.dto.RegistroProductoIndividualDto;
import jakarta.persistence.*;
import lombok.*;

/*
Producto es una clase abstracta ya que no se pueden crear instancias de ella, sino de sus subclases
Producto individual o de Paquete
*/

@Entity(name = "Producto")
@Table(name = "productos")
@ToString
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED) // Se usa JOINED para crear una tabla para cada subclase y evitar columnas innecesarias en Usuario.
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer stock;
    private String descripcion;
    private Double precio;
    //private String imagen;
    @Column(name = "activo")
    private Boolean activo = true;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
//    @Size(max = 3, message = "No se pueden agregar más de 3 imágenes a un producto")
//    private List<String> imagenes;


    public Producto(RegistroProductoIndividualDto registroProductoDto){
        this.nombre = registroProductoDto.nombre();
        this.stock = registroProductoDto.stock();
        this.precio = registroProductoDto.precio();
        this.categoria = registroProductoDto.categoria();
        this.descripcion = registroProductoDto.descripcion();
        this.tipo = registroProductoDto.tipo();
    }

    public Producto(RegistroPaqueteDto registroPaqueteDto){
        this.nombre = registroPaqueteDto.nombre();
        this.stock = registroPaqueteDto.stock();
        this.categoria = registroPaqueteDto.categoria();
        this.descripcion = registroPaqueteDto.descripcion();
        this.tipo = registroPaqueteDto.tipo();
    }



    public Double calcularPrecioConDescuento(Double descuento) {
        return precio - (precio * (descuento / 100));
    }


}
