package com.unam.greenwave.controllers;

import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.producto.dto.ListadoPaqueteDto;
import com.unam.greenwave.model.producto.Paquete;
import com.unam.greenwave.model.producto.ProductoIndividual;
import com.unam.greenwave.model.producto.dto.*;
import com.unam.greenwave.services.ProductoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/individual")
    public ResponseEntity<RespuestaProductoDto> registrarProductoIndividual(@RequestBody @Valid RegistroProductoIndividualDto registroProductoDto, UriComponentsBuilder uriBuilder){
        ProductoIndividual productoIndividual = productoService.registrarProductoIndividual(registroProductoDto);

        RespuestaProductoDto respuestaProductoIndividualDto= new RespuestaProductoDto(
                productoIndividual.getId(),
                productoIndividual.getNombre(),
                productoIndividual.getStock(),
                productoIndividual.getPrecio(),
                productoIndividual.getDescripcion(),
                productoIndividual.getCategoria(),
                productoIndividual.getTipo()
        );

        URI url = uriBuilder.path("/productos/{id}").buildAndExpand(productoIndividual.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaProductoIndividualDto);

    }

    @PostMapping("/paquete")
    public ResponseEntity<RespuestaPaqueteDto> registrarPaquete(@RequestBody @Valid RegistroPaqueteDto registroPaqueteDto, UriComponentsBuilder uriBuilder){
        Paquete paquete = productoService.registrarPaquete(registroPaqueteDto,registroPaqueteDto.descuento());

        RespuestaPaqueteDto respuestaPaqueteDto = new RespuestaPaqueteDto(
                paquete.getId(),
                paquete.getNombre(),
                paquete.getStock(),
                paquete.getPrecio(),
                paquete.getDescripcion(),
                paquete.getCategoria(),
                paquete.getDescuento(),
                paquete.getProductos()
        );
        URI url = uriBuilder.path("/productos/{id}").buildAndExpand(paquete.getId()).toUri();

        return ResponseEntity.created(url).body(respuestaPaqueteDto);
    }


    @GetMapping("/individual")
    public ResponseEntity<Page<ListadoProductoDto>> listarProductosIndividuales(Pageable paginacion){
        var page = productoService.listarProductosIndividuales(paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/paquete")
    public ResponseEntity<Page<ListadoPaqueteDto>> listarPaquetes(Pageable paginacion){
        var page = productoService.listarPaquetes(paginacion);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<RespuestaProductoDto> actualizarProducto(@RequestBody @Valid ActualizacionProductoDto datos){
        productoService.actualizarProducto(datos);
        Producto producto = productoService.buscarProducto(datos.id());

        RespuestaProductoDto respuestaProductoIndividualDto= new RespuestaProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getStock(),
                producto.getPrecio(),
                producto.getDescripcion(),
                producto.getCategoria(),
                producto.getTipo()
        );
        return ResponseEntity.ok(respuestaProductoIndividualDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }


}
