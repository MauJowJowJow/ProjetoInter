package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import model.pk.Item_servicoPK;

@Entity
@Table(name="item_servico")

public class Item_servico extends ModelDefault {
	
	@EmbeddedId
	private Item_servicoPK pk = new Item_servicoPK();
	
	@NotNull(message="Informe a quantidade do produto")
	@Column(name="qtdVen")
	private int quantidadeVendido;
	
	@Column(name="vlrUni", length=12)
	private int valorUnitario;
	
	@Column(name="vlrTot", length=12)
	private int valorTotal;

	public int getCodigoSer() {
		return pk.getCodigoSer();
	}

	public void setCodigoSer(int codigoSer) {
		this.pk.setCodigoSer(codigoSer);
	}
	
	public int getCodigoPro() {
		return pk.getCodigoPro();
	}

	public void setCodigoPro(int codigoPro) {
		this.pk.setCodigoPro(codigoPro);
	}
	public int getCodigoFun() {
		return pk.getCodigoFun();
	}

	public void setCodigoFun(int codigoFun) {
		this.pk.setCodigoFun(codigoFun);
	}

	public int getQuantidadeVendido() {
		return quantidadeVendido;
	}

	public void setQuantidadeVendido(int quantidadeVendido) {
		this.quantidadeVendido = quantidadeVendido;
	}

	public int getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(int valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
}
