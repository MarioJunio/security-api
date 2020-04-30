package br.com.security.rest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.event.CheckinSalvoEvent;
import br.com.security.model.Checkin;
import br.com.security.repository.CheckinRepository;
import br.com.security.rest.stub.CheckinFoto;
import br.com.security.rest.stub.ClienteSms;
import br.com.security.rest.stub.SyncCheckin;
import br.com.security.rest.stub.SyncCheckins;
import br.com.security.service.CheckinFotoStorage;

@RestController
@RequestMapping("/checkins")
public class CheckinController {

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private CheckinFotoStorage checkinFotoStorage;

	@GetMapping("/{id}/{dataUltimaSync}")
	public ResponseEntity<SyncCheckins> buscar(@PathVariable("id") Long id, @PathVariable("dataUltimaSync") Long timeSync) {
		SyncCheckins syncCheckins = new SyncCheckins(0l, checkinRepository.findByEmpregado(id, timeSync));
		syncCheckins.setDataSync(new Date().getTime());
		
		return ResponseEntity.ok(syncCheckins);
	}

	@PostMapping("/sincronizar")
	public ResponseEntity<Long> receberCheckins(@RequestBody List<SyncCheckin> checkins) {
		Long timeSync = 0l;

		try {

			// data da sincronização com o servidor
			timeSync = new Date().getTime();

			for (SyncCheckin sc : checkins) {
				Checkin checkinSaved = checkinRepository.save(new Checkin(sc, timeSync));

				CheckinFoto checkinFoto = new CheckinFoto(checkinSaved.getId(), sc.getFoto());
				ClienteSms clienteSMS = checkinRepository.findBySms(checkinSaved.getId());
				eventPublisher.publishEvent(new CheckinSalvoEvent(checkinFoto, clienteSMS));
			}

		} catch (Exception e) {
			return ResponseEntity.ok(-1l);
		}

		// System.out.println("Data última sincronziação receber: " + timeSync);

		return ResponseEntity.ok(timeSync);
	}

	@GetMapping(value = "/foto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody ResponseEntity<byte[]> foto(@PathVariable("id") Long checkinId) {

		try {
			return ResponseEntity.ok(this.checkinFotoStorage.read(checkinId));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.notFound().build();
	}

}
