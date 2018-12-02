package CCCFarma.model.produto;

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

	
	
	
	
	
	
	
	
	
	
}
