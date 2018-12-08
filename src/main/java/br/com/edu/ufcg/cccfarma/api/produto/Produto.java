package br.com.edu.ufcg.cccfarma.api.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.edu.ufcg.cccfarma.api.lote.Lote;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codBarra")
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
	
	@OneToMany(mappedBy = "produto", targetEntity = br.com.edu.ufcg.cccfarma.api.lote.Lote.class, fetch = FetchType.LAZY)
	private List<Lote> lotes;
	
	@Transient
	private Integer qtdDisponivel;
	
	public Produto() {
		this.lotes = new ArrayList<>();
	}

	public Produto(@NotNull String nome, @NotNull String codBarra, @NotNull String fabricante,
			@NotNull TipoProduto tipo, @NotNull double preco, @NotNull String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.codBarra = codBarra;
		this.fabricante = fabricante;
		this.tipo = tipo;
		this.preco = preco;
		this.lotes = new ArrayList<>();
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
	public Integer getQtdDisponivel() {
		return this.qtdDisponivel;
	}
	
	@PostLoad
	public void postLoad() {
		int qtdDisponivel = 0;
		for (Lote l : this.lotes) {
			qtdDisponivel += l.getQuantidadeInicial() - l.getQuantidadeVendida();
		}
		this.qtdDisponivel = qtdDisponivel;
	}

	
	
	
}
