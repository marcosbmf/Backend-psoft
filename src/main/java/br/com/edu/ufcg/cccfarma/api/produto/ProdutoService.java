package br.com.edu.ufcg.cccfarma.api.produto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import br.com.edu.ufcg.cccfarma.api.lote.Lote;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	private ProdutosRepositorio produtos;
	
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
		System.out.println("SERVICE" + produtoId);
		return this.produtos.findById(produtoId).get();
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
	
	

}
