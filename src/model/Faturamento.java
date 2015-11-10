package model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="faturamento")

public class Faturamento extends ModelDefault {
	
	@Id
	@Column(name="codFat", length=7)
	@SequenceGenerator(name="FaturamentoSequence", sequenceName="hotel.faturamento_sequence", allocationSize=1)
	@GeneratedValue(generator="PredioSequence", strategy=GenerationType.SEQUENCE)
	private int codigoFat;
	
	@Column(name="datEmi")
	private Date dataEmissao;
	
	@Column(name="codPes", length=7)
	private int codigoPes;
	
	@Column(name="codQua", length=7)
	private int codigoQua;
	
	@Column(name="vlrTot", length=12)
	private int vlrTot;
	
	@Column(name="obsFat", length=70)
	private String observacao;

	public int getCodigoFat() {
		return codigoFat;
	}

	public void setCodigoFat(int codigoFat) {
		this.codigoFat = codigoFat;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public int getCodigoPes() {
		return codigoPes;
	}

	public void setCodigoPes(int codigoPes) {
		this.codigoPes = codigoPes;
	}

	public int getCodigoQua() {
		return codigoQua;
	}

	public void setCodigoQua(int codigoQua) {
		this.codigoQua = codigoQua;
	}

	public int getVlrTot() {
		return vlrTot;
	}

	public void setVlrTot(int vlrTot) {
		this.vlrTot = vlrTot;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
