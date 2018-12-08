package br.com.edu.ufcg.cccfarma.api.produto;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.lote.Lote;
import br.com.edu.ufcg.cccfarma.api.lote.LoteRepositorio;
import br.com.edu.ufcg.cccfarma.api.produto.Produto;
import br.com.edu.ufcg.cccfarma.api.produto.ProdutoResponse;
import br.com.edu.ufcg.cccfarma.api.produto.TipoProduto;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutosRepositorio produtos;
	@Autowired
	private LoteRepositorio lotes;
	
	public List<Produto> getProdutos(){
		return (List<Produto>) this.produtos.findAll();
	}
	
	public List<Produto> getProdutosPorTipo(TipoProduto tipo) {
		return produtos.findByTipo(tipo);
	}
	
	public Produto cadastraProduto(Produto produto) {
		if (produtos.findByCodBarra(produto.getCodBarra()) != null)
			throw new ResourceAccessException("Produto com o mesmo id já existe!");
		return this.produtos.saveAndFlush(produto);
	}
	
	public Produto getProduto(String produtoId) {
		return this.produtos.findByCodBarra(produtoId);
	}
	
	public Produto updateProduto(Produto produto, String produtoId) {
		if (produto.getCodBarra() != produtoId) {
			throw new ResourceAccessException("Update de produto inválido.");
		}
		return this.produtos.saveAndFlush(produto);
	}
	
	public List<ProdutoResponse> makeProdutoResponse(List<Produto> produtos) {
		List<ProdutoResponse> response = new ArrayList<>();
		for (Produto p : produtos) {
			List<Lote> lotesProduto = this.lotes.findByProduto(p);
			int qtdDisponivel = 0;
			for(Lote lote : lotesProduto) {
				qtdDisponivel += lote.getQuantidadeInicial() - lote.getQuantidadeVendida();
			}
			response.add(new ProdutoResponse(p, qtdDisponivel));
		}
		
		return response;
	}
	
	

}
