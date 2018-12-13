package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.response.ProdutoResponse;
import br.com.edu.ufcg.cccfarma.api.service.ProdutoService;
import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProdutoController {

	public static final String PATH = "/produtos";
	public static final String PRODUTO_ID = "produtoId";

	@Autowired
	private ProdutoService produtos;

	@GetMapping("/public/produtos")
	public List<ProdutoResponse> listaProdutos() {
		return this.produtos.getProdutos();
	}

	@GetMapping("/public/produtos/search")
	public List<ProdutoResponse> produtoPorTipo(@RequestParam(name = "tipo", required = false) TipoProduto tipo) {
		return this.produtos.getProdutosPorTipo(tipo);
	}

	@PutMapping("/public/produtos")
	public Produto cadastraProduto(@RequestBody Produto produto) {
		return this.produtos.cadastraProduto(produto);
	}

	@GetMapping("/public/produtos/{produtoId}")
	public ProdutoResponse getProduto(@PathVariable("produtoId") String produtoId) {
		return this.produtos.getProduto(produtoId);
	}

	@PostMapping("/admin/produtos/{produtoId}")
    public Produto updateProduto(@RequestBody Produto produto, @PathVariable(PRODUTO_ID) String produtoId) {
		return this.produtos.updateProduto(produto, produtoId);
	}

	@DeleteMapping("admin/produtos/{produtoId}")
	public Produto updateProduto(@PathVariable(PRODUTO_ID) String produtoId) {
		Produto produto = this.produtos.getProduto(produtoId).getProduto();
		this.produtos.deleteProduto(produtoId);
		return produto;
	}
}
