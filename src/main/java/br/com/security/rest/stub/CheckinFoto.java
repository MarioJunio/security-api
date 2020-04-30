package br.com.security.rest.stub;

public class CheckinFoto {

	private Long checkinId;
	private String foto;

	public CheckinFoto(Long checkinId, String foto) {
		super();
		this.checkinId = checkinId;
		this.foto = foto;
	}

	public Long getCheckinId() {
		return checkinId;
	}

	public void setCheckinId(Long checkinId) {
		this.checkinId = checkinId;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
