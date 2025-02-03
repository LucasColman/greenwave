package com.unam.greenwave.services;

import com.unam.greenwave.model.Vendedor;
import com.unam.greenwave.model.producto.dto.ListadoPaqueteDto;
import com.unam.greenwave.model.producto.Paquete;
import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.producto.ProductoIndividual;
import com.unam.greenwave.model.producto.dto.*;
import com.unam.greenwave.repository.PaqueteRepository;
import com.unam.greenwave.repository.ProductoIndividualRepository;
import com.unam.greenwave.repository.ProductoRepository;
import com.unam.greenwave.repository.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoIndividualRepository productoIndividualRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private VendedorRepository vendedorRepository;



    @org.springframework.transaction.annotation.Transactional
    public ProductoIndividual registrarProductoIndividual(RegistroProductoIndividualDto registroProductoDto) {
        //Validar vendedor
        Optional<Vendedor> vendedor = vendedorRepository.findById(registroProductoDto.vendedor());

        if(vendedor.isPresent()){
            ProductoIndividual productoIndividual = new ProductoIndividual(registroProductoDto);
            productoIndividual.setVendedor(vendedor.get());
            vendedor.get().getProductos().add(productoIndividual);
            //productoIndividualRepository.save(productoIndividual);
            //vendedorRepository.save(vendedor.get());
            return productoIndividual;
        }else{
            throw new RuntimeException("El vendedor no existe");
        }
    }

    @org.springframework.transaction.annotation.Transactional
    public Paquete registrarPaquete(RegistroPaqueteDto registroPaqueteDto, Double descuento) {
        List<ProductoIndividual> productos = productoIndividualRepository.findAllById(registroPaqueteDto.productos());

        if (productos.isEmpty()) {
            throw new RuntimeException("Los productos del paquete no existen");
        }

        //validar vendedor
        Optional<Vendedor> vendedor = vendedorRepository.findById(registroPaqueteDto.vendedor());

        if(vendedor.isPresent()){
            // Disminuir el stock de los productos individuales
            for (ProductoIndividual producto : productos) {
                if (producto.getStock() == 0) {
                    throw new RuntimeException("No hay stock suficiente para el producto " + producto.getNombre());
                }
                producto.setStock(producto.getStock() - 1);
            }

            Paquete paquete = new Paquete(registroPaqueteDto);
            paquete.setProductos(productos);
            paquete.setVendedor(vendedor.get());

            // Calcular el precio del paquete con descuento
            paquete.setDescuento(descuento);
            paquete.setPrecio(paquete.calcularPrecioPaquete());
            paquete.setPrecio(paquete.calcularPrecioConDescuento(descuento));


//            double precioTotal = productos.stream()
//                    .mapToDouble(ProductoIndividual::getPrecio)
//                    .sum();
//            paquete.setPrecio(precioTotal - (precioTotal * descuento / 100));


            //paqueteRepository.save(paquete);
            vendedor.get().getProductos().add(paquete);
            //vendedorRepository.save(vendedor.get());
            return paquete;
        } else {
            throw new RuntimeException("El vendedor no existe");
        }
    }

    public Page<ListadoProductoDto> listarProductosIndividuales(Pageable paginacion){
        return productoIndividualRepository.findByActivoTrue(paginacion).map(ListadoProductoDto::new);
    }

    public Page<ListadoPaqueteDto> listarPaquetes(Pageable paginacion){
        return paqueteRepository.findByActivoTrue(paginacion).map(ListadoPaqueteDto::new);

    }

    @org.springframework.transaction.annotation.Transactional
    public void actualizarProducto(ActualizacionProductoDto datos) {
        Producto producto = productoRepository.findById(datos.id())
                .orElseThrow(() -> new EntityNotFoundException("El producto no existe"));

        //List<ProductoIndividual> productos = productoIndividualRepository.findAllById(datos.productos());
        List<ProductoIndividual> productos = (datos.productos() != null && !datos.productos().isEmpty())
                ? productoIndividualRepository.findAllById(datos.productos())
                : Collections.emptyList();

        if(producto instanceof ProductoIndividual){
            if (datos.nombre() != null) producto.setNombre(datos.nombre());
            if (datos.stock() != null) producto.setStock(datos.stock());
            if (datos.precio() != null) producto.setPrecio(datos.precio());
            if (datos.descripcion() != null) producto.setDescripcion(datos.descripcion());
            if (datos.categoria() != null) producto.setCategoria(datos.categoria());
        } else if (producto instanceof Paquete paquete) {
            if (datos.nombre() != null) paquete.setNombre(datos.nombre());
            if (datos.stock() != null) paquete.setStock(datos.stock());
            if (datos.precio() != null) paquete.setPrecio(datos.precio());
            if (datos.descripcion() != null) paquete.setDescripcion(datos.descripcion());
            if (datos.categoria() != null) paquete.setCategoria(datos.categoria());
            if (datos.descuento() != null) paquete.setDescuento(datos.descuento());
            //Si se enviaron productos, actualizar
            if (!productos.isEmpty()){
                paquete.getProductos().clear();
                paquete.getProductos().addAll(productos);
            }
        }
        //productoRepository.save(producto);
    }

    public ProductoIndividual buscarProductoIndividual(Long id) {
        return productoIndividualRepository.getReferenceById(id);
    }


    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Producto buscarProducto(Long id) {
        return productoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

//    @Transactional
//    public void eliminarProducto(Producto producto){
//        productoRepository.delete(producto);
//    }

    @org.springframework.transaction.annotation.Transactional
    public void eliminarProducto(Long id) {
        Producto producto = buscarProducto(id);

        if (!producto.getActivo()) {
            throw new EntityNotFoundException("El producto ya ha sido eliminado");
        }

        if (producto instanceof ProductoIndividual) {
            manejarEliminacionProductoIndividual((ProductoIndividual) producto);
        } else if (producto instanceof Paquete) {
            manejarEliminacionPaquete((Paquete) producto);
        } else {
            throw new IllegalStateException("Tipo de producto desconocido");
        }

        // Realiza soft delete
        productoRepository.softDelete(id);
    }

    private void manejarEliminacionPaquete(Paquete paquete) {
        // Eliminar referencias al paquete sin eliminar los productos individuales
        paquete.getProductos().clear();
    }

    private void manejarEliminacionProductoIndividual(ProductoIndividual producto) {
        // Remover el producto de todos los paquetes que lo contienen
        for (Paquete paquete : producto.getPaquetes()) {
            paquete.getProductos().remove(producto);
        }
    }

}
