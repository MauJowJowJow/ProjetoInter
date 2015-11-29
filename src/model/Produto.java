package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import model.dao.ProdutoDAO;
import model.enums.UniMedProConverter;
import model.enums.UniMedProduto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.DoubleProperty;

@Entity
@Table(name="produto")

public class Produto extends ModelDefault {
	
	private final IntegerProperty codigo = new SimpleIntegerProperty(this, "codigo");
	@Id
	@Column(name="codPro", length=7)
	@SequenceGenerator(name="ProdutoSequence", sequenceName="hotel.produto_sequence", allocationSize=1)
	@GeneratedValue(generator="ProdutoSequence", strategy=GenerationType.SEQUENCE)
	public int getCodigo() {
		return codigo.get();
	}

	public void setCodigo(int codigo) {
		this.codigo.set(codigo);
	}
	@Transient
	public IntegerProperty getCodigoProperty() {
		return codigo;
	}
	
	Estoque_produto estoque_produto = new Estoque_produto();	
	@OneToOne
	@JoinColumn(name="codPro")	
	public Estoque_produto getEstoque_produto() {
		return estoque_produto;
	}
	
	public void setEstoque_produto(Estoque_produto estoque_produto) {
		this.estoque_produto = estoque_produto;
	}
	
	private StringProperty descProduto = new SimpleStringProperty(this, "descProduto");
	@NotNull(message="Informe o nome do produto!")
	@Column(name="desPro", length=30)
	public String getDescProduto() {
		return descProduto.get();
	}

	public void setDescProduto(String descProduto) {
		this.descProduto.set(descProduto);;
	}
	@Transient
	public StringProperty getDescProdutoProperty(){
		return descProduto;
	}
	
	private ObjectProperty<UniMedProduto> uniMedProduto = new SimpleObjectProperty<UniMedProduto>(this, "uniMedProduto");
	
	@NotNull(message="Informe a unidade de venda do produto!")
	@Column(name="uniPro", length=5)
	@Convert(converter = UniMedProConverter.class)
	public UniMedProduto getUniProduto() {
		return uniMedProduto.get();
	}
	public void setUniProduto(UniMedProduto uniMedProduto) {
		this.uniMedProduto.set(uniMedProduto);;
	}
	@Transient
	public ObjectProperty<UniMedProduto> getUniProdutoProperty() {
		return uniMedProduto;
	}
	
	private final DoubleProperty valorProduto = new SimpleDoubleProperty(this, "valorProduto");
	@NotNull(message="Informe o valor do produto!")
	@Column(name="vlrUni", length=12)
	public Double getValorProduto() {
		return valorProduto.get();
	}
	public void setValorProduto(Double valorProduto) {
		this.valorProduto.set(valorProduto);
	}
	@Transient
	public DoubleProperty getValorProdutoProperty(){
		return valorProduto;
	}
	
	private final IntegerProperty codBarra = new SimpleIntegerProperty(this, "codBarra");
	@Column(name="codBar", length=13)
	public int getCodBarra() {
		return codBarra.get();
	}
	public void setCodBarra(int codBarra) {
		this.codBarra.set(codBarra);;
	}
	@Transient
	public IntegerProperty getCodBarraProperty() {
		return codBarra;
	}

	
	public Produto exists(){
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.getById(getCodigo());
		
		if(produto == null){
			setErrors("Produto não cadastrado!");
		}
		return produto;
	}
	

}
