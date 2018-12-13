package br.com.edu.ufcg.cccfarma.api.controller;

import br.com.edu.ufcg.cccfarma.api.repository.UsuariosRepositorio;
import br.com.edu.ufcg.cccfarma.api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@RestController
public class UsuariosController {

	@Autowired
	private UsuariosRepositorio usuarios;

	@PostMapping("/public/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario novoUsuario,
									   @AuthenticationPrincipal UserDetails userDetails) {
		System.out.println(userDetails);
	    usuarios.saveAndFlush(novoUsuario);
	    return new ResponseEntity(novoUsuario, HttpStatus.CREATED);
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
	public ResponseEntity<?> getUsuarios() {
		return new ResponseEntity(usuarios.findAll(), HttpStatus.OK);
	}



}
