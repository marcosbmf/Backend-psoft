package br.com.edu.ufcg.cccfarma.api.promocao;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edu.ufcg.cccfarma.api.produto.TipoProduto;

@Service
@Transactional
public class PromocaoService {
	
	@Autowired
	private PromocaoRepositorio promocoes;

	public Promocao save(Promocao promocao) {
		return this.promocoes.save(promocao);
	}

	public List<Promocao> getPromocoes() {
		return this.promocoes.findAll();
	}
	
	public Promocao delete(Long id) {
		Promocao promo = this.promocoes.getOne(id);
		this.promocoes.deleteById(id);
		return promo;
	}
	
	public Map<TipoProduto, Promocao> getMelhorPromocaoPorTipo() {
		List<Promocao> promocoes = this.promocoes.findByOcorrendoTrue();
		Map<TipoProduto, Promocao> mapa = new EnumMap<>(TipoProduto.class);
		for (TipoProduto tipo: TipoProduto.values()) {
			mapa.put(tipo, null);
		}
		
		for(Promocao p: promocoes) {
			if (p.isOcorrendo()) {
				Promocao best = mapa.get(p.getTipoProduto());
				if (best == null || p.getValorDesconto() > best.getValorDesconto()) {
					mapa.put(p.getTipoProduto(), p);
				}
			}
		}
		
		return mapa;
		
	}

	public Promocao getMelhorPromocaoPorTipo(TipoProduto tipo) {
		List<Promocao> promocoes = this.promocoes.findByTipoProdutoAndOcorrendoTrue(tipo);
		Promocao best = null;
		
		for(Promocao p: promocoes) {
			if (p.isOcorrendo()) {
				if (best == null || p.getValorDesconto() > best.getValorDesconto()) {
					best = p;
				}
			}
		}
		
		return best;
	}
	
}
