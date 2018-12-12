package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.repository.UsuariosRepositorio;
import br.com.edu.ufcg.cccfarma.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuariosController {

	@Autowired
	private UsuariosRepositorio usuarios;

	@PostMapping("/api/signup")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario novoUsuario) {
	    usuarios.save(novoUsuario);
	    return new ResponseEntity(novoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/{cpf}")
    public ResponseEntity<?> getUsuario(@PathVariable String cpf) {
	    ResponseEntity response;
	    Usuario usuario = usuarios.getOne(cpf);

	    if (usuario == null) {
	        response = new ResponseEntity(HttpStatus.NO_CONTENT);

	    } else {
	        response = new ResponseEntity(usuario, HttpStatus.OK);
        }


	    return response;
    }



}
