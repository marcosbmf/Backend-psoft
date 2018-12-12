package br.com.edu.ufcg.cccfarma.api.promocao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/promocoes")
public class PromocaoController {
	
	@Autowired
	private PromocaoService promocoes;
	
	@GetMapping
	public List<Promocao> getPromocoes(){
		return promocoes.getPromocoes();
	}
	
	@PutMapping
	public Promocao putPromocoes(@RequestBody Promocao promo){
		return this.promocoes.save(promo);
	}
	/**
	@DeleteMapping(path = "/{promocaoId}")
	public Promocao deletePromocoes(@PathVariable("promocaoId") String id){
		return null;
	}
	**/
}
