package br.com.edu.ufcg.cccfarma.api.produto;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProdutoController.PATH)
public class ProdutoController {
	
	public static final String PATH = "/produtos";
	public static final String PRODUTO_ID = "produtoId";
	

	@Autowired
	private ProdutoService produtos;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ProdutoResponse> listaProdutos(){
		return this.produtos.makeProdutoResponse(this.produtos.getProdutos());
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<ProdutoResponse> produtoPorTipo(@RequestParam(name = "tipo", required = false) TipoProduto tipo) {
		return this.produtos.makeProdutoResponse(this.produtos.getProdutosPorTipo(tipo));
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Produto cadastraProduto(@RequestBody Produto produto) {
		return this.produtos.cadastraProduto(produto);
	}
	
	@RequestMapping(path = "/{produtoId}", method = RequestMethod.GET)
	public ProdutoResponse getProduto(@PathParam("produtoId") String produtoId) {
		return new ProdutoResponse(this.produtos.getProduto(produtoId), 0);
	}
	
	@RequestMapping(path = "/{produtoId}", method = RequestMethod.POST)
	public Produto updateProduto(@RequestBody Produto produto, @PathParam(PRODUTO_ID) String produtoId) {
		return this.produtos.updateProduto(produto, produtoId);
	}
}

