package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.Predio;
import model.Produto;
import model.Reserva;
import model.Servico;

@Embeddable
public class Item_faturadoPK implements Serializable{
	
	private Servico servico = new Servico();	
	@ManyToOne
	@JoinColumn(name="codSer")	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico){
		this.servico = servico;
	}

	@Column(name="codFat", length=7)
	private int codigoFat;
		
	private Produto produto = new Produto();	
	@ManyToOne
	@JoinColumn(name="codPro")	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getCodigoFat() {
		return codigoFat;
	}

	public void setCodigoFat(int codigoFat) {
		this.codigoFat = codigoFat;
	}
	
	private static final long serialVersionUID =1L;
	
	@Override
	public boolean equals(Object o){
		return o instanceof Item_faturadoPK &&
				((Item_faturadoPK)o).getServico().getCodigo() == this.getServico().getCodigo() &&
				((Item_faturadoPK)o).getCodigoFat() == this.getCodigoFat() &&
				((Item_faturadoPK)o).getProduto().getCodigo() == this.getProduto().getCodigo();
	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoFat + getProduto().getCodigo());
	}
}
