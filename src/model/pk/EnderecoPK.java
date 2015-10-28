package model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EnderecoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	private int codigoPessoa;
	private int codigo;
	
	
	public int getCodigoPessoa() {
		return codigoPessoa;
	}
	public void setCodigoPessoa(int codigoPessoa) {
		this.codigoPessoa = codigoPessoa;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
		
}
