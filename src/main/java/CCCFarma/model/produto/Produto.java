package CCCFarma.model.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Produto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4509030253600549069L;

	@NotNull
	private String nome;
	
	@NotNull
	@Id
	@Column(name="cod_barra")
	private String codBarra;
	
	@NotNull
	private String fabricante;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoProduto tipo;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private int preco;
	
	public Produto() {
	}

	public Produto(@NotNull String nome, @NotNull String codBarra, @NotNull String fabricante,
			@NotNull TipoProduto tipo, @NotNull int preco, @NotNull String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.codBarra = codBarra;
		this.fabricante = fabricante;
		this.tipo = tipo;
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
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

	
	
	
}
