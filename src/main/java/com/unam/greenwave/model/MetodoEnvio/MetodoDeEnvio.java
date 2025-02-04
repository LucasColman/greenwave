package com.unam.greenwave.model.MetodoEnvio;

import com.unam.greenwave.model.Pedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    private TipoEnvio tipo;

    @Column(name = "tiempo_estimado")
    private String tiempoEstimado;

    @Column(name = "tarifa_base")
    private BigDecimal tarifaBase;

    @Column(name = "costo_por_km")
    private BigDecimal costoPorKm;

    private Boolean activo;

    @PrePersist
    public void prePersist() {
        this.activo = true;
    }



    public BigDecimal calcularCostoEnvio(Double distanciaKm ){
        BigDecimal costoTotal = tarifaBase;

        BigDecimal distancia = BigDecimal.valueOf(distanciaKm); // Convertir a BigDecimal

        return switch (tipo) {
            case LOCAL -> calcularCostoLocal();
            case PROVINCIAL -> calcularCostoProvincial(distancia);
            case NACIONAL -> calcularCostoNacional(distancia);
        };
    }

    private BigDecimal calcularCostoLocal() {
        return tarifaBase;
    }

    private BigDecimal calcularCostoProvincial(BigDecimal distancia) {
        // Tarifa base + (costo por km * distancia)
        BigDecimal costoPorDistancia = costoPorKm.multiply(distancia);

        return tarifaBase.add(costoPorDistancia);
    }

    private BigDecimal calcularCostoNacional(BigDecimal distancia) {
        // Similar al provincial pero con tarifa adicional por servicio nacional
        BigDecimal costoPorDistancia = costoPorKm.multiply(distancia);

        BigDecimal tarifaNacional = BigDecimal.valueOf(20); // Cargo adicional por servicio nacional
        return tarifaBase.add(costoPorDistancia).add(tarifaNacional);
    }


    public void desactivarMetodoDeEnvio() {
        this.activo = false;
    }
}
