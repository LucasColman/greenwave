package com.unam.greenwave.model.producto;

import java.util.List;



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
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
//    @Size(max = 3, message = "No se pueden agregar más de 3 imágenes a un producto")
//    private List<String> imagenes;


    public Producto() {
    }

    public Producto(RegistroProductoIndividualDto registroProductoDto){
        this.nombre = registroProductoDto.nombre();
        this.stock = registroProductoDto.stock();
        this.precio = registroProductoDto.precio();
        this.categoria = registroProductoDto.categoria();
        this.descripcion = registroProductoDto.descripcion();
    }

    public Producto(RegistroPaqueteDto registroPaqueteDto){
        this.nombre = registroPaqueteDto.nombre();
        this.stock = registroPaqueteDto.stock();
        this.categoria = registroPaqueteDto.categoria();
        this.descripcion = registroPaqueteDto.descripcion();
    }



    public Double calcularPrecioConDescuento(Double descuento) {
        return precio - (precio * (descuento / 100));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

//    public String getImagen() {
//        return imagen;
//    }
//
//    public void setImagen(String imagen) {
//        this.imagen = imagen;
//    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }

    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
