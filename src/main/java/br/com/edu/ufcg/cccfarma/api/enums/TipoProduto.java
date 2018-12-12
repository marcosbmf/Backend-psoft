package br.com.edu.ufcg.cccfarma.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoProduto {

		Medicamento("medicamento"),
		HigienePessoal("higiene"),
		Cosmeticos("cosmetico"),
		Alimento("alimento");
	
		private String tipo;
	
		TipoProduto() {
		}
		
		private TipoProduto(String tipo) {
			this.tipo = tipo.toLowerCase();
		}
		
		@JsonValue
		public String getTipo() {
			return this.tipo;
		}
}
