package model.pk;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Relacao_fat_serPK implements Serializable {
	
	@Column(name="codFat", length=7)
	private int codigoFat;
	
	@Column(name="codSer", length=7)
	private int codigoSer;

	public int getCodigoFat() {
		return codigoFat;
	}

	public void setCodigoFat(int codigoFat) {
		this.codigoFat = codigoFat;
	}

	public int getCodigoSer() {
		return codigoSer;
	}

	public void setCodigoSer(int codigoSer) {
		this.codigoSer = codigoSer;
	}
	
	private static final long serialVersionUID=1L;
	
	@Override
	public boolean equals(Object o){
		return o instanceof Relacao_fat_serPK &&
				((Relacao_fat_serPK)o).getCodigoFat() == this.codigoFat &&
				((Relacao_fat_serPK)o).getCodigoSer() == this.codigoSer;
	}
	
	public int hashCode(){
		return 31 * (codigoFat + codigoSer);
	}
}
