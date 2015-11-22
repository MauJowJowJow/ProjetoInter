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
	private double valorUnitario;
	
	@Column(name="vlrTot", length=12)
	private double valorTotal;


	public int getQuantidadeItem() {
		return quantidadeItem;
	}

	public void setQuantidadeItem(int quantidadeItem) {
		this.quantidadeItem = quantidadeItem;
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
	
	public Item_faturadoPK getPK(){
		return pk;
	}
	
	public void setPK(Item_faturadoPK pk){
		this.pk = pk;
	}
}
