package br.com.security.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.repository.CheckinRepository;
import br.com.security.repository.ClienteRepository;
import br.com.security.rest.stub.AppCliente;
import br.com.security.rest.stub.AppClienteCheckin;
import br.com.security.rest.stub.SyncAppClienteCheckin;
import br.com.security.service.AuthCode;
import br.com.security.sms.Zenvia;
import br.com.security.utils.AppUtils;

@RestController
@RequestMapping("/app-clientes")
public class AppClientesController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private Zenvia zenvia;

	@Autowired
	private AuthCode authCode;

	@GetMapping("/{phone}")
	public ResponseEntity<AppCliente> buscar2App(@PathVariable("phone") String phone) throws IOException {
		List<AppCliente> appClientes = clienteRepository.buscar(phone);
		AppCliente appCliente = null;

		if (!appClientes.isEmpty()) {
			appCliente = appClientes.get(0);
		}

		ResponseEntity<AppCliente> response;

		// obtem código de confirmação
		if (appCliente != null) {
			appCliente.setCode(authCode.getCode());

			System.out.println("[buscar2App] " + appCliente.getCode());

			// variavel temporaria para armazenar os dados de envio do sms
			final AppCliente appClienteTmp = appCliente;

			Thread sendSMSThread = new Thread(new Runnable() {

				@Override
				public void run() {
					zenvia.sendSms(AppUtils.getOnlyDigits(appClienteTmp.getTelefone1()), String.format("%s", appClienteTmp.getCode()));
				}

			});

			sendSMSThread.start();

			return ResponseEntity.ok(appCliente);

		} else
			response = ResponseEntity.notFound().build();

		return response;
	}

	@PostMapping("/ativar")
	public ResponseEntity<String> ativarCliente(@RequestParam("id") Long id) {
		final Date dataAlteracao = new Date();
		clienteRepository.ativar(id, dataAlteracao);
		return ResponseEntity.ok(String.valueOf(dataAlteracao.getTime()));
	}

	@GetMapping("/buscar-checkins/{id}/{date}")
	public ResponseEntity<SyncAppClienteCheckin> buscarCheckins(@PathVariable("id") Long id, @PathVariable("date") Long timeSync) {
		List<AppClienteCheckin> listAppClienteCheckin = checkinRepository.findByCliente(id, timeSync);

		return listAppClienteCheckin.isEmpty() ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(new SyncAppClienteCheckin(new Date().getTime(), listAppClienteCheckin));
	}
}
