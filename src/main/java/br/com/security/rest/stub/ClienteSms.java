package br.com.security.rest.stub;

import java.util.Date;

public class ClienteSms {

	private String nome;
	private Date data;
	private String descricao;
	private String celular;

	public ClienteSms(String nome, Date data, String descricao, String celular) {
		super();
		this.nome = nome;
		this.data = data;
		this.descricao = descricao;
		this.celular = celular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public String toString() {
		return "ClienteSms [nome=" + nome + ", data=" + data + ", descricao=" + descricao + ", celular=" + celular + "]";
	}

}
