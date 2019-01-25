package br.com.security.rest.stub;

import java.util.Collection;

public class SyncCheckins {

	private Long dataSync;
	private Collection<SyncCheckin> checkins;

	public SyncCheckins(Long dataSync, Collection<SyncCheckin> checkins) {
		super();
		this.dataSync = dataSync;
		this.checkins = checkins;
	}

	public Long getDataSync() {
		return dataSync;
	}

	public void setDataSync(Long dataSync) {
		this.dataSync = dataSync;
	}

	public Collection<SyncCheckin> getCheckins() {
		return checkins;
	}

	public void setCheckins(Collection<SyncCheckin> checkins) {
		this.checkins = checkins;
	}

	@Override
	public String toString() {
		return "SyncCheckins [dataSync=" + dataSync + ", checkins=" + checkins + "]";
	}

}
