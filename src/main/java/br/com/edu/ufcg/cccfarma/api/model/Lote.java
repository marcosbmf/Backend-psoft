package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Lote implements Serializable, Comparable<Lote> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "numero_lote")
	private Integer numeroLote;

	@NotNull
	@JoinColumn(name = "cod_barra")
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dataValidade;

	@NotNull
	@Positive
	@Column(name = "quantidade_inicial", updatable = false)
	private int quantidadeInicial;

	@NotNull
	@Column(name = "quantidade_vendida")
	private int quantidadeVendida;

	Lote() {
		this.quantidadeVendida = 0;
		this.numeroLote = ThreadLocalRandom.current().nextInt(1000000);
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

	public Integer getNumeroLote() {
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
	
	@Transient
	public boolean getVencimentoProximo() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, +1);
		return (this.dataValidade.before(calendar.getTime()));
	}

	public Integer getQuantidadeDisponivel() {
		LocalDate ld = LocalDate.now();
		Date today = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		if (this.dataValidade.after(today)) {
			return this.quantidadeInicial - this.quantidadeVendida;
		} else {
			return 0;
		}
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

	@Override
	public int compareTo(Lote o) {
		int result = this.dataValidade.compareTo(o.dataValidade);
		if (result == 0) {
			result = this.getQuantidadeDisponivel().compareTo(o.getQuantidadeDisponivel());
		}
		return result;
	}

	public void realizaVenda(Integer qtdVendida) {
		this.quantidadeVendida += qtdVendida;

	}

}
