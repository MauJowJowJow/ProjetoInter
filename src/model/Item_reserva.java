package model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import model.enums.StatusReserva;
import model.pk.Item_reservaPK;

@Entity
@Table(name="item_reserva")

public class Item_reserva extends ModelDefault{
	@EmbeddedId
	private Item_reservaPK pk = new Item_reservaPK();
	
	@NotNull(message="Informe a data de Check-In")
	@Column(name="datCIn")
	private Date checkIn;
	
	@Column(name="datOut")
	private Date checkOut;
	
	@Column(name="diaPer", length=4)
	private int diasReserva;
	
	@Column(name="vlrRes", length=7)
	private double valorReserva;
	
	@Column(name="staRes")
	private StatusReserva statusReserva;
	
	public Item_reservaPK getPK(){
		return pk;
	}
	
	public void setPK(Item_reservaPK pk){
		this.pk = pk;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(int diasReserva) {
		this.diasReserva = diasReserva;
	}

	public double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}
	
	public StatusReserva getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(StatusReserva statusReserva) {
		this.statusReserva = statusReserva;
	}
	
	public int existeNaLista(List<Item_reserva> lista){
		int index = -1;
		for(Item_reserva itr : lista){
			if(getPK().equals(itr.getPK())){
				index = lista.indexOf(itr);
				break;
			}
		}
		return index;
	}
}
