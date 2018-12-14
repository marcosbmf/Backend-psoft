package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.edu.ufcg.cccfarma.api.enums.SituacaoPedido;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numeroPedido")
@Entity
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@JoinColumn(updatable=false)
	private Integer numeroPedido;

	@Enumerated(EnumType.STRING)
	@NotNull
	private SituacaoPedido situacao;

	@Temporal(TemporalType.DATE)
	@NotNull
	@Column(updatable=false)
	private Date dataEmissao;

	@OneToMany(mappedBy = "itemPK.numeroPedido", orphanRemoval = true, cascade = CascadeType.ALL, targetEntity = ItemPedido.class)
	private List<ItemPedido> itens;
	
	@ManyToOne(targetEntity = br.com.edu.ufcg.cccfarma.api.model.Conta.class, cascade = CascadeType.ALL)
	@JoinColumn(updatable=false)
	private Conta usuario;

	public Pedido() {
		this.dataEmissao = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.itens = new ArrayList<>();
		this.numeroPedido = ThreadLocalRandom.current().nextInt(1000000);
	}

	public Pedido(@NotNull Integer numeroPedido, @NotNull SituacaoPedido situacao, Date dataEmissao,
			List<ItemPedido> itens, Conta usuario) {
		this.numeroPedido = numeroPedido;
		this.situacao = situacao;
		this.dataEmissao = dataEmissao;
		this.itens = itens;
		this.usuario = usuario;
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
		for (ItemPedido item : this.itens) {
			precoTotal += item.getPrecoTotal();
		}
		return precoTotal;
	}
	
	public void setUsuario(Conta conta) {
		this.usuario = conta;
	}
	
	public Conta getUsuario() {
		return this.usuario;
	}

	@PrePersist
	public void pre() {
		if (this.situacao == null) {
			this.situacao = (usuario.isAdmin()) ? SituacaoPedido.ENTREGUE : SituacaoPedido.NAO_ENTREGUE;
		}
	}
}
