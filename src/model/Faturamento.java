package model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name="faturamento")

public class Faturamento extends ModelDefault {
	
	@Id
	@Column(name="codFat", length=7)
	@SequenceGenerator(name="FaturamentoSequence", sequenceName="hotel.faturamento_sequence", allocationSize=1)
	@GeneratedValue(generator="FaturamentoSequence", strategy=GenerationType.AUTO)
	private int codigo;
	
	@Column(name="datEmi")
	private LocalDate dataEmissao;
	
	@ManyToOne
	@JoinColumn(name="codRes")
	private Reserva reserva = new Reserva();
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva){
		this.reserva = reserva;
	}
	
	@ManyToOne
	@JoinColumn(name="codPes")
	private Pessoa pessoa = new Pessoa();
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@ManyToOne
	@JoinColumn(name="codQua")
	private Quarto quarto = new Quarto();
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto){
		this.quarto = quarto;
	}
	
	@Column(name="vlrRes", length=12)
	private double valorReserva;
	
	@Column(name="vlrSer", length=12)
	private double valorServico;
	
	@Column(name="vlrTot", length=12)
	private double valorTotal;
	
	@Column(name="obsFat", length=70)
	private String observacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}

	public double getValorServico() {
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
