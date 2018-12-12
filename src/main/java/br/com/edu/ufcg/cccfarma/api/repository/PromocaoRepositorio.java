package br.com.edu.ufcg.cccfarma.api.repository;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;

public interface PromocaoRepositorio extends JpaRepository<Promocao, Long>{

	List<Promocao> findByTipoProdutoAndOcorrendoTrue(TipoProduto tipo);

	List<Promocao> findByOcorrendoTrue();
	
}
