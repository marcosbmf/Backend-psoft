package br.com.edu.ufcg.cccfarma.api.controller;

import java.util.List;

import br.com.edu.ufcg.cccfarma.api.model.Pedido;
import br.com.edu.ufcg.cccfarma.api.requests.PedidoRequest;
import br.com.edu.ufcg.cccfarma.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/public/pedido")
public class PedidoController {

	
	@Autowired
	PedidoService pedidos;
	
	@GetMapping
	public List<Pedido> getPedidos(){
		return this.pedidos.getPedidos();
	}
	
	@PutMapping
	public Pedido putPedido(@RequestBody List<PedidoRequest> request, @AuthenticationPrincipal UserDetails userDetails){
		return this.pedidos.savePedido(request, userDetails);
	}
}
