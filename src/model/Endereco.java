package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import model.bean.BindableModel;
import model.bean.validations.CheckCGC;
import model.enums.EstadoCivil;
import model.enums.TipoPessoa;;

@Entity
@Table(name="endereco")

public class Endereco extends BindableModel {
	
	@Id
	@Column=("codEnd", length=7)
	@SequenceGenerator(name="EnderecoSequence", sequenceName="hotel.endereço_sequence" allocationSize=1)
	@GeneratedValue(generator="EnderecoSequence", strategy = GenerationType.SEQUENCE)
	private int codigo;
	
	@NotNull(message="Informe o CEP (Somente números).")
	@Column(name="CEPEnd", length=9)
	private int CEP;
	
	@NotNull(message="Digite o nome da rua!")
	@Column(name="logEnd", length=50)
	private String logradouro;
	
	@Column(name="numEnd", length=9)
	private int numeroEnd;
	
	@Column(name="comEnd", lenght=60)
	private String complemento;
	
	@NotNull(message="Informe o bairro!")
	@Column(name="baiEnd", length=40)
	private String bairro;
	
	@Column(name="codCid")
	private int codigoCidade;

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
