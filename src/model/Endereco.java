package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import model.pk.EnderecoPK;

@Entity
@Table(name="endereco")
public class Endereco {
	
	@Id
	@Column(name="codPes", length=7)
	private int codigoPessoa;
	
	@Id
	@Column(name="codEnd", length=7)
	private int codigo;
	
	@NotNull(message="Informe o CEP (Somente números).")
	@Column(name="CEPEnd", length=9)
	private int CEP;
	
	@NotNull(message="Informe o nome da rua!")
	@Column(name="logEnd", length=50)
	private String logradouro;
	
	@Column(name="numEnd", length=9)
	private int numeroEnd;
	
	@Column(name="comEnd", length=60)
	private String complemento;
	
	@NotNull(message="Informe o bairro!")
	@Column(name="baiEnd", length=40)
	private String bairro;
	
	@Column(name="codCid")
	private int codigoCidade;
	
	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCEP() {
		return CEP;
	}

	public void setCEP(int cEP) {
		CEP = cEP;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroEnd() {
		return numeroEnd;
	}

	public void setNumeroEnd(int numeroEnd) {
		this.numeroEnd = numeroEnd;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(int codigoCidade) {
		this.codigoCidade = codigoCidade;
	}
}
