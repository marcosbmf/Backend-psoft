package CCCFarma.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CCCFarma.model.produto.Produto;
import CCCFarma.model.produto.TipoProduto;

public interface ProdutosRepositorio extends JpaRepository<Produto, String> {
	
	public List<Produto> findByTipo(TipoProduto tipo);
	
	public Produto findByCodBarra(String codBarra);

}
