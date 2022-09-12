package com.ingeneo.logistica.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "entregas")
public class EntregaEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String nro_transporte;

    @NotEmpty
    private String nro_guia;

    @NotEmpty
    private Integer cant_producto;

    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_registro;

    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_entrega;

    @NotEmpty
    private Double descuento;

    @NotEmpty
    private Double precio_envio;

    @NotEmpty
    private Integer fk_cliente;

    @JoinColumn(name = "nombreCliente", insertable = false, updatable = false)
    private String nombreCliente;

    @JoinColumn(name = "apellidoCliente", insertable = false, updatable = false)
    private String apellidoCliente;

    @NotEmpty
    private Integer fk_almacenamiento;

    @JoinColumn(name = "nombreAlmacenamiento", insertable = false, updatable = false)
    private String nombreAlmacenamiento;

    @NotEmpty
    private Integer fk_tipo_producto;

    @JoinColumn(name = "nombreTipoProducto", insertable = false, updatable = false)
    private String nombreTipoProducto;

    @NotEmpty
    private Integer fk_tipo_logistica;

    @JoinColumn(name = "nombreTipoLogistica", insertable = false, updatable = false)
    private String nombreTipoLogistica;

}
