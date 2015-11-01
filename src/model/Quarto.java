package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="quarto")

public class Quarto extends ModelDefault{

	@Id
	@Column(name="codQua", length=7)
	@SequenceGenerator(name="QuartoSequence", sequenceName="hotel.quarto_sequence", allocationSize=1)
	@GeneratedValue(generator="QuartoSequence", strategy=GenerationType.SEQUENCE)
	private int codigoQuarto;
	
	@NotNull(message="Informe o nome do quarto!")
	@Column(name="desQua", length=35)
	private String descricao;
	
	@NotNull(message="Informe o andar do quarto!")
	@Column(name="andQua", length=7)
	private int andarQuarto;
	
	@NotNull(message="Informe a quantidade de dormitorios no quarto!")
	@Column(name="qtdDor", length=7)
	private int dormitorios;
	
	@NotNull(message="Informe o valor da di�ria!")
	@Column(name="valQua", length=7)
	private int valorQuarto;
	
	@NotNull(message="Informe o status do quarto!")
	@Column(name="staQua", length=2)
	private String statusQuarto;
	
	@NotNull(message="Informe o c�digo do predio!")
	@Column(name="codPre", length=7)
	private int codigoPredio;

	public int getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAndarQuarto() {
		return andarQuarto;
	}

	public void setAndarQuarto(int andarQuarto) {
		this.andarQuarto = andarQuarto;
	}

	public int getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(int dormitorios) {
		this.dormitorios = dormitorios;
	}

	public int getValorQuarto() {
		return valorQuarto;
	}

	public void setValorQuarto(int valorQuarto) {
		this.valorQuarto = valorQuarto;
	}

	public String getStatusQuarto() {
		return statusQuarto;
	}

	public void setStatusQuarto(String statusQuarto) {
		this.statusQuarto = statusQuarto;
	}

	public int getCodigoPredio() {
		return codigoPredio;
	}

	public void setCodigoPredio(int codigoPredio) {
		this.codigoPredio = codigoPredio;
	}
	
	
}
