package br.com.security.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.security.model.MobileActivation;
import br.com.security.repository.MobileActivationRepository;

@RestController
@RequestMapping("/app-mdata")
public class AppMDataController {

	@Autowired
	private MobileActivationRepository mobileActivationRepository;

	@PostMapping("/key-register")
	public ResponseEntity<String> registerKey(@RequestParam("key") String key, @RequestParam("data") String data, @RequestParam("imei") String imei)
			throws ParseException {
		
		MobileActivation activation = mobileActivationRepository.findOne(key);

		// verifica se a chave ainda não foi usada, se não foi usada então essa chave
		// poderá ser registrada
		if (activation == null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			mobileActivationRepository.save(new MobileActivation(key, simpleDateFormat.parse(data), imei));
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Essa chave já foi utilizada por outro dispositivo, por favor utilize outra chave!", HttpStatus.OK);

	}
}
