package CCCFarma.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CCCFarma.model.produto.Lote;
import CCCFarma.model.produto.Produto;
import CCCFarma.repository.LoteRepositorio;

@RestController
@RequestMapping("/lotes")
public class LoteController {

	@Autowired
	private LoteRepositorio lotes;
	
	@GetMapping
	public List<Lote> listaLotes(){
		return this.lotes.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value="/{produtoId}")
	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		System.out.println(produtoId);
		return lotes.findByProdutoCodBarra(produtoId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void salvaLote(@RequestBody Lote lote) {
		lotes.save(lote);
	}
	
}
