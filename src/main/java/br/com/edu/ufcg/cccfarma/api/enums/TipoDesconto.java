package br.com.edu.ufcg.cccfarma.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDesconto {
	BOM_DESCONTO("bom desconto"),
	OTIMO_DESCONTO("otimo desconto"),
	SUPER_DESCONTO("super desconto");

	String tipo;
	
	TipoDesconto(){
	}
	
	private TipoDesconto(String tipo) {
		this.tipo = tipo;
	}
	
	@JsonValue
	public String getTipo() {
		return tipo;
	}
	
	
}
