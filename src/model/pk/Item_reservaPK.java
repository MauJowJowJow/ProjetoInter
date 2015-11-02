package model.pk;

import java.io.Serializable;

public class Item_reservaPK implements Serializable{
	
	private int codigo;
	private int codigoQuarto;


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQuarto(int codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public boolean equals(Object o){	
		return o instanceof Item_reservaPK &&
				((Item_reservaPK)o).getCodigo() == this.codigo &&
				((Item_reservaPK)o).getCodigoQuarto() == this.codigoQuarto;

	}
	
	@Override
	public int hashCode(){
		return 31 * (codigo + codigoQuarto);
	}

}
