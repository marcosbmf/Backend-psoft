package br.com.edu.ufcg.cccfarma.api.pedido;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoPedido {
	ENTREGUE("entregue"), NAO_ENTREGUE("nao_entregue"), CANCELADO("cancelado");
	
	private String situacao;
	
	SituacaoPedido() {
	}
	
	
	private SituacaoPedido(String situacao){
		this.situacao = situacao;
	}
	
	@JsonValue
	public String getSituacao() {
		return situacao;
	}
}
