package br.com.programa.torcedor.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SOCIO_TORCEDOR")
public class SocioTorcedor implements Serializable{

	private static final long serialVersionUID = 1976619413370688342L;
	
	@Id
	@Column(name = "ID_SOCIO")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "O Nome do Sócio é obrigatório!")
	@Column(name = "NOME_SOCIO")
	private String nome;
	
	@NotNull(message = "O Email do Sócio é obrigatório!")
	@Column(name = "EMAIL")
	private String email;
	
	@NotNull(message = "A Data de Nascimento é obrigatória!")
	@Column(name = "DATA_NASCIMENTO")
	private Calendar dataNascimento;
	
	@NotNull(message = "O Time do Coração é obrigatório!")
	@Column(name = "TIME_CORACAO")
	private String timeCoracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(String timeCoracao) {
		this.timeCoracao = timeCoracao;
	}
	
	public SocioTorcedor toSocioTorcedor(){
		SocioTorcedor socioTorcedor = new SocioTorcedor();
		socioTorcedor.setNome(nome);
		socioTorcedor.setEmail(email);
		socioTorcedor.setDataNascimento(dataNascimento);
		socioTorcedor.setTimeCoracao(timeCoracao);
		return socioTorcedor;
	}
	
}