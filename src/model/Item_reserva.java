package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import model.pk.Item_reservaPK;
import oracle.sql.DATE;

@Entity
@Table(name="item_reserva")
@IdClass(Item_reservaPK.class)
public class Item_reserva extends ModelDefault{

	@Id
	@Column(name="codRes", length=7)
	private int codigo;

	@Id
	@NotNull(message="Informe o codigo do quarto!")
	@Column(name="codQua", length=7)
	private int codigoQuarto;
	
	@Transient
	private String descricaoQuarto;
	
	@NotNull(message="Informe a data de Check-In")
	@Column(name="datCIn")
	private DATE checkIn;
	
	@Column(name="datOut")
	private DATE checkOut;
	
	@Column(name="diaPer", length=4)
	private int diasReserva;
	
	@Column(name="vlrRes", length=7)
	private double valorReserva;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public String getDescricaoQuarto() {
		return descricaoQuarto;
	}

	public void setDescricaoQuarto(String descricaoQuarto) {
		this.descricaoQuarto = descricaoQuarto;
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

	public double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}
}
