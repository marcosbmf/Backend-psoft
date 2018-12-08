package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

@Entity
public class Lote {
	
	@Id
	@NotNull
	@Column(name = "numero_lote")
	private String numeroLote;
	
	@NotNull
	@JoinColumn(name = "cod_barra")
	@ManyToOne
	private Produto produto;

	@NotNull
	@Future
	@Temporal(TemporalType.DATE)
	@Column(name="data_validade")
	private Date dataValidade;
	
	@NotNull
	@Positive
	@Column(name="quantidade_inicial")
	private int quantidadeInicial;
	
	@Generated(value = GenerationTime.INSERT)
	@Column(name="quantidade_vendida")
	private int quantidadeVendida;
	
	Lote(){
		this.quantidadeVendida = 0;
	}

	public Lote(@NotNull String numeroLote, @NotNull Produto produto, @NotNull @Future Date dataValidade,
			@NotNull @Positive int quantidadeInicial) {
		super();
		this.numeroLote = numeroLote;
		this.produto = produto;
		this.dataValidade = dataValidade;
		this.quantidadeInicial = quantidadeInicial;
		this.quantidadeVendida = 0;
	}
	
	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public Produto getProduto() {
		return produto;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public int getQuantidadeInicial() {
		return quantidadeInicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroLote == null) ? 0 : numeroLote.hashCode());
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
		Lote other = (Lote) obj;
		if (numeroLote == null) {
			if (other.numeroLote != null)
				return false;
		} else if (!numeroLote.equals(other.numeroLote))
			return false;
		return true;
	}

	
	
	
	
	
	
	
	
	
	
}