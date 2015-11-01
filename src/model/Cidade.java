package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cidade")

public class Cidade extends ModelDefault{
	
	@Id
	@Column(name="codCid", length=7)
	@SequenceGenerator(name="CidadeSequence", sequenceName="hotel.cidade_sequence", allocationSize=1)
	@GeneratedValue(generator="CidadeSequence", strategy=GenerationType.SEQUENCE)
	private int codigoCidade;
	
	@NotNull(message="Informe o nome da cidade!")
	@Column(name="desCid", length=45)
	private String nomeCidade;
	
	@NotNull(message="Informe o codigo do estado!")
	@Column(name="codEst", length=7)
	private int codigoEstado;

	public int getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(int codigoCidade) {
		this.codigoCidade = codigoCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public int getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
}
