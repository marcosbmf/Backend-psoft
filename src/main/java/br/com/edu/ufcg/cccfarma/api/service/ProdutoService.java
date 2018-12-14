package br.com.edu.ufcg.cccfarma.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.response.ProdutoResponse;
import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import br.com.edu.ufcg.cccfarma.api.repository.ProdutosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.model.Lote;
import br.com.edu.ufcg.cccfarma.api.model.Promocao;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutosRepositorio produtos;
	
	@Autowired
	private PromocaoService promocoes;
	
	public List<ProdutoResponse>  exibeProdutos(){
		return this.makeResponse(this.produtos.findAll());
	}
	
	public List<ProdutoResponse> exibeProdutosPorTipo(TipoProduto tipo) {
		return makeResponse(produtos.findByTipo(tipo));
	}
	
	public Produto cadastraProduto(Produto produto) {
		if (produtos.existsById(produto.getCodBarra()))
			throw new ResourceAccessException("Produto com o mesmo id já existe!");
		return this.produtos.saveAndFlush(produto);
	}
	
	public ProdutoResponse exibeProduto(String produtoId) {
		return makeResponse(this.produtos.findById(produtoId).get());
	}
	
	public Produto atualizaProduto(Produto produto, String produtoId) {
		if (!produto.getCodBarra().equals(produtoId)) {
			throw new ResourceAccessException("Update de produto inválido.");
		}
		return this.produtos.saveAndFlush(produto);
	}

	public void deletaProduto(String produtoId) {
		this.produtos.deleteById(produtoId);
	}

	public List<Lote> getLote(String produtoId) {
		return this.produtos.getOne(produtoId).getLotes();
	}

	public List<ProdutoResponse> makeResponse(List<Produto> produtos) {
		Map<TipoProduto, Promocao> mapaPromo = this.promocoes.getMelhorPromocaoPorTipo();
		List<ProdutoResponse> resposta = new ArrayList<>();
		
		for(Produto p: produtos) {
			resposta.add(new ProdutoResponse(p, mapaPromo.get(p.getTipo())));
		}
		
		return resposta;
		
	}
	
	public ProdutoResponse makeResponse(Produto produto) {
		Promocao promocao = this.promocoes.getMelhorPromocaoPorTipo(produto.getTipo());
		return new ProdutoResponse(produto, promocao);
		
	}
	
	

}
