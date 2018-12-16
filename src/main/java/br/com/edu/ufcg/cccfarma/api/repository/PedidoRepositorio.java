package br.com.edu.ufcg.cccfarma.api.repository;

import br.com.edu.ufcg.cccfarma.api.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{

	List<Pedido> findByUsuarioUsername(String username);
}
