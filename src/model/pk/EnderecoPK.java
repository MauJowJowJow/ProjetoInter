package model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import model.Pessoa;

@Embeddable
public class EnderecoPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public EnderecoPK(){
		pessoa = new Pessoa();
	}
	
	public EnderecoPK(Pessoa pessoa, int codigo){
		this.pessoa = pessoa;
		this.codigo = codigo;
	}
	
	@MapsId("codPes")
	@ManyToOne
	@JoinColumn(name="codPes")
	private Pessoa pessoa;
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa;
	}
	
	@Column(name="codEnd", length=7)
	private int codigo;

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
				((EnderecoPK)o).getPessoa().getCodigo() == this.getPessoa().getCodigo();

	}
	
	@Override
	public int hashCode(){
		return 31 * (getPessoa().getCodigo() + getCodigo());
	}		
}