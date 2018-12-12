package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class ItemPedidoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="numero_pedido")
	@JsonIgnore
	private Pedido numeroPedido;
	
	@NotNull
	@JoinColumn(name="produto_cod_barra")
	@ManyToOne(cascade=CascadeType.ALL)
	private Produto produto;
	
	@NotNull
	@JoinColumn(name="lote_id")
	@ManyToOne(cascade=CascadeType.ALL)
	private Lote lote;
	
	ItemPedidoPK() {
	}

	public ItemPedidoPK(@NotNull Pedido numeroPedido, @NotNull Produto produto, @NotNull Lote lote) {
		this.numeroPedido = numeroPedido;
		this.produto = produto;
		this.lote = lote;
	}

	public Pedido getNumeroPedido() {
		return numeroPedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public Lote getLote() {
		return lote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lote == null) ? 0 : lote.hashCode());
		result = prime * result + ((numeroPedido == null) ? 0 : numeroPedido.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		ItemPedidoPK other = (ItemPedidoPK) obj;
		if (lote == null) {
			if (other.lote != null)
				return false;
		} else if (!lote.equals(other.lote))
			return false;
		if (numeroPedido == null) {
			if (other.numeroPedido != null)
				return false;
		} else if (!numeroPedido.equals(other.numeroPedido))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
	
	
	

}
