package br.com.security.rest.stub;

public class AuthCredentials {

	private Long id;
	private String login;
	private String telefone;
	private boolean ativo;
	private float minRadius;

	public AuthCredentials(Long id, String login, String telefone, boolean ativo, float minRadius) {
		super();
		this.id = id;
		this.login = login;
		this.telefone = telefone;
		this.ativo = ativo;
		this.minRadius = minRadius;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public float getMinRadius() {
		return minRadius;
	}

	public void setMinRadius(float minRadius) {
		this.minRadius = minRadius;
	}

}
