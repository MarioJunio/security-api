package br.com.security.rest.stub;

import java.util.List;

public class SyncAppClienteCheckin {

	private Long timeSync;
	private List<AppClienteCheckin> listAppClienteCheckin;

	public SyncAppClienteCheckin(Long timeSync, List<AppClienteCheckin> listAppClienteCheckin) {
		super();
		this.timeSync = timeSync;
		this.listAppClienteCheckin = listAppClienteCheckin;
	}

	public Long getTimeSync() {
		return timeSync;
	}

	public void setTimeSync(Long timeSync) {
		this.timeSync = timeSync;
	}

	public List<AppClienteCheckin> getListAppClienteCheckin() {
		return listAppClienteCheckin;
	}

	public void setListAppClienteCheckin(List<AppClienteCheckin> listAppClienteCheckin) {
		this.listAppClienteCheckin = listAppClienteCheckin;
	}

}
