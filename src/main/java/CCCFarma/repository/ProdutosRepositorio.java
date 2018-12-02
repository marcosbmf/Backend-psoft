package CCCFarma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CCCFarma.model.produto.Produto;
import CCCFarma.model.produto.TipoProduto;

public interface ProdutosRepositorio extends JpaRepository<Produto, String> {
	
	public List<Produto> findByTipo(TipoProduto tipo);

}
