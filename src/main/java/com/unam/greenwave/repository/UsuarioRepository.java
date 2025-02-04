package com.unam.greenwave.repository;

import com.unam.greenwave.model.usuario.Rol;
import com.unam.greenwave.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByEmail(String email);

    Optional<Usuario> findById(Long id);

    Optional<Usuario>  findByEmailAndPassword(String email, String password);

    Optional<Usuario>  findByEmailAndRol(String email, String rol);

    Optional<Usuario>  findByRol(Rol rol);

    //save(Usuario usuario);

    void softDelete(Long id);

    Page<Usuario> findByEmail(Pageable paginacion);
}
