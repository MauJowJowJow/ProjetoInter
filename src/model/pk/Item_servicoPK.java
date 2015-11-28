package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Transient;

import model.Predio;
import model.Produto;
import model.Servico;

@Embeddable
public class Item_servicoPK implements Serializable {
	private static final long serialVersionUID=1L;
	
	
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
	
	@Column(name="codFun", length=7)
	private int codigoFun;

	public int getCodigoFun() {
		return codigoFun;
	}

	public void setCodigoFun(int codigoFun) {
		this.codigoFun = codigoFun;
	}
	
	@Override
	public boolean equals (Object o){
		return o instanceof Item_servicoPK &&
				((Item_servicoPK)o).getCodigoFun() == this.codigoFun &&
				((Item_servicoPK)o).getProduto().getCodigo() == this.getProduto().getCodigo() &&
				((Item_servicoPK)o).getServico().getCodigo() == this.getServico().getCodigo();
	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoFun + getProduto().getCodigo() + getServico().getCodigo());
	}
}
