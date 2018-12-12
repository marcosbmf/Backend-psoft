package br.com.edu.ufcg.cccfarma.api.repository;

import br.com.edu.ufcg.cccfarma.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{
}
