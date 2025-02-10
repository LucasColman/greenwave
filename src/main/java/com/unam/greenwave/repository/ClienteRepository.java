package com.unam.greenwave.repository;

import com.unam.greenwave.model.Cliente;
import com.unam.greenwave.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente>  findByUsuario(Usuario usuario);

}
