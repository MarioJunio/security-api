package br.com.security.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.model.Checkin;
import br.com.security.repository.CheckinRepository;
import br.com.security.rest.stub.ClienteSms;
import br.com.security.rest.stub.SyncCheckin;
import br.com.security.rest.stub.SyncCheckins;
import br.com.security.sms.Zenvia;
import br.com.security.utils.AppUtils;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private Zenvia zenvia;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

	@GetMapping("/{id}/{dataUltimaSync}")
	public ResponseEntity<SyncCheckins> buscar(@PathVariable("id") Long id, @PathVariable("dataUltimaSync") Long timeSync) {

		SyncCheckins syncCheckins = new SyncCheckins(0l, checkinRepository.findByEmpregado(id, timeSync));
		syncCheckins.setDataSync(new Date().getTime());

		return ResponseEntity.ok(syncCheckins);
	}

	@PostMapping("/sincronizar")
	public ResponseEntity<Long> receberCheckins(@RequestBody List<SyncCheckin> checkins) {
		Long timeSync = 0l;

		System.out.println(checkins.toString());
		
		try {
			final List<ClienteSms> clientsSms = new ArrayList<ClienteSms>();

			// data da sincronização com o servidor
			timeSync = new Date().getTime();

			for (SyncCheckin sc : checkins) {
				Checkin checkinSaved = checkinRepository.save(new Checkin(sc, timeSync));
				clientsSms.add(checkinRepository.findBySms(checkinSaved.getId()));
			}

			// se encontrou clientes, então envie o sms

			if (!clientsSms.isEmpty()) {

				new Thread(new Runnable() {

					@Override
					public void run() {
						enviarSmsLote(clientsSms);
					}

				}).start();

			}

		} catch (Exception e) {
			return ResponseEntity.ok(-1l);
		}

		// System.out.println("Data última sincronziação receber: " + timeSync);

		return ResponseEntity.ok(timeSync);
	}

	private void enviarSmsLote(List<ClienteSms> clientesSms) {

		for (ClienteSms cs : clientesSms) {

			String smartDate = AppUtils.getSmartDate(cs.getData());
			String text = AppUtils.getOnlyDigits(smartDate).isEmpty() ? smartDate : AppUtils.getOnlyDigits(smartDate) + " dias atrás";
			String horario = dateFormat.format(cs.getData());

			Response response = zenvia.sendSms(AppUtils.getOnlyDigits(cs.getCelular()),
					String.format("Inspeção realizada %s as %s Observação: %s - Duvidas: %s", text, horario, cs.getDescricao(), "(34) 3819-4644"));

//			System.out.println("status: " + response.getStatus());
//			System.out.println("headers: " + response.getHeaders());
//			System.out.println("body:" + response.readEntity(String.class));
//			System.out.println();

		}

	}

}
