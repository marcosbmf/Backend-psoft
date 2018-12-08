package br.com.edu.ufcg.cccfarma.api.lote;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroLote")
@Entity
public class Lote {
	
	@Id
	@NotNull
	@Column(name = "numero_lote")
	private String numeroLote;
	
	@NotNull
	@JoinColumn(name = "cod_barra")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Produto produto;

	@NotNull
	@FutureOrPresent
	@Temporal(TemporalType.DATE)
	@Column(name="data_validade", columnDefinition = "Date")
	private Date dataValidade;
	
	@NotNull
	@Positive
	@Column(name="quantidade_inicial")
	private int quantidadeInicial;
	
	@NotNull
	@Column(name="quantidade_vendida")
	private int quantidadeVendida;
	
	Lote(){
		this.quantidadeVendida = 0;
	}

	public Lote(@NotNull String numeroLote, @NotNull Produto produto, @NotNull @Future Date dataValidade,
			@NotNull @Positive int quantidadeInicial) {
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

	public boolean naValidade() {
		return (this.dataValidade.compareTo(java.util.Calendar.getInstance().getTime()) >=  0);
	}

	
	
	
	
	
	
	
	
	
	
}
