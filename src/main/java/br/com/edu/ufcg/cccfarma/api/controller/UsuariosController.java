package br.com.edu.ufcg.cccfarma.api.controller;

import br.com.edu.ufcg.cccfarma.api.repository.UsuariosRepositorio;
import br.com.edu.ufcg.cccfarma.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuariosController {

	private UsuariosRepositorio usuarios;

	@Autowired
	public UsuariosController(UsuariosRepositorio usuarios) {
		this.usuarios = usuarios;
	}

	@PostMapping("/public/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario novoUsuario) {
		System.out.println(novoUsuario);
	    return new ResponseEntity(this.usuarios.saveAndFlush(novoUsuario), HttpStatus.CREATED);
    }

    @GetMapping("/public/usuarios/{cpf}")
	public ResponseEntity<?> getUsuario(@PathVariable String cpf) {
	    ResponseEntity response;
	    Usuario usuario = usuarios.findByCpf(cpf);

	    if (usuario == null) {
	        response = new ResponseEntity(HttpStatus.NO_CONTENT);

	    } else {
	        response = new ResponseEntity(usuario, HttpStatus.OK);
        }


	    return response;
    }

    @GetMapping("/public/usuarios/")
	public List<Usuario> getUsuarios() {
		return usuarios.findAll();
	}



}
