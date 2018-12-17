package br.com.edu.ufcg.cccfarma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.service.ContaService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
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
