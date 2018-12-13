package br.com.edu.ufcg.cccfarma.api.repository;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContaRepository extends PagingAndSortingRepository<Conta, String> {
}
