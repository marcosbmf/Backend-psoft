package br.com.edu.ufcg.cccfarma.api.pedido;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

public class PedidoRequest {
	
	private Produto produto;
	private Integer quantidade;
	
	public PedidoRequest() {
	}

	public PedidoRequest(Produto produto, Integer quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	
	
}
