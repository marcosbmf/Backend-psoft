package br.com.edu.ufcg.cccfarma.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoPedido {
	ENTREGUE("Entregue"), NAO_ENTREGUE("Não entregue"), CANCELADO("Cancelado");
	
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
