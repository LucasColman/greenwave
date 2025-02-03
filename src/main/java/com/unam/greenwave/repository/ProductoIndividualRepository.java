package com.unam.greenwave.repository;

import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.producto.ProductoIndividual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoIndividualRepository extends JpaRepository<ProductoIndividual, Long> {
    Page<Producto> findByActivoTrue(Pageable paginacion);
}
