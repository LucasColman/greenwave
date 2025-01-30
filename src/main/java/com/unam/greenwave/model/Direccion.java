package com.unam.greenwave.model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String ciudad;
    private String codigoPostal;
    private String provincia;

}
