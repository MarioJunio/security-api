package br.com.security.service.sms;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
public class Zenvia {

	public Response sendSms(String phone, String message) {

		phone = "55" + phone;

		Client client = ClientBuilder.newClient();

		Entity<?> payload = Entity.json(String.format(
				"{\"sendSmsRequest\": {    \"from\": \"Security app\",    \"to\": \"%s\",    \"msg\": \"%s\",    \"callbackOption\": \"NONE\",    \"id\":\"%s\",   \"flashSms\": \"true\"  }}",
				phone, message, String.valueOf(new Date().getTime())));

		return client.target("https://api-rest.zenvia.com/services/send-sms").request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Basic c2VjdXJpdHl0ZWNoMjo3czRYNnVHRjNL").header("Accept", "application/json").post(payload);

	}

}
