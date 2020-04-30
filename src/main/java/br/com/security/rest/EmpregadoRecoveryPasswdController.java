package br.com.security.rest;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.security.model.Empregado;
import br.com.security.model.EmpregadoRecoveryCode;
import br.com.security.repository.EmpregadoRecoveryCodeRepository;
import br.com.security.repository.EmpregadoRepository;
import br.com.security.rest.stub.ResponseSms;
import br.com.security.service.AuthCode;
import br.com.security.service.sms.Zenvia;
import br.com.security.utils.AppUtils;

@RestController
@RequestMapping("/recovery/empregado")
public class EmpregadoRecoveryPasswdController {

	@Autowired
	private AuthCode authCode;

	@Autowired
	private EmpregadoRepository empregadoRepository;

	@Autowired
	private EmpregadoRecoveryCodeRepository empregadoRecoveryCodeRepository;

	@Autowired
	private Zenvia zenvia;

	@GetMapping("/get/{usuario}")
	public ResponseEntity<String> buscarEmpregado(@PathVariable("usuario") String usuario) {
		Empregado empregado = empregadoRepository.findByLogin(usuario);

		if (empregado == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return new ResponseEntity<String>(empregado.getTelefone1(), HttpStatus.OK);
	}

	@PostMapping("/get-code")
	public ResponseEntity<String> gerarTokenAutenticacao(@RequestParam("usuario") String usuario) {

		final Empregado empregado = empregadoRepository.findByLogin(usuario);

		if (empregado == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		final Gson gson = new Gson();
		final String code = authCode.getCode();

		empregadoRecoveryCodeRepository.save(new EmpregadoRecoveryCode(usuario, code, null));
		Response responseSms = zenvia.sendSms(AppUtils.getOnlyDigits(empregado.getTelefone1()), String.format("%s", code));

		ResponseSms bodySms = gson.fromJson(responseSms.readEntity(String.class), ResponseSms.class);
		String statusCode = bodySms.getSendSmsResponse().getStatusCode();

		if (responseSms != null && bodySms != null && responseSms.getStatus() == 200 && (statusCode.equals("00") || statusCode.equals("02")))
			return new ResponseEntity<String>(code, HttpStatus.OK);
		else
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	@PostMapping("/salvar-nova-senha")
	public ResponseEntity<Void> salvarNovaSenha(@RequestParam("usuario") String usuario, @RequestParam("code") String code,
			@RequestParam("novaSenha") String novaSenha) {

		ResponseEntity<Void> response;
		EmpregadoRecoveryCode empregadoRecoveryCode = empregadoRecoveryCodeRepository.findByUsuarioAndCode(usuario, code);

		if (empregadoRecoveryCode != null) {

			// altera senha do empregado
			empregadoRepository.alterarSenha(novaSenha, usuario);

			// salva log da última alteração
			empregadoRecoveryCode.setDataAlteracao(new Date());
			empregadoRecoveryCodeRepository.save(empregadoRecoveryCode);

			response = new ResponseEntity<>(HttpStatus.OK);
		} else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return response;

	}

}
