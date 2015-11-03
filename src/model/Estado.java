package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="estado")
public class Estado extends ModelDefault{

	@Id
	@Column(name="codEst", length=7)
	@SequenceGenerator(name="EstadoSequence", sequenceName="hotel.estado_sequence", allocationSize=1)
	@GeneratedValue(generator="EstadoSequence", strategy=GenerationType.SEQUENCE)
	private int codigoEstado;
	
	@NotNull(message="Informe o nome do estado!")
	@Column(name="desEst", length=45)
	private String nomeEstado;
	
	@NotNull(message="Informe a sigla do estado")
	@Column(name="sigEst", length=3)
	private String siglaEstado;
	
	@NotNull(message="Informe o código do país")
	@Column(name="nomPai", length=40)
	private String nomePais;

	public int getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(int codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
}
