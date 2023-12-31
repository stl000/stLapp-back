package com.stlapp.stlappback.controladores;

import com.stlapp.stlappback.modelos.Rol;
import com.stlapp.stlappback.modelos.Usuario;
import com.stlapp.stlappback.modelos.UsuarioRol;
import com.stlapp.stlappback.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("usuarioId") Long usuarioId) throws Exception {
        return usuarioService.obtenerUsuario(usuarioId);
    }

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{

        usuario.setPerfil("default.png");

        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

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


    @PutMapping("/{usuarioId}")
    public Usuario actualizarUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody Usuario usuario) throws Exception{
        return usuarioService.actualizarUsuario(usuarioId, usuario);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) throws Exception {
        usuarioService.eliminarUsuario(usuarioId);
    }




}
