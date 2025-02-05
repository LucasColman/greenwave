package com.unam.greenwave.model.MetodoEnvio;

import com.unam.greenwave.model.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "MetodoDeEnvio")
@Table(name = "metodos_de_envio")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class MetodoDeEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre; // "Envío Express"

    @Column(name = "tipo_envio")
    private TipoEnvio tipoEnvio; // Local, Provincial, Nacional

    @Column(name = "tiempo_estimado")
    private String tiempoEstimado;

    @Column(name = "costo")
    private Double costo;

    private Boolean activo;

    @PrePersist
    public void prePersist() {
        this.activo = true;
    }

    public void desactivarMetodoDeEnvio() {
        this.activo = false;
    }
}
