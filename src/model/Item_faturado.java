package model;

import javax.persistence.*;
import model.pk.Item_faturadoPK;

@Entity
@Table(name="item_faturado")

public class Item_faturado extends ModelDefault {
	
	@EmbeddedId
	private Item_faturadoPK pk = new Item_faturadoPK();
	
	@Column(name="qtdIte", length=9)
	private int quantidadeItem;
	
	@Column(name="vlrUni", length=12)
	private int valorUnitario;
	
	@Column(name="vlrTot", length=12)
	private int valorTotal;


	public int getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(int quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
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
