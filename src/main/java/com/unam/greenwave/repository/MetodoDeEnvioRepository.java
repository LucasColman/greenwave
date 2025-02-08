package com.unam.greenwave.repository;

import com.unam.greenwave.model.MetodoEnvio.MetodoDeEnvio;
import com.unam.greenwave.model.MetodoEnvio.TipoEnvio;
import com.unam.greenwave.model.producto.Producto;
import com.unam.greenwave.model.producto.Tipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MetodoDeEnvioRepository extends JpaRepository<MetodoDeEnvio, Long> {

    Page<MetodoDeEnvio> findByActivoTrue(Pageable paginacion);
    Optional<MetodoDeEnvio> findByIdAndActivoTrue(Long id);
}
