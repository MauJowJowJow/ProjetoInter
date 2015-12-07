package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

@Entity
@Table(name="estoque_produto")
public class Estoque_produto extends ModelDefault implements Serializable{
	private static final long serialVersionUID = 3205501978338533398L;
    private Produto produto;
	private final IntegerProperty codigo = new SimpleIntegerProperty(this, "codigo");    
	
	//  Necessário pra pegar o ID do Produto na inserção
    @GenericGenerator(name = "generator", strategy = "foreign", 
    		parameters = @Parameter(name = "property", value = "produto"))
    		@Id
    		@GeneratedValue(generator = "generator")
    		@Column(name = "codPro", unique = true, nullable = false)
	public int getCodigo() {
		return codigo.get();
	}
    
	public void setCodigo(int codigo) {
		this.codigo.set(codigo);;
	}
	
	@Transient
	public IntegerProperty getCodigoProperty() {
		return codigo;
	}
    
    @OneToOne @PrimaryKeyJoinColumn
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Transient
	private final IntegerProperty quantidade = new SimpleIntegerProperty(this, "quantidade");
	@Column(name="qtdPro", length=9)
	public int getQuatidade() {
		return quantidade.get();
	}

	public void setQuatidade(int quantidade) {
		this.quantidade.set(quantidade);;
	}
	@Transient
	public IntegerProperty getQuatidadeProperty() {
		return quantidade;
	}
}
