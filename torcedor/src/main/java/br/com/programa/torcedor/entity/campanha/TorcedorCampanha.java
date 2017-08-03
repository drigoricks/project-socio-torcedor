package br.com.programa.torcedor.entity.campanha;

public class TorcedorCampanha {
	
	private String email;
	private String nomeTime;
	private Long codigoCampanha;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeTime() {
		return nomeTime;
	}
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}
	public Long getCodigoCampanha() {
		return codigoCampanha;
	}
	public void setCodigoCampanha(Long codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
}
