package br.com.security.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.filters.FiltroConsulta;
import br.com.security.repository.ClienteRepository;
import br.com.security.repository.ClienteRepositoryImpl;
import br.com.security.rest.stub.LocalizacaoCliente;
import br.com.security.rest.stub.SyncCliente;
import br.com.security.rest.stub.SyncClientes;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ClienteRepositoryImpl clienteRepositoryCustomImpl;

	@GetMapping(value = "/{dataUltimaSync}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SyncClientes> buscar(@PathVariable("dataUltimaSync") Long dataUltimaSync) {
		return ResponseEntity.ok(new SyncClientes(new Date().getTime(), clienteRepository.buscarNaoSincronizados(new Date(dataUltimaSync))));
	}

	@GetMapping("/consulta")
	public ResponseEntity<List<SyncCliente>> consultar(FiltroConsulta filtro, Model model, Pageable pageable) {

		if (filtro.getCampo() == null)
			filtro.setCampo("<domain>.nome");

		if (filtro.getValor() == null)
			filtro.setValor("");

		if (filtro.getStatus() == null)
			filtro.setStatus("");

		return ResponseEntity.ok(clienteRepositoryCustomImpl.findByField(new PageRequest(pageable.getPageNumber(), 20), filtro));
	}

	@PostMapping("/sync-localizacoes")
	public ResponseEntity<String> postCheckin(@RequestBody List<LocalizacaoCliente> localizacoes) {

		Date dataAlteracao = new Date();

		// atualiza as localizacoes do cliente e tambem a data de alteracao, para os
		// outros dispositivos sincronizarem
		for (LocalizacaoCliente loc : localizacoes)
			clienteRepository.atualizarLocalizacao(loc.getLatitude(), loc.getLongitude(), dataAlteracao, loc.getId());

		if (localizacoes.size() > 0)
			return ResponseEntity.ok(String.valueOf(dataAlteracao.getTime()));
		else
			return ResponseEntity.ok("");
	}

}
