package CCCFarma.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CCCFarma.model.produto.Produto;
import CCCFarma.model.produto.TipoProduto;
import CCCFarma.repository.ProdutosRepositorio;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	
	@Autowired
	private ProdutosRepositorio produtos;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> listaProdutos() {
		return produtos.findAll();
	}

	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Produto> produtoPorTipo(@RequestParam("tipo") String tipo) {
		TipoProduto tipoBusca = null;
		for (TipoProduto t: TipoProduto.values()) {
			if (t.getTipo().equals(tipo.toLowerCase())) {
				tipoBusca = t;
			}
		}
		return produtos.findByTipo(tipoBusca);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void cadastraProduto(@RequestBody Produto produto) {
		produtos.save(produto);
	}
}
