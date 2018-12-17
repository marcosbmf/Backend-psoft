package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;

@Entity
public class Produto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4509030253600549069L;

	@NotNull
	@Id
	@Column(name="cod_barra")
	private String codBarra;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String fabricante;
	
	@NotNull
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoProduto tipo;
	
	@NotNull
	private Double preco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "produto", targetEntity = Lote.class)
	private List<Lote> lotes;
	
	public Produto() {
		this.lotes = new ArrayList<Lote>();
	}

	public Produto(@NotNull String nome, @NotNull String codBarra, @NotNull String fabricante,
			@NotNull TipoProduto tipo, @NotNull double preco, @NotNull String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.codBarra = codBarra;
		this.fabricante = fabricante;
		this.tipo = tipo;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getNome() {
		return nome;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public String getFabricante() {
		return fabricante;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codBarra == null) ? 0 : codBarra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codBarra == null) {
			if (other.codBarra != null)
				return false;
		} else if (!codBarra.equals(other.codBarra))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return this.codBarra + " - " + this.nome + " - " + this.fabricante + " - " + this.descricao;
	}

	public List<Lote> getLotes() {
		return this.lotes;
	}
	
	@Transient
	public boolean getVencimentoProximo() {
		for (Lote lote : this.lotes) {
			if (lote.getVencimentoProximo())
				return true;
		}
		return false;
	}
	
	@Transient
	public Integer quantidadeDisponivel() {
		Integer qtdDisponivel = 0;
		for (Lote l : this.lotes) {
			qtdDisponivel += l.getQuantidadeDisponivel();
		}
		return qtdDisponivel;
	}

	
	
	
}
