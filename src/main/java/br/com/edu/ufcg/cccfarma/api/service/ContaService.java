package br.com.edu.ufcg.cccfarma.api.service;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ContaService {

    private ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ResponseEntity<?> cadastrar(Conta conta) {
        ResponseEntity response = new ResponseEntity<>(
                new RuntimeException("Usuário já cadastrado. Talvez você quisesse atualizá-lo?"),
                HttpStatus.FORBIDDEN);

        if (conta.getUsername().isEmpty() || conta.getPassword().isEmpty()) {
            response = new ResponseEntity<>(
                    new RuntimeException("Usuário e senha não podem ser vazios."),
                    HttpStatus.BAD_REQUEST);

        } else if (!contaRepository.existsById(conta.getUsername())) {
            response = new ResponseEntity<>(
                    contaRepository.save(conta),
                    HttpStatus.CREATED);
        }


        return response;
    }

    public ResponseEntity<?> atualizar(Conta conta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        ResponseEntity response = new ResponseEntity<>("Usuário não existe", HttpStatus.NOT_FOUND);

        if (contaRepository.existsById(conta.getUsername())) {
            if (user.equals(conta.getUsername()) || contaRepository.findById(user).get().isAdmin()) {
                if (!conta.getPassword().isEmpty()) {
                    conta.setPassword();
                    response = new ResponseEntity<>(contaRepository.save(conta), HttpStatus.OK);

                } else {
                    response = new ResponseEntity<>("Senha não pode estar vazia.", HttpStatus.OK);
                }

            } else {
                response = new ResponseEntity<>("Quem você é pensa que é para sair mudando as senhas alheias?",
                        HttpStatus.FORBIDDEN);
            }
        }

        return response;
    }

    public ResponseEntity<?> exibirPerfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return new ResponseEntity<>(contaRepository.findById(user), HttpStatus.OK);
    }



    public ResponseEntity<?> exibirPerfil(String username) {
        ResponseEntity response = new ResponseEntity<>("Usuário não existe.", HttpStatus.NOT_FOUND);

        if (contaRepository.existsById(username)) {
            response = new ResponseEntity<>(contaRepository.findById(username), HttpStatus.OK);
        }

        return response;
    }
//
//    public ResponseEntity<?> exibirTodos() {
//        return new ResponseEntity<>(contaRepository.findAll(), HttpStatus.OK);
//    }
//
//    public ResponseEntity<?> excluir(Conta conta) {
//        ResponseEntity response = new ResponseEntity<>(
//                new RuntimeException("Usuário não existe. Talvez você quisesse criá-lo?"),
//                HttpStatus.NOT_FOUND);
//
//        if (contaRepository.existsById(conta.getUsername())) {
//            contaRepository.save(conta);
//
//            contaRepository.deleteById(conta.getUsername());
//            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return response;
//    }
}
