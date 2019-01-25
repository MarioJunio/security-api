package br.com.security.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.security.model.Empregado;
import br.com.security.repository.EmpregadoRepository;

@Component
public class WsEmpregadoDetailsService implements UserDetailsService {

	@Autowired
	private EmpregadoRepository empregadoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		return (User) buscarEmpregado(username);
	}

	private UserDetails buscarEmpregado(String username) {
		Empregado empregado = empregadoRepository.findByLogin(username);

		if (empregado == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
		}

		return new User(username, empregado.getSenha(), new HashSet<>());
	}

}
