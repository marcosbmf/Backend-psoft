package CCCFarma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CCCFarma.model.produto.Lote;
import CCCFarma.model.produto.Produto;

public interface LoteRepositorio extends JpaRepository<Lote, String>{

	List<Lote> findByProduto(Produto produto);

	List<Lote> findByProdutoCodBarra(String codBarra);
	
	
}
