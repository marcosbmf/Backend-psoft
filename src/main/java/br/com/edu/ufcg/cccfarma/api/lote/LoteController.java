package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos/{produtoId}/lote")
public class LoteController {

	@Autowired
	private LoteService lotes;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		return lotes.listaLotesPorProduto(produtoId);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Lote salvaLote(@RequestBody Lote lote) {
		return lotes.salvaLote(lote);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{loteId}")
	public void deleteLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		lotes.deleteLote(loteId, produtoId);
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{loteId}")
	public Lote getLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		return lotes.getLote(produtoId, loteId);
	}
}
