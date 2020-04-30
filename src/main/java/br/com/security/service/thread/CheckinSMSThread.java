package br.com.security.service.thread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.security.rest.stub.ClienteSms;
import br.com.security.service.sms.Zenvia;
import br.com.security.utils.AppUtils;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckinSMSThread extends Thread {

	private ClienteSms clienteSMS;
	private List<ClienteSms> clientesSMS = new ArrayList<ClienteSms>();

	@Autowired
	private Zenvia zenvia;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

	public CheckinSMSThread(ClienteSms clienteSMS) {
		super();
		this.clienteSMS = clienteSMS;
	}

	public CheckinSMSThread(List<ClienteSms> clientesSMS) {
		super();
		this.clientesSMS = clientesSMS;
	}

	@Override
	public void run() {

		// adiciona o cliente se houver a lista de clientes a serem enviados SMS
		if (clienteSMS != null) {
			clientesSMS.add(clienteSMS);
		}

		for (ClienteSms cs : clientesSMS) {

			String smartDate = AppUtils.getSmartDate(cs.getData());
			String text = AppUtils.getOnlyDigits(smartDate).isEmpty() ? smartDate : AppUtils.getOnlyDigits(smartDate) + " dias atrás";
			String horario = dateFormat.format(cs.getData());

			zenvia.sendSms(AppUtils.getOnlyDigits(cs.getCelular()),
					String.format("Inspeção realizada %s as %s Observação: %s - Duvidas: %s", text, horario, cs.getDescricao(), "(34) 3819-4644"));

			// System.out.println("status: " + response.getStatus());
			// System.out.println("headers: " + response.getHeaders());
			// System.out.println("body:" + response.readEntity(String.class));
			// System.out.println();

		}

	}

}
