package br.com.edu.ufcg.cccfarma.api.pedido;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroPedido")
@Entity
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	private Integer numeroPedido;

	@Enumerated(EnumType.STRING)
	@NotNull
	private SituacaoPedido situacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataEmissao;

	@OneToMany(mappedBy = "itemPK.numeroPedido", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = ItemPedido.class)
	private List<ItemPedido> itens;

	public Pedido() {
		this.dataEmissao = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.itens = new ArrayList<>();
		this.numeroPedido = ThreadLocalRandom.current().nextInt(1000000);
	}

	public Pedido(@NotNull Integer numeroPedido, SituacaoPedido situacao, Date dataEmissao, List<ItemPedido> itens) {
		this.numeroPedido = numeroPedido;
		this.situacao = situacao;
		this.itens = itens;
		this.dataEmissao = dataEmissao;
	}
	
	public void adicionaItem(ItemPedido item) {
		this.itens.add(item);
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}
	
	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public void setSituacao(SituacaoPedido situacao) {
		this.situacao = situacao;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
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
		Pedido other = (Pedido) obj;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		return true;
	}
	
	public Double getPrecoTotal() {
		Double precoTotal = 0.00;
		for (ItemPedido item: this.itens) {
			precoTotal += item.getPrecoTotal();
		}
		return precoTotal;
	}

}