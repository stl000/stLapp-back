package com.stlapp.stlappback.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    @Getter @Setter
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Rol(){

    }
}
