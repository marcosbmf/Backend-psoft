package CCCFarma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CCCFarma.model.Usuario;
import CCCFarma.repository.UsuariosRepositorio;

@RestController
@RequestMapping("/user")
public class UsuariosController {
	
	@Autowired
	private UsuariosRepositorio usuarios;

	@GetMapping
	public List<Usuario> listaUsuarios() {
		return usuarios.findAll();
	}
}
