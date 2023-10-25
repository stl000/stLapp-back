package com.stlapp.stlappback.modelos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class UsuarioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter
    private Usuario usuario;

    @ManyToOne
    @Getter @Setter
    private Rol rol;

    public UsuarioRol(){

    }
}
