package br.com.edu.ufcg.cccfarma.api.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import br.com.edu.ufcg.cccfarma.api.enums.TipoDesconto;

@Entity
public class Promocao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDesconto;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoProduto tipoProduto;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoDesconto tipoDesconto;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataInicio;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dataTermino;
	
	@NotNull
	private boolean ocorrendo;
	
	@NotNull
	private double taxaDesconto;
	
	Promocao(){
	}

	public Long getIdDesconto() {
		return idDesconto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}
	
	public double getTaxaDesconto() {
		return taxaDesconto;
	}
	
	public boolean isOcorrendo() {
		return this.ocorrendo;
	}
	
	public TipoDesconto getTipoDesconto() {
		return tipoDesconto;
	}
	
	@PrePersist
	public void prePersist() {
		switch(this.tipoDesconto) {
		case BOM_DESCONTO: this.taxaDesconto = 0.1;
						   break;
		case OTIMO_DESCONTO: this.taxaDesconto = 0.25;
		   				   break;
		case SUPER_DESCONTO: this.taxaDesconto = 0.50;
						   break;
		}
		
		LocalDate ld = LocalDate.now();
		Date today = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.ocorrendo = ((this.dataInicio.compareTo(today) <= 0) && (this.dataTermino.compareTo(today) >= 0));
	}

	@PostLoad
	public void postLoad() {
		LocalDate ld = LocalDate.now();
		Date today = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.ocorrendo = ((this.dataInicio.compareTo(today) <= 0) && (this.dataTermino.compareTo(today) >= 0));
	}
}
