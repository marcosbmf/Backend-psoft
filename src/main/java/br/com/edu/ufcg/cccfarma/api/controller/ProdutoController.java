package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.response.ProdutoResponse;
import br.com.edu.ufcg.cccfarma.api.service.ProdutoService;
import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(ProdutoController.PATH)
public class ProdutoController {

	public static final String PATH = "/produtos";
	public static final String PRODUTO_ID = "produtoId";

	@Autowired
	private ProdutoService produtos;

	@RequestMapping(method = RequestMethod.GET)
	public List<ProdutoResponse> listaProdutos() {
		return this.produtos.getProdutos();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<ProdutoResponse> produtoPorTipo(@RequestParam(name = "tipo", required = false) TipoProduto tipo) {
		return this.produtos.getProdutosPorTipo(tipo);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Produto cadastraProduto(@RequestBody Produto produto) {
		return this.produtos.cadastraProduto(produto);
	}

	@RequestMapping(path = "/{produtoId}", method = RequestMethod.GET)
	public ProdutoResponse getProduto(@PathVariable("produtoId") String produtoId) {
		return this.produtos.getProduto(produtoId);
	}

	@RequestMapping(path = "/{produtoId}", method = RequestMethod.POST)
	public Produto updateProduto(@RequestBody Produto produto, @PathVariable(PRODUTO_ID) String produtoId) {
		return this.produtos.updateProduto(produto, produtoId);
	}

	@RequestMapping(path = "/{produtoId}", method = RequestMethod.DELETE)
	public Produto updateProduto(@PathVariable(PRODUTO_ID) String produtoId) {
		Produto produto = this.produtos.getProduto(produtoId).getProduto();
		this.produtos.deleteProduto(produtoId);
		return produto;
	}
}
