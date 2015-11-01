package model;

import javax.persistence.*;

@Entity
@Table(name="predio")

public class Predio extends ModelDefault{
	
	@Id
	@Column(name="codPre", length=7)
	@SequenceGenerator(name="PredioSequence", sequenceName="hotel.predio_sequence", allocationSize=1)
	@GeneratedValue(generator="PredioSequence", strategy=GenerationType.SEQUENCE)
	private int codigoPredio;
	
	@Column(name="desPre", length=30)
	private String descricao;

	public int getCodigoPredio() {
		return codigoPredio;
	}

	public void setCodigoPredio(int codigoPredio) {
		this.codigoPredio = codigoPredio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	

}
