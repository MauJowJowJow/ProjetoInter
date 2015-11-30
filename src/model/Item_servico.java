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
	
	@Column(name="vlrUni", length=12, precision = 2)
	private double valorUnitario;
	
	@Column(name="vlrTot", length=12, precision = 2)
	private double valorTotal;

	public void setPK(Item_servicoPK pk) {
		this.pk = pk;
	}
	
	public Item_servicoPK getPK() {
		return pk;
	}

	public int getQuantidadeVendido() {
		return quantidadeVendido;
	}

	public void setQuantidadeVendido(int quantidadeVendido) {
		this.quantidadeVendido = quantidadeVendido;
	}

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
