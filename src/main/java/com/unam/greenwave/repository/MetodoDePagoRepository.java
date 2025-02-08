package com.unam.greenwave.repository;

import com.unam.greenwave.model.MetodoDePago.MetodoDePago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetodoDePagoRepository extends JpaRepository<MetodoDePago,Long> {
    Page<MetodoDePago> findByActivoTrue(Pageable paginacion);

    Optional<MetodoDePago> findByActivoTrue(Long id);
}
