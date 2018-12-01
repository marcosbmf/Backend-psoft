package CCCFarma.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	
	@NotNull
	private String nome;
	
	@NotNull
	@Id
	private String cod_barra;
	
	@NotNull
	private String fabricante;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoProduto tipo;
	
	@NotNull
	private int preco;
}
