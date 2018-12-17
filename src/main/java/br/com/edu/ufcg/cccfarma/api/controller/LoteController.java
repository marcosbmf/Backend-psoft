package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.ufcg.cccfarma.api.model.Lote;
import br.com.edu.ufcg.cccfarma.api.service.LoteService;

@RestController
@CrossOrigin("*")
public class LoteController {

	@Autowired
	private LoteService lotes;
	
	@GetMapping("/admin/produtos/{produtoId}/lotes")
	public List<Lote> listaLotesPorProduto(@PathVariable("produtoId") String produtoId) {
		if (produtoId.equals("-1")) {
			return this.lotes.listaLotes();
		} else {
			return lotes.listaLotesPorProduto(produtoId);
		}
	}

	@PostMapping("/admin/produtos/{produtoId}/lotes")
	public Lote cadastraLote(@RequestBody Lote lote) {
		return lotes.cadastraLote(lote);
	}

	@DeleteMapping("/admin/produtos/{produtoId}/lotes/{loteId}")
	public void deletaLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		lotes.deletaLote(loteId, produtoId);
	}

	@GetMapping("/admin/produtos/{produtoId}/lotes/{loteId}")
	public Lote exibeLote(@PathVariable("produtoId") String produtoId, @PathVariable("loteId") Integer loteId) {
		return lotes.exibeLote(produtoId, loteId);
	}
}
