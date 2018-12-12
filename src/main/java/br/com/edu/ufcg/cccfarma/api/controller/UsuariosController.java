package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.repository.UsuariosRepositorio;
import br.com.edu.ufcg.cccfarma.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
