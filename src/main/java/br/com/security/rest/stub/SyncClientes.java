package br.com.security.rest.stub;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SyncClientes {

	private Long dataSync;
	private Collection<SyncCliente> clientes;

	public SyncClientes(Long dataSync, Collection<SyncCliente> collection) {
		super();
		this.dataSync = dataSync;
		this.clientes = collection;
	}

	public Long getDataSync() {
		return dataSync;
	}

	public void setDataSync(Long dataSync) {
		this.dataSync = dataSync;
	}

	public Collection<SyncCliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<SyncCliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "SyncClientes [dataSync=" + dataSync + ", clientes=" + clientes + "]";
	}

}
