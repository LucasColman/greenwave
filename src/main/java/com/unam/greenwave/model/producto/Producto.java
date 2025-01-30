package com.unam.greenwave.model.producto;

import java.util.List;



import com.unam.greenwave.model.DetallePedido;
import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.dto.RegistroProductoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

/*
Producto es una clase abstracta ya que no se pueden crear instancias de ella, sino de sus subclases
Producto individual o de Paquete
*/

@Entity(name = "Producto")
@Table(name = "productos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED) // Se usa JOINED para crear una tabla para cada subclase y evitar columnas innecesarias en Usuario.
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer stock;
    private Double precioUnitario;
    private String imagen;


    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", orphanRemoval = true)
//    @Size(max = 3, message = "No se pueden agregar más de 3 imágenes a un producto")
//    private List<Imagen> imagenes;

    public Producto(RegistroProductoDto registroProductoDto){
        this.nombre = registroProductoDto.nombre();
        this.stock = registroProductoDto.stock();
        this.precioUnitario = registroProductoDto.precioUnitario();
        this.categoria = registroProductoDto.categoria();
        this.imagen = registroProductoDto.imagen();
    }



    public Double calcularPrecioConDescuento(Double descuento) {
        return precioUnitario - (precioUnitario * (descuento / 100));
    }

}
