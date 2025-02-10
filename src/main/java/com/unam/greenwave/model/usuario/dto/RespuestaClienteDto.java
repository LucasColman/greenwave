package com.unam.greenwave.model.usuario.dto;

import com.unam.greenwave.model.usuario.Rol;

public record RespuestaClienteDto(Long id, String nombre, String password, String email, Rol rol) {
}
