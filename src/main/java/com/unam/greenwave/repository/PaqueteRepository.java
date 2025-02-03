package com.unam.greenwave.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.unam.greenwave.model.producto.Paquete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    Page<Paquete> findByActivoTrue(Pageable paginacion);
}
