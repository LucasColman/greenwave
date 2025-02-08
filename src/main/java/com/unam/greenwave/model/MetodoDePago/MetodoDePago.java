package com.unam.greenwave.model.MetodoDePago;


import com.unam.greenwave.model.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "MetodoDePago")
@Table(name = "metodos_de_pago")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder
public class MetodoDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titular;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPago tipoPago; // Enum para diferenciar tipos de pago

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "ultima_modificacion")
    private LocalDateTime ultimaModificacion;

    @OneToMany(mappedBy = "metodoDePago" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;

    private Boolean activo;


    // Para tarjetas
    @Column(name = "numero_tarjeta")
    private String numeroTarjeta; // Solo últimos 4 dígitos

    @Column(name = "fecha_expiracion")
    private String fechaExpiracion;
    private String marca; // Visa, MasterCard, etc.

    // Para cuentas bancarias
    @Column(name = "numero_cuenta")
    private String numeroCuenta; // Últimos dígitos o CLAVE
    private String banco;


    // Para Mercado Pago
    private String email;

    @PrePersist // Se ejecuta antes de insertar
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        ultimaModificacion = LocalDateTime.now();
    }

    @PreUpdate // Se ejecuta antes de actualizar
    protected void onUpdate() {
        ultimaModificacion = LocalDateTime.now();
    }


    public void desactivarMetodoDePago() {
        this.activo = false;
    }
}
