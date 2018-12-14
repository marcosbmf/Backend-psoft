package br.com.edu.ufcg.cccfarma.api.controller;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ContaController {

    private ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/public/conta")
    public ResponseEntity<?> cadastrar(@RequestBody Conta conta) {
        return contaService.cadastrar(conta);
    }

    @PutMapping("/protected/conta")
    public ResponseEntity<?> atualizar(@RequestBody Conta conta) {
        System.out.println(conta.getUsername() + " - " + conta.getPassword());
        return contaService.atualizar(conta);
    }

    //
//    @DeleteMapping("/admin/conta")
//    public ResponseEntity<?> excluir(@RequestBody Conta conta) {
//        return contaService.excluir(conta);
//    }
//
//    @GetMapping("/admin/conta")
//    public ResponseEntity<?> exibirTodos() {
//        return contaService.exibirTodos();
//    }
//
    @GetMapping("/protected/conta")
    public ResponseEntity<?> exibirPerfil() {
        return contaService.exibirPerfil();
//    }
//
//    @GetMapping("/admin/conta/{username}")
//    public ResponseEntity<?> exibirPerfil(@PathVariable String username) {
//        return contaService.exibirPerfil(username);
//    }

    }
}
