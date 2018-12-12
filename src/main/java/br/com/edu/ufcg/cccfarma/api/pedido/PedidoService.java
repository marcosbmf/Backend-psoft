package br.com.edu.ufcg.cccfarma.api.pedido;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edu.ufcg.cccfarma.api.lote.Lote;
import br.com.edu.ufcg.cccfarma.api.lote.LoteRepositorio;
import br.com.edu.ufcg.cccfarma.api.produto.Produto;
import br.com.edu.ufcg.cccfarma.api.produto.TipoProduto;
import br.com.edu.ufcg.cccfarma.api.promocao.Promocao;
import br.com.edu.ufcg.cccfarma.api.promocao.PromocaoService;

@Service
@Transactional
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio pedidos;
	
	@Autowired
	private LoteRepositorio lotes;
	
	@Autowired
	private PromocaoService promocoes;
	
	public List<Pedido> getPedidos() {
		return this.pedidos.findAll();
	}
	
	public Pedido savePedido(List<PedidoRequest> request) {
		Pedido pedido = new Pedido();
		pedido.setSituacao(SituacaoPedido.NAO_ENTREGUE); //VAI DEPENDER DO TIPO DE USUÁRIO.
		this.formaPedido(request, pedido);
		return this.pedidos.saveAndFlush(pedido);
		
	}

	private void formaPedido(List<PedidoRequest> request, Pedido pedido) {
		Map<TipoProduto, Promocao> promocoes = this.promocoes.getMelhorPromocaoPorTipo();

		for(PedidoRequest itemRequest : request) {
			List<Lote> lotes = this.lotes.findByProduto(itemRequest.getProduto());
			Collections.sort(lotes);
			int qtd = itemRequest.getQuantidade();
			Produto produto  = itemRequest.getProduto();
			Promocao promo = promocoes.get(produto.getTipo());
			Double preco = (promo == null) ? produto.getPreco() : produto.getPreco()*(1-promo.getTaxaDesconto());
			this.adicionaAoPedido(produto, lotes, qtd, preco, pedido);
		}
			
	}

	private void adicionaAoPedido(Produto produto, List<Lote> lotes, int qtd, Double preco, Pedido pedido) {
		for (Lote lote: lotes) {
			Integer disponivel = lote.getQuantidadeDisponivel();
			if (disponivel > 0) {
				ItemPedidoPK itemInfo = new ItemPedidoPK(pedido, produto, lote);
				if (qtd > disponivel) {
					ItemPedido item = new ItemPedido(itemInfo, disponivel, preco*disponivel);
					pedido.adicionaItem(item);

					qtd -= disponivel;
					lote.realizaVenda(disponivel);
				} else {
					ItemPedido item = new ItemPedido(itemInfo, qtd, preco*qtd);
					pedido.adicionaItem(item);
					
					lote.realizaVenda(qtd);
					qtd = 0;
					break;
				}
			}
		}
		if (qtd != 0) {
			throw new IllegalArgumentException("Não há disponibilidade desta quantidade do produto " + produto.getNome());
		}
	}
	
	

}
