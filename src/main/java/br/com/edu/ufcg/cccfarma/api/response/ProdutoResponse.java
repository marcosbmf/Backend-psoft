package br.com.edu.ufcg.cccfarma.api.response;

import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.model.Promocao;

public class ProdutoResponse {

	private Produto produto;
	private Double taxaDesconto;
	private Double precoPromocional;
	private Integer quantidadeDisponivel;
	
	public ProdutoResponse(Produto produto, Promocao promocaoDisponivel) {
		this.produto = produto;
		this.quantidadeDisponivel = this.produto.quantidadeDisponivel();
		if (promocaoDisponivel != null) {
			this.precoPromocional = this.produto.getPreco()*(1-promocaoDisponivel.getTaxaDesconto());
			this.taxaDesconto = promocaoDisponivel.getTaxaDesconto();
		} else {
			this.precoPromocional = this.getProduto().getPreco();
			this.taxaDesconto = 0.00;
		}
	}

	public Produto getProduto() {
		return produto;
	}

	public Double getTaxaDesconto() {
		return taxaDesconto;
	}

	public Double getPrecoPromocional() {
		return precoPromocional;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	
}
