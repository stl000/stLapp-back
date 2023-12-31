package com.stlapp.stlappback;

import com.stlapp.stlappback.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StLappBackApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(StLappBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
/*
		try{
			Usuario usuario = new Usuario();
			usuario.setNombre("Javier");
			usuario.setApellidos("Benavides Gonzalez");
			usuario.setTelefono("987654321");
			usuario.setEmail("stl.benavides@gmail.com");
			usuario.setUsername("stL");
			usuario.setPassword(bCryptPasswordEncoder.encode("321321"));
			usuario.setPerfil("foto.png");

			Rol rol = new Rol();
			rol.setId(1L);
			rol.setNombre("ADMIN");

			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setUsuario(usuario);
			usuarioRol.setRol(rol);

			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			usuarioRoles.add(usuarioRol);

			Usuario usuarioCreado = usuarioService.guardarUsuario(usuario, usuarioRoles);

			System.out.println(usuarioCreado.getUsername());
		}catch(UsuarioFoundException exception){
			exception.printStackTrace();
		}*/
	}
}
