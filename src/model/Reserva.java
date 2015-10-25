package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import oracle.sql.DATE;

@Entity
@Table(name="reserva")

public class Reserva {
	
	@Id
	@Column(name="codRes", length=7)
	@SequenceGenerator(name="ReservaSequence", sequenceName="hotel.reserva_sequence", allocationSize=1)
	@GeneratedValue(generator="ReservaSequence", strategy=GenerationType.SEQUENCE)
	private int codigoReserva;
	
	@NotNull(message="Informe o codigo da pessoa!")
	@Column(name="codPes", length=7)
	private String codigoPessoa;
	
	@Column(name="emiRes")
	private DATE emissaoReserva;

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public String getCodigoPessoa() {
		return codigoPessoa;
	}

	public void setCodigoPessoa(String codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}

	public DATE getEmissaoReserva() {
		return emissaoReserva;
	}

	public void setEmissaoReserva(DATE emissaoReserva) {
		this.emissaoReserva = emissaoReserva;
	}
	
	
}
