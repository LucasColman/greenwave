package com.unam.greenwave.model;

import com.unam.greenwave.model.MetodoDePago.MetodoDePago;
import com.unam.greenwave.model.MetodoEnvio.MetodoDeEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Entity(name = "Pedido")
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    private LocalDateTime fecha; // Fecha de creación
    private String estado; // Pendiente, Enviado, Entregado, Cancelado

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DetallePedido> detallePedidos;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "metodo_de_pago_id", nullable = false)
    private MetodoDePago metodoDePago;

    @ManyToOne
    @JoinColumn(name = "metodo_envio_id")
    private MetodoDeEnvio metodoDeEnvio;





}