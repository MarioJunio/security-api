package br.com.security.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.security.event.CheckinSalvoEvent;
import br.com.security.service.thread.CheckinFotoStorageThread;
import br.com.security.service.thread.CheckinSMSThread;

@Component
public class CheckinSalvoListener implements ApplicationListener<CheckinSalvoEvent> {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void onApplicationEvent(CheckinSalvoEvent checkinSalvoEvent) {
		CheckinFotoStorageThread checkinFotoStorageThread = applicationContext.getBean(CheckinFotoStorageThread.class, checkinSalvoEvent.getCheckinFoto());
		CheckinSMSThread checkinSMSThread = applicationContext.getBean(CheckinSMSThread.class, checkinSalvoEvent.getClienteSms());
		
		checkinFotoStorageThread.start();
		checkinSMSThread.start();
	}

}
