package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Item_faturadoPK implements Serializable{

	@Column(name="codFat", length=7)
	private int codigoFat;
	
	@Column(name="codPro", length=7)
	private int codigoPro;

	public int getCodigoFat() {
		return codigoFat;
	}

	public void setCodigoFat(int codigoFat) {
		this.codigoFat = codigoFat;
	}

	public int getCodigoPro() {
		return codigoPro;
	}

	public void setCodigoPro(int codigoPro) {
		this.codigoPro = codigoPro;
	}
	
	private static final long serialVersionUID =1L;
	
	@Override
	public boolean equals(Object o){
		return o instanceof Item_faturadoPK &&
				((Item_faturadoPK)o).getCodigoFat() == this.codigoFat &&
				((Item_faturadoPK)o).getCodigoPro() == this.codigoPro;
	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoFat + codigoPro);
	}
}
