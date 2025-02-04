package com.unam.greenwave.model.usuario.dto;

import com.unam.greenwave.model.usuario.Rol;
import com.unam.greenwave.model.usuario.Usuario;

public record ListadoUsuarioDto(
        Long id,
        String nombre,
        String email,
        String password,
        Rol rol
) {
    public ListadoUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
    }
}
