package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.service.LoteService;
import br.com.edu.ufcg.cccfarma.api.model.Lote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/produtos/{produtoId}/lote")
public class LoteController {

	@Autowired
	private LoteService lotes;
	
	@GetMapping
	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		return lotes.listaLotesPorProduto(produtoId);
	}
	
	@PutMapping
	public Lote salvaLote(@RequestBody Lote lote) {
		return lotes.salvaLote(lote);
	}
	
	@DeleteMapping("/{loteId}")
	public void deleteLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		lotes.deleteLote(loteId, produtoId);
	}
	
	@GetMapping("/{loteId}")
	public Lote getLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		return lotes.getLote(produtoId, loteId);
	}
}
