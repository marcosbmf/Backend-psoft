package br.com.edu.ufcg.cccfarma.api.promocao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edu.ufcg.cccfarma.api.produto.TipoProduto;

public interface PromocaoRepositorio extends JpaRepository<Promocao, Long>{

	List<Promocao> findByTipoProdutoAndOcorrendoTrue(TipoProduto tipo);

	List<Promocao> findByOcorrendoTrue();
	
}
