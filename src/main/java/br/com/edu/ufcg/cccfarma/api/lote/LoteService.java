package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

@Service
@Transactional
public class LoteService {
	
	@Autowired
	private LoteRepositorio lotes;
	
	public List<Lote> listaLotes(){
		return this.lotes.findAll();
	}

	public List<Lote> listaLotesPorProduto(String produtoId) {
		System.out.println(produtoId);
		return lotes.findByProdutoCodBarra(produtoId);
	}
	
	public Lote salvaLote(Lote lote) {
		System.out.println(this.lotes.findById(lote.getNumeroLote()));
		if(this.lotes.findByNumeroLote(lote.getNumeroLote()) != null)
			throw new ResourceAccessException("Produto com o mesmo id já existe!");
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
	
	public Lote updateLote(Lote lote, String produtoId) {
		if(!lote.getNumeroLote().equals(produtoId))
			throw new ResourceAccessException("Erro ao realizar atualização do produto!");
		return this.lotes.saveAndFlush(lote);
	}
}
