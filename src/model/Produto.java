package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="produto")

public class Produto extends ModelDefault {
	
	@Id
	@Column(name="codPro", length=7)
	@SequenceGenerator(name="ProdutoSequence", sequenceName="hotel.produto_sequence", allocationSize=1)
	@GeneratedValue(generator="ProdutoSequence", strategy=GenerationType.SEQUENCE)
	private int codigo;
	
	@NotNull(message="Informe o nome do produto!")
	@Column(name="desPro", length=30)
	private String descProduto;
	
	@NotNull(message="Informe a unidade de venda do produto!")
	@Column(name="uniPro", length=5)
	private String uniProduto;
	
	@NotNull(message="Informe o valor do produto!")
	@Column(name="vlrUni", length=10)
	private int valorProduto;
	
	@Column(name="codBar", length=13)
	private int codBarra;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	public String getUniProduto() {
		return uniProduto;
	}

	public void setUniProduto(String uniProduto) {
		this.uniProduto = uniProduto;
	}

	public int getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(int valorProduto) {
		this.valorProduto = valorProduto;
	}

	public int getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(int codBarra) {
		this.codBarra = codBarra;
	}
}
