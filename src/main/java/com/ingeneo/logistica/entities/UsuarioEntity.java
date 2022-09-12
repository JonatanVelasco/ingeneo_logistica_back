package com.ingeneo.logistica.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String documento;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    @NotEmpty
    private String telefono;

    @NotEmpty
    @Email
    private String correo;

    @NotEmpty
    @Column(length = 50 )
    private String username;

    @NotEmpty
    @Column(length = 200 )
    private String password;

    @NotEmpty
    private Integer fk_rol;

    @ManyToOne
    @JoinColumn(name = "fk_rol",insertable = false, updatable = false, nullable = false)
    private RolEntity roles;

}
