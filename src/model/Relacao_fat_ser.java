package model;

import javax.persistence.*;
import model.pk.Relacao_fat_serPK;

@Entity
@Table(name="relacao_Fat_Ser")

public class Relacao_fat_ser extends ModelDefault {

	@EmbeddedId
	private Relacao_fat_serPK pk = new Relacao_fat_serPK();
	
	public int getCodigoFat(){
		return pk.getCodigoFat();
	}
	
	public void setCodigoFat(int codigoFat){
		this.pk.setCodigoFat(codigoFat);
	}
	public int getCodigoSer(){
		return pk.getCodigoSer();
	}
	
	public void setCodigoSer(int codigoSer){
		this.pk.setCodigoSer(codigoSer);
	}
}
