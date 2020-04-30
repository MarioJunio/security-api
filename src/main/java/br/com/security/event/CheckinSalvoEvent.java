package br.com.security.event;

import org.springframework.context.ApplicationEvent;

import br.com.security.rest.stub.CheckinFoto;
import br.com.security.rest.stub.ClienteSms;

public class CheckinSalvoEvent extends ApplicationEvent {

	private static final long serialVersionUID = -7685290792414264656L;

	private CheckinFoto checkinFoto;
	private ClienteSms clienteSms;

	public CheckinSalvoEvent(ClienteSms clienteSms) {
		super(clienteSms);

		this.clienteSms = clienteSms;
	}

	public CheckinSalvoEvent(CheckinFoto checkinFoto, ClienteSms clienteSms) {
		super(clienteSms);

		this.checkinFoto = checkinFoto;
		this.clienteSms = clienteSms;
	}

	public CheckinFoto getCheckinFoto() {
		return checkinFoto;
	}

	public void setCheckinFoto(CheckinFoto checkinFoto) {
		this.checkinFoto = checkinFoto;
	}

	public ClienteSms getClienteSms() {
		return clienteSms;
	}

	public void setClienteSms(ClienteSms clienteSms) {
		this.clienteSms = clienteSms;
	}

}
