package com.unam.greenwave.controllers;

import com.unam.greenwave.model.MetodoDePago.dto.ActualizarMetodoDePagoDto;
import com.unam.greenwave.model.MetodoDePago.dto.ListadoMetodoDePagoDto;
import com.unam.greenwave.model.MetodoDePago.dto.MetodoDePagoDto;
import com.unam.greenwave.model.MetodoDePago.dto.RespuestaMetodoDePagoDto;
import com.unam.greenwave.services.MetodoDePagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/metodos_de_pago")
public class MetodoDePagoController {

    @Autowired
    private MetodoDePagoService metodoDePagoService;


    @PostMapping
    public ResponseEntity<RespuestaMetodoDePagoDto> registrarMetodoDePago(@RequestBody @Valid MetodoDePagoDto metodoDePagoDto, UriComponentsBuilder uriBuilder){
        RespuestaMetodoDePagoDto respuestaMetodoDePagoDto = metodoDePagoService.registrarMetodoDePago(metodoDePagoDto);

        URI url = uriBuilder.path("/metodos_de_pago/{id}").buildAndExpand(respuestaMetodoDePagoDto.id()).toUri();
        return ResponseEntity.created(url).body(respuestaMetodoDePagoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoMetodoDePagoDto>> listarMetodosDePago(Pageable paginacion){
        var page = metodoDePagoService.listarMetodosDePago(paginacion).map(ListadoMetodoDePagoDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaMetodoDePagoDto> buscarMetodoDePago(@PathVariable Long id){
        RespuestaMetodoDePagoDto respuestaMetodoDePagoDto = metodoDePagoService.buscarMetodoDePago(id);

        return ResponseEntity.ok(respuestaMetodoDePagoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaMetodoDePagoDto> actualizarMetodoDePago(@PathVariable Long id, @RequestBody @Valid ActualizarMetodoDePagoDto datos){
        RespuestaMetodoDePagoDto respuestaMetodoDePagoDto = metodoDePagoService.actualizarMetodoDePago(id,datos);

        return ResponseEntity.ok(respuestaMetodoDePagoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMetodoDePago(@PathVariable Long id){
        metodoDePagoService.eliminarMetodoDePago(id);
        return ResponseEntity.noContent().build();
    }


}
