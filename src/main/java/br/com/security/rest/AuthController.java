package br.com.security.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.model.Empregado;
import br.com.security.repository.EmpregadoRepository;
import br.com.security.rest.stub.AuthCredentials;

@RestController
public class AuthController {

	@Autowired
	private EmpregadoRepository empregadoRepository;

	@PostMapping("/auth")
	public ResponseEntity<AuthCredentials> autenticar(@AuthenticationPrincipal UserDetails user) {
		Empregado empregado = empregadoRepository.findByLogin(user.getUsername());
		return new ResponseEntity<>(new AuthCredentials(empregado.getId(), empregado.getLogin(),
				empregado.getTelefone1(), empregado.isAtivo(), 50f), HttpStatus.OK);
	}

	@PostMapping("/logoff")
	public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null)
			new SecurityContextLogoutHandler().logout(request, response, auth);

		return ResponseEntity.ok("Logout efetuado com sucesso");
	}

}
