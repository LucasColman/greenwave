package com.unam.greenwave.controllers;

import com.unam.greenwave.model.MetodoEnvio.*;
import com.unam.greenwave.services.MetodoDeEnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/metodos_de_envio")
public class MetodoDeEnvioController {

    @Autowired
    private MetodoDeEnvioService metodoDeEnvioService;

    @PostMapping
    public ResponseEntity<RespuestaMetodoDeEnvioDto> registrarMetodoDeEnvio(@RequestBody @Valid MetodoDeEnvioDto datos,  UriComponentsBuilder uriBuilder){
        RespuestaMetodoDeEnvioDto respuestaMetodoDeEnvioDto = metodoDeEnvioService.registrarMetodoDeEnvio(datos);

        URI url = uriBuilder.path("/metodos_de_envio/{id}").buildAndExpand(respuestaMetodoDeEnvioDto.id()).toUri();
        return ResponseEntity.created(url).body(respuestaMetodoDeEnvioDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoMetodoDeEnvioDto>> listarMetodosDeEnvio(Pageable paginacion){
        var page = metodoDeEnvioService.listarMetodosDeEnvio(paginacion).map(ListadoMetodoDeEnvioDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaMetodoDeEnvioDto> buscarMetodoDeEnvio(@PathVariable Long id){
        RespuestaMetodoDeEnvioDto respuestaMetodoDeEnvioDto = metodoDeEnvioService.buscarMetodoDeEnvio(id);

        return ResponseEntity.ok(respuestaMetodoDeEnvioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaMetodoDeEnvioDto> actualizarMetodoDeEnvio(@PathVariable Long id, @RequestBody @Valid ActualizarMetodoDeEnvioDto datos){
        RespuestaMetodoDeEnvioDto respuestaMetodoDeEnvioDto = metodoDeEnvioService.actualizarMetodoDeEnvio(id, datos);

        return ResponseEntity.ok(respuestaMetodoDeEnvioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMetodoDeEnvio(@PathVariable Long id){
        metodoDeEnvioService.eliminarMetodoDeEnvio(id);
        return ResponseEntity.noContent().build();
    }

}
