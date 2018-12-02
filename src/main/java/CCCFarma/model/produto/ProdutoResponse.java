package CCCFarma.model.produto;

public class ProdutoResponse {
	
	private Produto produto;
	private int disponibilidade;
	
	public ProdutoResponse(Produto produto, int disponibilidade) {
		this.produto = produto;
		this.disponibilidade = disponibilidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	
	
}
