package com.unam.greenwave.repository;

import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.producto.ProductoIndividual;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p WHERE p.id = :id AND p.activo = true")
    Optional<Producto> findByIdAndActivoTrue(Long id);

    @Modifying
    @Query("UPDATE Producto p SET p.activo = false WHERE p.id = :id")
    void softDelete(Long id);
}
