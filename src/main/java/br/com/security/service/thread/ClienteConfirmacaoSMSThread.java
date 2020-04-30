package br.com.security.service.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.security.rest.stub.AppCliente;
import br.com.security.service.sms.Zenvia;
import br.com.security.utils.AppUtils;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClienteConfirmacaoSMSThread extends Thread {

	private AppCliente appCliente;

	@Autowired
	private Zenvia zenvia;

	public ClienteConfirmacaoSMSThread(AppCliente appCliente) {
		this.appCliente = appCliente;
	}

	@Override
	public void run() {
		zenvia.sendSms(AppUtils.getOnlyDigits(this.appCliente.getTelefone1()), String.format("%s", this.appCliente.getCode()));
	}

}
