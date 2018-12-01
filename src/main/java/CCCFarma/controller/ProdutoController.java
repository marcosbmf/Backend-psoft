package CCCFarma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CCCFarma.repository.ProdutosRepositorio;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	
	@Autowired
	private ProdutosRepositorio produtos;
}
