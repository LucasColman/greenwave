package com.unam.greenwave.model;

import jakarta.persistence.*;

@Embeddable
public class DireccionDeEnvio {

    private String calle;
    private String numero;
    private String ciudad;
    private String codigoPostal;
    private String provincia;

}
