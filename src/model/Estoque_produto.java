package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

@Entity
@Table(name="estoque_produto")
public class Estoque_produto extends ModelDefault {
	
	private final IntegerProperty codigo = new SimpleIntegerProperty(this, "codigo");
	@Id
	@Column(name="codPro", length=7)
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
	
	/*
	@OneToOne
	@JoinColumn(name = "codPro")
	@MapsId("codPro")
	private Produto produto;
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}*/
}
