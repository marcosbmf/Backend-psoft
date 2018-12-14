package br.com.edu.ufcg.cccfarma.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.edu.ufcg.cccfarma.api.model.Conta;
import br.com.edu.ufcg.cccfarma.api.repository.ContaRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	private ContaRepository contaRepository;

	@Autowired
	public CustomUserDetailsService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Conta conta = Optional.ofNullable(contaRepository.findById(username).get())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
		return new User(conta.getUsername(), conta.getPassword(),
				conta.isAdmin() ? authorityListAdmin : authorityListUser);

	}
}
