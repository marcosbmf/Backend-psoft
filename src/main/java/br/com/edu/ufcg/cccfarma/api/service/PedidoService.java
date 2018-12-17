package br.com.edu.ufcg.cccfarma.api.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edu.ufcg.cccfarma.api.enums.TipoProduto;
import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.model.ItemPedido;
import br.com.edu.ufcg.cccfarma.api.model.ItemPedidoPK;
import br.com.edu.ufcg.cccfarma.api.model.Lote;
import br.com.edu.ufcg.cccfarma.api.model.Pedido;
import br.com.edu.ufcg.cccfarma.api.model.Produto;
import br.com.edu.ufcg.cccfarma.api.model.Promocao;
import br.com.edu.ufcg.cccfarma.api.repository.PedidoRepositorio;
import br.com.edu.ufcg.cccfarma.api.requests.PedidoRequest;

@Service
@Transactional
public class PedidoService {

	@Autowired
	private PedidoRepositorio pedidos;

	@Autowired
	private LoteService lotes;

	@Autowired
	private PromocaoService promocoes;

	@Autowired
	private ContaService usuarios;

	public List<Pedido> getPedidos() {
		Conta usuario = ((Optional<Conta>) this.usuarios.exibirPerfil().getBody()).get();
		if (usuario.isAdmin())
			return this.pedidos.findAll();
		else
			return this.pedidos.findByUsuarioUsername(usuario.getUsername());
	}

	@SuppressWarnings("unchecked")
	public Pedido savePedido(List<PedidoRequest> request) {
		Pedido pedido = new Pedido();
		Conta usuario = ((Optional<Conta>) this.usuarios.exibirPerfil().getBody()).get();
		pedido.setUsuario(usuario);
		this.formaPedido(request, pedido);
		return this.pedidos.saveAndFlush(pedido);
	}

	private void formaPedido(List<PedidoRequest> request, Pedido pedido) {
		Map<TipoProduto, Promocao> promocoes = this.promocoes.getMelhorPromocaoPorTipo();

		for (PedidoRequest itemRequest : request) {
			List<Lote> lotes = this.lotes.listaLotesPorProduto(itemRequest.getProduto().getCodBarra());
			Collections.sort(lotes);
			if (lotes.size() >= 1) {
				int qtd = itemRequest.getQuantidade();
				Produto produto = lotes.get(0).getProduto();
				Promocao promo = promocoes.get(produto.getTipo());
				Double preco = (promo == null) ? produto.getPreco() : produto.getPreco() * (1 - promo.getTaxaDesconto());
				this.adicionaAoPedido(produto, lotes, qtd, preco, pedido);
			} else {
				throw new IllegalArgumentException(
						"Não há disponibilidade desta quantidade do produto " + itemRequest.getProduto().getNome());
			}
		}

	}

	private void adicionaAoPedido(Produto produto, List<Lote> lotes, int qtd, Double preco, Pedido pedido) {
		for (Lote lote : lotes) {
			Integer disponivel = lote.getQuantidadeDisponivel();
			if (disponivel > 0) {
				ItemPedidoPK itemInfo = new ItemPedidoPK(pedido, produto, lote);
				if (qtd > disponivel) {
					ItemPedido item = new ItemPedido(itemInfo, disponivel, preco * disponivel);
					pedido.adicionaItem(item);

					qtd -= disponivel;
					lote.realizaVenda(disponivel);
				} else {
					ItemPedido item = new ItemPedido(itemInfo, qtd, preco * qtd);
					pedido.adicionaItem(item);

					lote.realizaVenda(qtd);
					qtd = 0;
					break;
				}
			}
		}
		if (qtd != 0) {
			throw new IllegalArgumentException(
					"Não há disponibilidade desta quantidade do produto " + produto.getNome());
		}
	}
	
	public Pedido editaPedido(Pedido pedido, int pedidoId) {
		Pedido editado = this.pedidos.getOne(pedido.getNumeroPedido());
		if (editado == null) {
			throw new IllegalArgumentException("Produto não existe!");
		} else if (editado.getNumeroPedido() != pedidoId) {
			throw new IllegalArgumentException("BAD REQUEST!");
		}
		editado.setSituacao(pedido.getSituacao());
		return this.pedidos.saveAndFlush(editado);
	}

}
