package com.unam.greenwave.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Administrador")
@Table(name = "administradores")
@Getter @Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Administrador  {
    @Id
    private Long id;

    @OneToOne
    @MapsId // Mapea el id de la entidad Administrador con el id de la entidad Usuario
    @JoinColumn(name = "id")
    private Usuario usuario;
}
