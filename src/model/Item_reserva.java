package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import oracle.sql.DATE;

@Entity
@Table(name="item_reserva")

public class Item_reserva {

	@Id
	@Column(name="codRes", length=7)
	@SequenceGenerator(name="ItemReservaSequence", sequenceName="hotel.item_sequence", allocationSize=1)
	@GeneratedValue(generator="ItemReservaSequence", strategy=GenerationType.SEQUENCE)
	private int codigoItemReserva;
	
	@NotNull(message="Informe o codigo do quarto!")
	@Column(name="codQua", length=7)
	private int codigoQuarto;
	
	@NotNull(message="Informe a data de Check-In")
	@Column(name="datCIn")
	private DATE checkIn;
	
	@NotNull(message="Informe a data de Check-Out")
	@Column(name="datOut")
	private DATE checkOut;
	
	@NotNull(message="Informe a quantidade de dias!")
	@Column(name="diaPer", length=4)
	private int diasReserva;
	
	@Column(name="vlrRes", length=7)
	private int valorReserva;

	public int getCodigoItemReserva() {
		return codigoItemReserva;
	}

	public void setCodigoItemReserva(int codigoItemReserva) {
		this.codigoItemReserva = codigoItemReserva;
	}

	public int getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public DATE getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(DATE checkIn) {
		this.checkIn = checkIn;
	}

	public DATE getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(DATE checkOut) {
		this.checkOut = checkOut;
	}

	public int getDiasReserva() {
		return diasReserva;
	}

	public void setDiasReserva(int diasReserva) {
		this.diasReserva = diasReserva;
	}

	public int getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(int valorReserva) {
		this.valorReserva = valorReserva;
	}
}
