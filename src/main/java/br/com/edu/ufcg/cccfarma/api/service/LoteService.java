package br.com.edu.ufcg.cccfarma.api.service;

import java.util.List;

import javax.transaction.Transactional;

import br.com.edu.ufcg.cccfarma.api.model.Lote;
import br.com.edu.ufcg.cccfarma.api.repository.LoteRepositorio;
import br.com.edu.ufcg.cccfarma.api.repository.ProdutosRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.model.Produto;

@Service
@Transactional
public class LoteService {

	@Autowired
	private LoteRepositorio lotes;
	@Autowired
	private ProdutosRepositorio produtos;

	public List<Lote> listaLotes() {
		return this.lotes.findAll();
	}

	public List<Lote> listaLotesPorProduto(String produtoId) {
		return lotes.findByProdutoCodBarra(produtoId);
	}

	public Lote cadastraLote(Lote lote) {
		if (this.produtos.existsById(lote.getProduto().getCodBarra())) {
			return lotes.saveAndFlush(lote);
		} else {
			throw new IllegalArgumentException("Produto não existe!");
		}
	}

	public int getQuantidadeDisponivel(Produto produto) {
		List<Lote> lotesProduto = this.lotes.findByProduto(produto);
		int qtdDisponivel = 0;
		for (Lote lote : lotesProduto) {
			qtdDisponivel += lote.getQuantidadeInicial() - lote.getQuantidadeVendida();
		}
		return qtdDisponivel;
	}

	public Lote updateLote(Lote lote, Integer loteId, String produtoId) {
		if (!lote.getNumeroLote().equals(loteId) || !lote.getProduto().getCodBarra().equals(produtoId))
			throw new ResourceAccessException("Erro ao realizar atualização do produto! Número do lote ou id do produto inválido!");
		return this.lotes.saveAndFlush(lote);
	}

	public void deletaLote(Integer loteId, String produtoId) {
		Lote lote = this.lotes.getOne(loteId);
		if (lote == null || !lote.getNumeroLote().equals(loteId) || !lote.getProduto().getCodBarra().equals(produtoId))
			throw new ResourceAccessException("Erro ao realizar atualização do produto!");
		if (lote.getQuantidadeVendida() != 0) {
			throw new ResourceAccessException("Erro ao deletar lote: há produtos vendidos, impossível deletar lote.");
		}
		this.lotes.deleteById(loteId);
	}

	public Lote exibeLote(String produtoId, Integer loteId) {
		return this.lotes.getLoteByNumeroLoteAndProdutoCodBarra(loteId, produtoId);
	}
}
