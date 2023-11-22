package com.stlapp.stlappback.servicios.impl;

import com.stlapp.stlappback.excepciones.UsuarioFoundException;
import com.stlapp.stlappback.excepciones.UsuarioNotFoundException;
import com.stlapp.stlappback.modelos.Usuario;
import com.stlapp.stlappback.modelos.UsuarioRol;
import com.stlapp.stlappback.repositorios.RolRepository;
import com.stlapp.stlappback.repositorios.UsuarioRepository;
import com.stlapp.stlappback.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya está registrado");
            throw new UsuarioFoundException("El usuario ya existe en la bbdd");
        }else{
            System.out.println("usuarioRoles: "+usuarioRoles);
            for(UsuarioRol usuarioRol: usuarioRoles){
                System.out.println("usuarioRolGuardado: "+usuarioRol.getRol());
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public ResponseEntity<Usuario> obtenerUsuario(Long usuarioId) throws Exception {

        Usuario usuarioLocal = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("No se encuentra el usuario"));

        return ResponseEntity.ok(usuarioLocal);
    }

    @Override
    public void eliminarUsuario(Long usuarioId) throws Exception {

        Optional<Usuario> usuarioLocal = usuarioRepository.findById(usuarioId);
        if(!usuarioLocal.isPresent()){
            System.out.println("El usuario no está registrado");
            throw new UsuarioNotFoundException("El usuario no existe en la bbdd");
        }else{
            usuarioRepository.deleteById(usuarioId);
        }
    }

    @Override
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Long usuarioId, Usuario usuario) throws Exception{
        Usuario usuarioLocal = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario no existe en la bbdd"));

        if(usuario.getPerfil()!=null){
            usuarioLocal.setPerfil(usuario.getPerfil());
        }
        if(usuario.getNombre()!=null){
            usuarioLocal.setNombre(usuario.getNombre());
        }
        if(usuario.getApellidos()!=null){
            usuarioLocal.setApellidos(usuario.getApellidos());
        }
        if(usuario.getEmail()!=null){
            usuarioLocal.setEmail(usuario.getEmail());
        }
        if(usuario.getTelefono()!=null){
            usuarioLocal.setTelefono(usuario.getTelefono());
        }
        if(usuario.getUsername()!=null) {
            usuarioLocal.setUsername(usuario.getUsername());
        }
        if(usuario.getPassword()!=null){
            usuarioLocal.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));
        }

        if(usuario.getUsuarioRoles()!=null){
            for(UsuarioRol usuarioRol: usuario.getUsuarioRoles()){
                rolRepository.save(usuarioRol.getRol());
            }
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioLocal);

        return usuarioActualizado;

    }

}
