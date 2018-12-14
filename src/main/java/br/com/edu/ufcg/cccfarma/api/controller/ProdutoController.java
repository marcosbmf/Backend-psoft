package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.response.ProdutoResponse;
import br.com.edu.ufcg.cccfarma.api.service.ProdutoService;

@RestController
@CrossOrigin("*")
public class ProdutoController {

	public static final String PATH = "/produtos";
	public static final String PRODUTO_ID = "produtoId";

	@Autowired
	private ProdutoService produtos;

	@GetMapping("/public/produtos")
	public List<ProdutoResponse> exibeProdutos() {
		return this.produtos.exibeProdutos();
	}

	@GetMapping("/public/produtos/search")
	public List<ProdutoResponse> exibeProdutoPorTipo(@RequestParam(name = "tipo", required = false) TipoProduto tipo) {
		return this.produtos.exibeProdutosPorTipo(tipo);
	}

	@GetMapping("/public/produtos/{produtoId}")
	public ProdutoResponse exibeProduto(@PathVariable("produtoId") String produtoId) {
		return this.produtos.exibeProduto(produtoId);
	}

	@PostMapping("/admin/produtos")
	public Produto cadastraProduto(@RequestBody Produto produto) {
		return this.produtos.cadastraProduto(produto);
	}
	
	@PutMapping("/admin/produtos/{produtoId}")
    public Produto atualizaProduto(@RequestBody Produto produto, @PathVariable(PRODUTO_ID) String produtoId) {
		return this.produtos.atualizaProduto(produto, produtoId);
	}

	@DeleteMapping("admin/produtos/{produtoId}")
	public Produto deletaProduto(@PathVariable(PRODUTO_ID) String produtoId) {
		Produto produto = this.produtos.exibeProduto(produtoId).getProduto();
		this.produtos.deletaProduto(produtoId);
		return produto;
	}
}
