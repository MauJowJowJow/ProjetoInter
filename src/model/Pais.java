package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pais")

public class Pais {
	
	@Id
	@Column(name="codPai", length=7)
	@SequenceGenerator(name="PaisSequence", sequenceName="hotel.pais.sequence", allocationSize=1)
	@GeneratedValue(generator="PaisSequence", strategy=GenerationType.SEQUENCE)
	private int codigo;
	
	@NotNull(message="Informe o nome do país!")
	@Column(name="desPai", length=30)
	private String nomePais;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
}
