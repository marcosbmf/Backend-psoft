package br.com.edu.ufcg.cccfarma.api.controller;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    private ContaRepository contaRepository;

    @Autowired
    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping("/public/conta")
    public ResponseEntity<?> signup(@RequestBody Conta conta) {
        return new ResponseEntity(contaRepository.save(conta), HttpStatus.CREATED);
    }


    @GetMapping("/admin/conta")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(contaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/admin/conta_public")
    public ResponseEntity<?> getAllPublic() {
        return new ResponseEntity<>(contaRepository.findAll(), HttpStatus.OK);
    }
}
