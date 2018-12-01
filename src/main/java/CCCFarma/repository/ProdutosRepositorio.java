package CCCFarma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CCCFarma.model.Produto;

public interface ProdutosRepositorio extends JpaRepository<Produto, String> {

}
