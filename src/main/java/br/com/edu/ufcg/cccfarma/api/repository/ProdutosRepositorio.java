package br.com.edu.ufcg.cccfarma.api.repository;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepositorio extends JpaRepository<Produto, String> {
	
	public List<Produto> findByTipo(TipoProduto tipo);
	
	public Produto findByCodBarra(String codBarra);

}
