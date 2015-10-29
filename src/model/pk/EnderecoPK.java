package model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnderecoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public EnderecoPK(){
		
	}
	
	public EnderecoPK(int codigoPessoa, int codigo){
		this.codigoPessoa = codigoPessoa;
		this.codigo = codigo;
	}
	
	@Column(name="codPes", length=7)
	private int codigoPessoa;
	
	@Column(name="codEnd", length=7)
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
	
	@Override
	public boolean equals(Object o){	
		return o instanceof EnderecoPK &&
				((EnderecoPK)o).getCodigo() == this.codigo &&
				((EnderecoPK)o).getCodigoPessoa() == this.codigoPessoa;

	}
	
	@Override
	public int hashCode(){
		return 31 * (codigoPessoa + codigoPessoa);
	}
	
		
}