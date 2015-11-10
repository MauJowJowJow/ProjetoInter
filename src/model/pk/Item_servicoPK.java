package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Item_servicoPK implements Serializable {

	@Column(name="codSer", length=7)
	private int codigoSer;
	
	@Column(name="codPro", length=7)
	private int codigoPro;
	
	@Column(name="codFun", length=7)
	private int codigoFun;

	public int getCodigoSer() {
		return codigoSer;
	}

	public void setCodigoSer(int codigoSer) {
		this.codigoSer = codigoSer;
	}

	public int getCodigoPro() {
		return codigoPro;
	}

	public void setCodigoPro(int codigoPro) {
		this.codigoPro = codigoPro;
	}

	public int getCodigoFun() {
		return codigoFun;
	}

	public void setCodigoFun(int codigoFun) {
		this.codigoFun = codigoFun;
	}
	
	private static final long serialVersionUID=1L;
	
	@Override
	public boolean equals (Object o){
		return o instanceof Item_servicoPK &&
				((Item_servicoPK)o).getCodigoFun() == this.codigoFun &&
				((Item_servicoPK)o).getCodigoPro() == this.codigoPro &&
				((Item_servicoPK)o).getCodigoSer() == this.codigoSer;
	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoFun + codigoPro + codigoSer);
	}
}
