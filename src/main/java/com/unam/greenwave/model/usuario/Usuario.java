package com.unam.greenwave.model.usuario;


import com.unam.greenwave.model.usuario.Rol;
import com.unam.greenwave.model.usuario.dto.RegistroUsuarioDto;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Column(nullable = false)
    private boolean activo = true;

    public Usuario(RegistroUsuarioDto registroUsuarioDto) {
        this.nombre = registroUsuarioDto.nombre();
        this.email = registroUsuarioDto.email();
        this.password = registroUsuarioDto.password();
        this.rol = registroUsuarioDto.rol();
    }

}
