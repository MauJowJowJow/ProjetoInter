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
	private int codigo;
	
	@Column(name="datEmi")
	private Date dataEmissao;
	
	@Column(name="codRes", length=7)
	private int codigoReserva;
	
	@Column(name="codPes", length=7)
	private int codigoPessoa;
	
	@Column(name="codQua", length=7)
	private int codigoQuarto;
	
	@Column(name="vlrTot", length=12)
	private int valorTotal;
	
	@Column(name="obsFat", length=70)
	private String observacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public int getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public int getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQua(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
