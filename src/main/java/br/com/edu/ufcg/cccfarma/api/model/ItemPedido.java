package br.com.edu.ufcg.cccfarma.api.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ItemPedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@NotNull
	private ItemPedidoPK itemPK;

	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotNull
	private Double precoTotal; 
	
	ItemPedido() {
	}

	
	
	public ItemPedido(ItemPedidoPK item, Integer quantidade, Double precoTotal) {
		this.itemPK = item;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
	}
	
	public Double getPrecoTotal() {
		return precoTotal;
	}

	public ItemPedidoPK getItemPK() {
		return itemPK;
	}

	public Integer getQuantidade() {
		return quantidade;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemPK == null) ? 0 : itemPK.hashCode());
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
		ItemPedido other = (ItemPedido) obj;
		if (itemPK == null) {
			if (other.itemPK != null)
				return false;
		} else if (!itemPK.equals(other.itemPK))
			return false;
		return true;
	}
	
	
	
	
	
	
	
	
}
