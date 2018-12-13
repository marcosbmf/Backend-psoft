package br.com.edu.ufcg.cccfarma.api.repository;

import br.com.edu.ufcg.cccfarma.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuario, String> {

    Usuario findByCpf(String cpf);

}
