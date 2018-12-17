package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.edu.ufcg.cccfarma.api.model.Pedido;
import br.com.edu.ufcg.cccfarma.api.requests.PedidoRequest;
import br.com.edu.ufcg.cccfarma.api.service.PedidoService;

@RestController
@CrossOrigin("*")
public class PedidoController {

	
	@Autowired
	PedidoService pedidos;
	
	@GetMapping("/protected/pedidos")
	public List<Pedido> exibePedidos(){
		return this.pedidos.getPedidos();
	}
	
	@PostMapping("/protected/pedidos")
	public Pedido cadastraPedido(@RequestBody List<PedidoRequest> request){
		return this.pedidos.savePedido(request);
	}
	
	@PutMapping("/protected/pedidos/{pedidoId}")
	public Pedido editaPedido(@RequestBody Pedido request, @PathVariable("pedidoId") Integer pedidoId){
		return this.pedidos.editaPedido(request, pedidoId);
	}
}
