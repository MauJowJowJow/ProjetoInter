package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ServicoPK implements Serializable{

	@Column(name="codSer", length=7)
	private int codigoSer;
	
	@Column(name="codPes", length=7)
	private int codigoPes;
	
	@Column(name="codQua", length=7)
	private int codigoQua;

	public int getCodigoSer() {
		return codigoSer;
	}

	public void setCodigoSer(int codigoSer) {
		this.codigoSer = codigoSer;
	}

	public int getCodigoPes() {
		return codigoPes;
	}

	public void setCodigoPes(int codigoPes) {
		this.codigoPes = codigoPes;
	}

	public int getCodigoQua() {
		return codigoQua;
	}

	public void setCodigoQua(int codigoQua) {
		this.codigoQua = codigoQua;
	}
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object o){
		return o instanceof ServicoPK &&
				((ServicoPK)o).getCodigoPes() == this.codigoPes &&
				((ServicoPK)o).getCodigoQua() == this.codigoQua &&
				((ServicoPK)o).getCodigoSer() == this.codigoSer;
	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoPes + codigoQua + codigoSer);
	}
}
