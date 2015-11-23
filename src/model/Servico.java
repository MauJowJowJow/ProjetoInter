package model;

import java.util.Date;
import javax.persistence.*;

import model.enums.StatusQuartoConverter;
import model.enums.StatusServico;
import model.enums.StatusServicoConverter;

@Entity
@Table(name="servico")

public class Servico extends ModelDefault {

	@Id
	@Column(name="codSer", length=7)
	private int codigo;
	
	@ManyToOne
	@JoinColumn(name="codPes")
	@MapsId("codPes")
	private Pessoa pessoa = new Pessoa();
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa;
	}	
	
		
	@ManyToOne
	@JoinColumn(name="codQua")
	@MapsId("codQua")
	private Quarto quarto = new Quarto();
	
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto){
		this.quarto = quarto;
	}
	
	@Column(name="datSer")
	private Date dataServico;
	
	@Column(name="staSer")
	@Convert(converter = StatusServicoConverter.class)
	private StatusServico statusServico;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getDataServico() {
		return dataServico;
	}

	public void setDataServico(Date dataServico) {
		this.dataServico = dataServico;
	}

	public StatusServico getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(StatusServico statusServico) {
		this.statusServico = statusServico;
	}
}
