package CCCFarma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import CCCFarma.model.produto.Lote;
import CCCFarma.model.produto.Produto;
import CCCFarma.model.produto.ProdutoResponse;
import CCCFarma.model.produto.TipoProduto;
import CCCFarma.repository.LoteRepositorio;
import CCCFarma.repository.ProdutosRepositorio;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	
	@Autowired
	private ProdutosRepositorio produtos;
	@Autowired
	private LoteRepositorio lotes;
	
	
	private List<Produto> getProdutos() {
		return produtos.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ProdutoResponse> listaProdutos(){
		List<ProdutoResponse> lista = new ArrayList<>();
		for (Produto p : this.getProdutos()) {
			List<Lote> lotesProduto = this.lotes.findByProduto(p);
			int qtdDisponivel = 0;
			for(Lote lote : lotesProduto) {
				qtdDisponivel += lote.getQuantidadeInicial() - lote.getQuantidadeVendida();
			}
			lista.add(new ProdutoResponse(p, qtdDisponivel));
		}
		return lista;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public List<Produto> produtoPorTipo(@RequestParam(name = "tipo", required = false) TipoProduto tipo) {
		return produtos.findByTipo(tipo);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void cadastraProduto(@RequestBody Produto produto) {
		produtos.save(produto);
	}
}

