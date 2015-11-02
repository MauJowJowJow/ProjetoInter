package model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import model.pk.Item_reservaPK;

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
	private Date checkIn;
	
	@Column(name="datOut")
	private Date checkOut;
	
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
}
