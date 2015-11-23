package model;

import java.sql.Date;
import javax.persistence.*;
import model.dao.GenericDAOImpl;
import model.dao.ReservaDAO;
import model.enums.StatusReserva;

@Entity
@Table(name="reserva")
public class Reserva extends ModelDefault{
	
	@Id
	@Column(name="codRes", length=7)
	@SequenceGenerator(name="ReservaSequence", sequenceName="hotel.reserva_sequence", allocationSize=1)
	@GeneratedValue(generator="ReservaSequence", strategy=GenerationType.SEQUENCE)
	private int codigoReserva;
	
	@Column(name="emiRes")
	private Date emissaoReserva;
	
	@Transient
	private Pessoa pessoa = new Pessoa();
	
	@ManyToOne
	@JoinColumn(name="codPes")
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Column(name="vlrTot", length = 12, precision = 2)
	private double valorTotal;
	
	@Column(name="staRes")
	private StatusReserva statusReserva;

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public Date getEmissaoReserva() {
		return emissaoReserva;
	}

	public void setEmissaoReserva(Date emissaoReserva) {
		this.emissaoReserva = emissaoReserva;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public StatusReserva getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(StatusReserva statusReserva) {
		this.statusReserva = statusReserva;
	}
	
	public Reserva exists(){
		ReservaDAO dao = new ReservaDAO();
		Reserva model = dao.getById(getCodigoReserva());
		
		if(model == null){
			setErrors("Reserva não cadastrada!");
		}
		return model;
	}

	public boolean cancelaReserva(){
		//TODO Cancelamento reserva
		
		if(getCodigoReserva() == 0){
			setErrors("Reserva ainda não finalizada!");
			return false;
		}
		
		ReservaDAO reservaDAO = new ReservaDAO();
		GenericDAOImpl<Integer, Boolean> faturamentoDAO = new GenericDAOImpl<Integer, Boolean>();
		
		faturamentoDAO.query("SELECT TRUE FROM faturamento where ", null);
		
		setStatusReserva(StatusReserva.Cancelada);
		reservaDAO.update(this);

		return true;
	}	
}
