package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.ufcg.cccfarma.api.model.Promocao;
import br.com.edu.ufcg.cccfarma.api.service.PromocaoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/promocoes")
public class PromocaoController {

	@Autowired
	private PromocaoService promocoes;

	@GetMapping
	public List<Promocao> exibePromocoes() {
		return promocoes.getPromocoes();
	}

	@PostMapping
	public Promocao cadastraPromocao(@RequestBody Promocao promo) {
		return this.promocoes.cadastraPromocao(promo);
	}
	/**
	 * @DeleteMapping(path = "/{promocaoId}") public Promocao
	 *                     deletePromocoes(@PathVariable("promocaoId") String id){
	 *                     return null; }
	 **/
}
