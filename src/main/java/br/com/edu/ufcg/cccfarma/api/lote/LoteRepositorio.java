package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

public interface LoteRepositorio extends JpaRepository<Lote, Integer>{

	List<Lote> findByProduto(Produto produto);

	List<Lote> findByProdutoCodBarra(String codBarra);

	Lote findByNumeroLote(Integer long1);

	Lote getLoteByNumeroLoteAndProdutoCodBarra(Integer loteId, String produtoId);
	
	
}
