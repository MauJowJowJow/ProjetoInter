package model;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;

import model.dao.FaturamentoDAO;
import model.dao.PessoaDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.Alerta;

@Entity
@Table(name="faturamento")

public class Faturamento extends ModelDefault {
	
	@Id
	@Column(name="codFat", length=7)
	@SequenceGenerator(name="FaturamentoSequence", sequenceName="hotel.faturamento_sequence", allocationSize=1)
	@GeneratedValue(generator="FaturamentoSequence", strategy=GenerationType.AUTO)
	private int codigo;
	
	@Column(name="datEmi")
	private LocalDate dataEmissao;
	
	@ManyToOne
	@JoinColumn(name="codRes")
	private Reserva reserva = new Reserva();
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva){
		this.reserva = reserva;
	}
	
	@ManyToOne
	@JoinColumn(name="codPes")
	private Pessoa pessoa = new Pessoa();
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@ManyToOne
	@JoinColumn(name="codQua")
	private Quarto quarto = new Quarto();
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto){
		this.quarto = quarto;
	}
	
	@Column(name="vlrRes", length=12)
	private double valorReserva;
	
	@Column(name="vlrSer", length=12)
	private double valorServico;
	
	@Column(name="vlrTot", length=12)
	private double valorTotal;
	
	@Column(name="obsFat", length=70)
	private String observacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double getValorReserva() {
		return valorReserva;
	}

	public void setValorReserva(double valorReserva) {
		this.valorReserva = valorReserva;
	}

	public double getValorServico() {
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public void imprimir(){
		JasperReport jasperReport;
		try {    			
			OpenJPAEntityManagerFactory emf = (OpenJPAEntityManagerFactory) Persistence
	                .createEntityManagerFactory("ProjetoInterOPENJPA");
			
			OpenJPAEntityManager em = emf.createEntityManager();
		    OpenJPAEntityManager oem = em.unwrap(OpenJPAEntityManager.class);
		    Connection jdbcConnection = (Connection) oem.getConnection();
			
		    java.io.File file = new java.io.File("CheckOut.jrxml");
	        String path = file.getAbsolutePath();
	        String only_path = path.substring(0,path.lastIndexOf('\\'));
 
    			jasperReport = JasperCompileManager.compileReport(only_path + "/src/view/relatorios/CheckOut.jrxml");
			
			Map<String, Object> parametersMap = new HashMap<String, Object>();

			parametersMap.put("codigoFaturamento",getCodigo());				
			parametersMap.put("codigoPessoa",getPessoa().getCodigo());
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parametersMap, jdbcConnection);
			
			JasperViewer.viewReport(jasperPrint);
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Faturamento exists(){
		FaturamentoDAO dao = new FaturamentoDAO();
		Faturamento model = dao.getById(getCodigo());
		
		if(model == null){
			setErrors("Faturamento não cadastrado!");
		}
		return model;
	}
}
