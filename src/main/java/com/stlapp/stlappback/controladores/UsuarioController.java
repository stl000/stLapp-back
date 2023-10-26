package com.stlapp.stlappback.controladores;

import com.stlapp.stlappback.modelos.Rol;
import com.stlapp.stlappback.modelos.Usuario;
import com.stlapp.stlappback.modelos.UsuarioRol;
import com.stlapp.stlappback.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{

        usuario.setPerfil("default.png");

        Rol rol = new Rol();
        rol.setId(2L);
        rol.setNombre("USER");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        usuarioRoles.add(usuarioRol);

        return usuarioService.guardarUsuario(usuario, usuarioRoles);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

}
