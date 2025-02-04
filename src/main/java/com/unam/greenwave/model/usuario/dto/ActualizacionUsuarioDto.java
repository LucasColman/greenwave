package com.unam.greenwave.model.usuario.dto;

import com.unam.greenwave.model.usuario.Rol;

public record ActualizacionUsuarioDto(
    String nombre,
    String email,
    String password,
    Rol rol
) {
}
