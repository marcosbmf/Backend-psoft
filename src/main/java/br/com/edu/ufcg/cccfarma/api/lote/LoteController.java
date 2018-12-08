package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotes")
public class LoteController {

	@Autowired
	private LoteService lotes;
	
	@GetMapping
	public List<Lote> listaLotes(){
		return this.lotes.listaLotes();
	}

	@RequestMapping(method = RequestMethod.GET, value="/{produtoId}")
	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		return lotes.listaLotesPorProduto(produtoId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void salvaLote(@RequestBody Lote lote) {
		lotes.salvaLote(lote);
	}
	
}