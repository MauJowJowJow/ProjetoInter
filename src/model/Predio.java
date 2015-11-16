package model;

import javax.persistence.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table(name="predio")

public class Predio extends ModelDefault{
	
	private final IntegerProperty codigoPredio = new SimpleIntegerProperty(this, "codigoPredio");
	
	@Id
	@Column(name="codPre", length=7)
	@SequenceGenerator(name="PredioSequence", sequenceName="hotel.predio_sequence", allocationSize=1)
	@GeneratedValue(generator="PredioSequence", strategy=GenerationType.SEQUENCE)
	public int getCodigoPredio() {
		return codigoPredio.get();
	}

	public void setCodigoPredio(int codigoPredio) {
		this.codigoPredio.set(codigoPredio);
	}
	
	@Transient
	public IntegerProperty getCodigoPredioProperty(){
		return codigoPredio;
	}
	
	private StringProperty descricao = new SimpleStringProperty(this,"desPre");
	@Column(name="desPre", length=30)
	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);
	}
	@Transient
	public StringProperty getDescricaoProperty(){
		return this.descricao;
	}
	
	@Column(name="qtdQua", length=6)
	public String getQuartos() {
		return quartos;
	}

	public void setQuartos(String quartos) {
		this.quartos = quartos;
	}

	
}
