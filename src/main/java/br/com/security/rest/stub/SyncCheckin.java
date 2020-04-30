package br.com.security.rest.stub;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.security.model.CheckinStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SyncCheckin {

	private Long id;
	private Long funcionarioId;
	private Long cliente;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private Date data;

	private double latitude;
	private double longitude;
	private CheckinStatus status;
	private String descricao;
	private String foto;

	public SyncCheckin() {
		super();
	}

	public SyncCheckin(Long id, Long funcionarioId, Long cliente, Date data, double latitude, double longitude, CheckinStatus status,
			String descricao) {
		super();
		this.id = id;
		this.funcionarioId = funcionarioId;
		this.cliente = cliente;
		this.data = data;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.descricao = descricao;
	}

	public SyncCheckin(Long id, Long funcionarioId, Long cliente, Date data, double latitude, double longitude, CheckinStatus status,
			String descricao, String foto) {
		this(id, funcionarioId, cliente, data, latitude, longitude, status, descricao);
		this.foto = foto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Long funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "SyncCheckin [id=" + id + ", funcionarioId=" + funcionarioId + ", cliente=" + cliente + ", data=" + data + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", status=" + status + ", descricao=" + descricao + ", foto="
				+ (StringUtils.isEmpty(foto) ? "Nao possui" : "Possui") + "]";
	}

}
