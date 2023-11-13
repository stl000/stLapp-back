package com.stlapp.stlappback.servicios;


import com.stlapp.stlappback.modelos.Usuario;
import com.stlapp.stlappback.modelos.UsuarioRol;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public ResponseEntity<Usuario> obtenerUsuario(Long usuarioId) throws Exception;

    public void eliminarUsuario(Long usuarioId) throws Exception;

    List<Usuario> obtenerUsuarios();

    public ResponseEntity<Usuario> actualizarUsuario(Long usuarioId, Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

}
