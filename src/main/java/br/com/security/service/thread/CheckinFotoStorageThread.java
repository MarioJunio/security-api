package br.com.security.service.thread;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.security.rest.stub.CheckinFoto;
import br.com.security.service.CheckinFotoStorage;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CheckinFotoStorageThread extends Thread {

	private CheckinFoto checkinFoto;

	@Autowired
	private CheckinFotoStorage checkinFotoStorage;

	public CheckinFotoStorageThread(CheckinFoto checkinFoto) {
		this.checkinFoto = checkinFoto;
	}

	@Override
	public void run() {

		try {
			this.checkinFotoStorage.save(this.checkinFoto);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
