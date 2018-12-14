package br.com.edu.ufcg.cccfarma.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoDesconto {
	BOM_DESCONTO("Bom desconto"),
	OTIMO_DESCONTO("Ótimo desconto"),
	SUPER_DESCONTO("Super desconto");

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
