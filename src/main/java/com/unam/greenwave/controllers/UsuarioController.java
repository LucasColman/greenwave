package com.unam.greenwave.controllers;

import com.unam.greenwave.model.usuario.Usuario;
import com.unam.greenwave.model.usuario.dto.ActualizacionUsuarioDto;
import com.unam.greenwave.model.usuario.dto.ListadoUsuarioDto;
import com.unam.greenwave.model.usuario.dto.RegistroUsuarioDto;
import com.unam.greenwave.model.usuario.dto.RespuestaUsuarioDto;
import com.unam.greenwave.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro-usuario")
    public ResponseEntity<RespuestaUsuarioDto> registrarUsuario(@RequestBody @Valid RegistroUsuarioDto registroUsuarioDto, UriComponentsBuilder uriBuilder){
        Usuario usuario = usuarioService.registrarUsuario(registroUsuarioDto);

        RespuestaUsuarioDto respuestaUsuarioDto= new RespuestaUsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getPassword(),
                usuario.getEmail(),
                usuario.getRol()
        );

        URI url = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaUsuarioDto);
    }

    @GetMapping("/lista-usuarios")
    public ResponseEntity<Page<ListadoUsuarioDto>> listarUsuarios(Pageable paginacion){
        var page = usuarioService.listarUsuarios(paginacion);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<RespuestaUsuarioDto> actualizarProducto(@RequestBody @Valid ActualizacionUsuarioDto datos){
        usuarioService.actualizarUsuario(datos);
        Usuario usuario = usuarioService.buscarUsuario(datos.email());

        RespuestaUsuarioDto respuestaUsuarioDto= new RespuestaUsuarioDto(
            usuario.getId(),
            usuario.getNombre(),
            usuario.getPassword(),
            usuario.getEmail(),
            usuario.getRol()
        );
        return ResponseEntity.ok(respuestaUsuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
