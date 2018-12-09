package br.com.edu.ufcg.cccfarma.api.lote;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.edu.ufcg.cccfarma.api.produto.Produto;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroLote")
@Entity
public class Lote implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "numero_lote")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numeroLote;
	
	@NotNull
	@JoinColumn(name = "cod_barra", updatable=false)
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name="data_validade", columnDefinition = "Date", updatable=false)
	private Date dataValidade;
	
	@NotNull
	@Positive
	@Column(name="quantidade_inicial", updatable=false)
	private int quantidadeInicial;
	
	@NotNull
	@Column(name="quantidade_vendida")
	private int quantidadeVendida;
	
	Lote(){
		this.quantidadeVendida = 0;
	}

	public Lote(@NotNull Produto produto, @NotNull @Future Date dataValidade,
			@NotNull @Positive int quantidadeInicial) {
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

	public Long getNumeroLote() {
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
