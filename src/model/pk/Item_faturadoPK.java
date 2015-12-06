package model.pk;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import model.Faturamento;
import model.Produto;
import model.Servico;

@Embeddable
public class Item_faturadoPK implements Serializable{

	@MapsId("codFat")
	@ManyToOne
	@JoinColumn(name="codFat")
	private Faturamento faturamento = new Faturamento();
	
	public Faturamento getFaturamento() {
		return faturamento;
	}
	
	public void setFaturamento(Faturamento faturamento){
		this.faturamento = faturamento;
	}	
	
	@MapsId("codSer")
	@ManyToOne
	@JoinColumn(name="codSer")
	private Servico servico = new Servico();
	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico){
		this.servico = servico;
	}
		
	@MapsId("codPro")
	@ManyToOne
	@JoinColumn(name="codPro")
	private Produto produto = new Produto();
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	private static final long serialVersionUID =1L;
	
	@Override
	public boolean equals(Object o){
		return o instanceof Item_faturadoPK &&
				((Item_faturadoPK)o).getServico().getCodigo() == this.getServico().getCodigo() &&
				((Item_faturadoPK)o).getFaturamento().getCodigo() == this.getFaturamento().getCodigo() &&
				((Item_faturadoPK)o).getProduto().getCodigo() == this.getProduto().getCodigo();
	}
	
	@Override
	public int hashCode(){
		return 31 * (getFaturamento().getCodigo() + getProduto().getCodigo());
	}
}
