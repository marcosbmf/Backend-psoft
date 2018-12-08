package br.com.edu.ufcg.cccfarma.api.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepositorio extends JpaRepository<Produto, String> {
	
	public List<Produto> findByTipo(TipoProduto tipo);
	
	public Produto findByCodBarra(String codBarra);

}
