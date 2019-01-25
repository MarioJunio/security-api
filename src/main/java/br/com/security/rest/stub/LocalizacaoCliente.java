package br.com.security.rest.stub;

public class LocalizacaoCliente {

	private Long id;
	private double latitude;
	private double longitude;

	public LocalizacaoCliente() {
		super();
	}

	public LocalizacaoCliente(Long id, double latitude, double longitude) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "LocalizacaoCliente [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
