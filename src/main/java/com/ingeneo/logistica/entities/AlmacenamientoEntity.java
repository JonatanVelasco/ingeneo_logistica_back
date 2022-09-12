package com.ingeneo.logistica.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "almacenamientos")
public class AlmacenamientoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nombre;

    private String descripcion;

    private String ubicacion;

    @NotEmpty
    @NotNull
    @Min(value = 1)
    private Integer fk_tipo_almacenamiento;

    @JoinColumn(name = "nombretipoalmacenamiento", insertable = false, updatable = false)
    private String nombretipoalmacenamiento;

}
