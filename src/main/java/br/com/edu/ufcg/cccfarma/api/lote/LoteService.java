package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

@Service
@Transactional
public class LoteService {
	
	@Autowired
	private LoteRepositorio lotes;
	
	public List<Lote> listaLotes(){
		return this.lotes.findAll();
	}

	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		System.out.println(produtoId);
		return lotes.findByProdutoCodBarra(produtoId);
	}
	
	public Lote salvaLote(@RequestBody Lote lote) {
		return lotes.saveAndFlush(lote);
	}
	
	public int getQuantidadeDisponivel(Produto produto) {
		List<Lote> lotesProduto = this.lotes.findByProduto(produto);
		int qtdDisponivel = 0;
		for(Lote lote : lotesProduto) {
			qtdDisponivel += lote.getQuantidadeInicial() - lote.getQuantidadeVendida();
		}
		return qtdDisponivel;
	}
}
