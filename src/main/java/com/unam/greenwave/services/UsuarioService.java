package com.unam.greenwave.services;

import com.unam.greenwave.model.usuario.Usuario;
import com.unam.greenwave.model.usuario.dto.ActualizacionUsuarioDto;
import com.unam.greenwave.model.usuario.dto.ListadoUsuarioDto;
import com.unam.greenwave.model.usuario.dto.RegistroUsuarioDto;
import com.unam.greenwave.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @org.springframework.transaction.annotation.Transactional
    public Usuario registrarUsuario(RegistroUsuarioDto datos) {
        Usuario usuario = new Usuario(datos);
        usuarioRepository.save(usuario);
        return usuario;
    }

    @org.springframework.transaction.annotation.Transactional
    public void actualizarUsuario(ActualizacionUsuarioDto datos) {
        Usuario usuario = usuarioRepository.findByEmail(datos.email())
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));

        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setPassword(datos.password());
        usuario.setRol(datos.rol());

        usuarioRepository.save(usuario);
    }

    @org.springframework.transaction.annotation.Transactional
    public Page<ListadoUsuarioDto> listarUsuarios(Pageable paginacion) {
        return usuarioRepository.findByEmail(paginacion).map(ListadoUsuarioDto::new);
    }

    @org.springframework.transaction.annotation.Transactional
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
        usuarioRepository.softDelete(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Usuario buscarUsuario(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
    }
}
