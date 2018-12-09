package br.com.edu.ufcg.cccfarma.api.produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.lote.Lote;
import br.com.edu.ufcg.cccfarma.api.promocao.Promocao;
import br.com.edu.ufcg.cccfarma.api.promocao.PromocaoService;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutosRepositorio produtos;
	
	@Autowired
	private PromocaoService promocoes;
	
	public List<ProdutoResponse>  getProdutos(){
		return this.makeResponse(this.produtos.findAll());
	}
	
	public List<ProdutoResponse> getProdutosPorTipo(TipoProduto tipo) {
		return makeResponse(produtos.findByTipo(tipo));
	}
	
	public Produto cadastraProduto(Produto produto) {
		if (produtos.findByCodBarra(produto.getCodBarra()) != null)
			throw new ResourceAccessException("Produto com o mesmo id já existe!");
		return this.produtos.saveAndFlush(produto);
	}
	
	public ProdutoResponse getProduto(String produtoId) {
		return makeResponse(this.produtos.findById(produtoId).get());
	}
	
	public Produto updateProduto(Produto produto, String produtoId) {
		if (!produto.getCodBarra().equals(produtoId)) {
			throw new ResourceAccessException("Update de produto inválido.");
		}
		return this.produtos.saveAndFlush(produto);
	}

	public void deleteProduto(String produtoId) {
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
