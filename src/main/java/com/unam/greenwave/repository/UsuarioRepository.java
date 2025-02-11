package com.unam.greenwave.repository;

import com.unam.greenwave.model.usuario.Rol;
import com.unam.greenwave.model.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario>  findByEmail(String email);

    Optional<Usuario> findById(Long id);

    Optional<Usuario>  findByEmailAndPassword(String email, String password);

    Optional<Usuario>  findByEmailAndRol(String email, String rol);

    Optional<Usuario>  findByRol(Rol rol);

    @Modifying // Necesario para realizar modificaciones en la base de datos
    @Query("UPDATE Usuario p SET p.activo = false WHERE p.id = :id")
    void softDelete(Long id);

    Page<Usuario> findByActivoTrue(Pageable paginacion);
}
