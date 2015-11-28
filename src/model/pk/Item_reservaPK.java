package model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import model.Pessoa;
import model.Quarto;
import model.Reserva;

@Embeddable
public class Item_reservaPK implements Serializable{
	
	@ManyToOne
	@MapsId("codRes")
	@JoinColumn(name="codRes")
	private Reserva reserva = new Reserva();
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	@ManyToOne
	@MapsId("codQua")
	@JoinColumn(name="codQua")
	private Quarto quarto = new Quarto();
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object o){	
		return o instanceof Item_reservaPK &&
				((Item_reservaPK)o).getReserva().getCodigoReserva() == this.getReserva().getCodigoReserva() &&
				((Item_reservaPK)o).getQuarto().getCodigo() == this.getQuarto().getCodigo();
	}
	
	@Override
	public int hashCode(){
		return 31 * (getReserva().getCodigoReserva() + getQuarto().getCodigo());
	}
}
