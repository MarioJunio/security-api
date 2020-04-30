package br.com.security.rest.stub;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.security.model.CheckinStatus;

public class AppClienteCheckin {

	private Long id;
	private String funcionario;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date data;
	
	private CheckinStatus status;
	private String descricao;
	private boolean foto;

	public AppClienteCheckin(Long id, String funcionario, Date data, CheckinStatus status, String descricao) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.data = data;
		this.status = status;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public CheckinStatus getStatus() {
		return status;
	}

	public void setStatus(CheckinStatus status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFoto() {
		return foto;
	}

	public void setFoto(boolean foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppClienteCheckin other = (AppClienteCheckin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppClienteCheckin [id=" + id + ", funcionario=" + funcionario + ", data=" + data + ", status=" + status + ", descricao=" + descricao
				+ ", foto=" + foto + "]";
	}

}
