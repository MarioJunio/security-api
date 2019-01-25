package br.com.security.rest.stub;

import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.security.utils.AppUtils;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SyncCliente {

	private Long id;
	private String hashId;
	private String nome;
	private String email;
	private String tipo;
	private String cpf;
	private String cnpj;
	private String telefone1;
	private String telefone2;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	private boolean ativo;
	private Double latitude, longitude;
	private Boolean excluido;

	public SyncCliente(Long id, String nome, String email, String tipo, String cpf, String cnpj, String telefone1,
			String telefone2, String logradouro, int numero, String bairro, String cep, String cidade, String uf,
			boolean ativo) throws NoSuchAlgorithmException {
		super();
		this.id = id;
		this.hashId = AppUtils.md5(id);
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.ativo = ativo;
	}

	public SyncCliente(Long id, String nome, String email, String tipo, String cpf, String cnpj, String telefone1,
			String telefone2, String logradouro, int numero, String bairro, String cep, String cidade, String uf,
			boolean ativo, Double latitude, Double longitude, Boolean excluido) throws NoSuchAlgorithmException {
		super();
		this.id = id;
		this.hashId = AppUtils.md5(id);
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
		this.ativo = ativo;
		this.latitude = latitude;
		this.longitude = longitude;
		this.excluido = excluido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public double getLatitude() {
		return latitude == null ? 0 : latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude == null ? 0 : longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(Boolean excluido) {
		this.excluido = excluido;
	}

	@Override
	public String toString() {
		return "SyncCliente [id=" + id + ", hashId=" + hashId + ", nome=" + nome + ", email=" + email + ", tipo=" + tipo
				+ ", cpf=" + cpf + ", cnpj=" + cnpj + ", telefone1=" + telefone1 + ", telefone2=" + telefone2
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", bairro=" + bairro + ", cep=" + cep
				+ ", cidade=" + cidade + ", uf=" + uf + ", ativo=" + ativo + ", latitude=" + latitude + ", longitude="
				+ longitude + ", excluido=" + excluido + "]";
	}

}
