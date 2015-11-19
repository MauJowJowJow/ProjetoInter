package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.DoubleProperty;
import model.enums.StatusQuarto;
import model.enums.StatusQuartoConverter;
import model.dao.QuartoDAO;

@Entity
@Table(name="quarto")
public class Quarto extends ModelDefault{
	
	private final IntegerProperty codigo = new SimpleIntegerProperty(this, "codigo");
	@Id
	@Column(name="codQua", length=7)
	@SequenceGenerator(name="QuartoSequence", sequenceName="hotel.quarto_sequence", allocationSize=1)
	@GeneratedValue(generator="QuartoSequence", strategy=GenerationType.SEQUENCE)
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
	
	private StringProperty nome = new SimpleStringProperty(this, "nome");
	@NotNull(message="Informe o nome do quarto!")
	@Column(name="desQua", length=35)
	public String getNome() {
		return nome.get();
	}
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	@Transient
	public StringProperty getNomeProperty(){
		return nome;
	}
	private final IntegerProperty andarQuarto = new SimpleIntegerProperty(this, "andarQuarto");
	@NotNull(message="Informe o andar do quarto!")
	@Column(name="andQua", length=3)
	public int getAndarQuarto() {
		return andarQuarto.get();
	}
	public void setAndarQuarto(int andarQuarto) {
		this.andarQuarto.set(andarQuarto);
	}
	@Transient
	public IntegerProperty getAndarQuartoProperty(){
		return andarQuarto;
	}
	
	private final IntegerProperty dormitorios = new SimpleIntegerProperty(this, "dormitorios");
	@NotNull(message="Informe a quantidade de dormitorios no quarto!")
	@Column(name="qtdDor", length=2)
	public int getDormitorios() {
		return dormitorios.get();
	}
	public void setDormitorios(int dormitorios) {
		this.dormitorios.set(dormitorios);
	}
	@Transient
	public IntegerProperty getDormitoriosProperty(){
		return dormitorios;
	}

	private final DoubleProperty valorQuarto = new SimpleDoubleProperty(this, "valorQuarto");
	@NotNull(message="Informe o valor da diária!")
	@Column(name="vlrQua", length=12)
	public Double getValorQuarto() {
		return valorQuarto.get();
	}
	public void setValorQuarto(Double valorQuarto) {
		this.valorQuarto.set(valorQuarto);
	}
	@Transient
	public DoubleProperty getValorQuartoProperty(){
		return valorQuarto;
	}
	
	private ObjectProperty<StatusQuarto> statusQuarto = new SimpleObjectProperty<StatusQuarto>(this, "statusQuarto");
	@NotNull(message="Informe o status do quarto!")
	@Column(name="staQua", length=2)
	@Convert(converter = StatusQuartoConverter.class)
	public StatusQuarto getStatusQuarto() {
		return statusQuarto.get();
	}
	public void setStatusQuarto(StatusQuarto statusQuarto) {
		this.statusQuarto.set(statusQuarto);
	}
	@Transient
	public ObjectProperty<StatusQuarto> getStatusQuartoProperty() {
		return statusQuarto;
	}
	
	private Predio predio = new Predio();	
	@ManyToOne
	@JoinColumn(name="codPre")	
	public Predio getPredio() {
		return predio;
	}
	
	public void setPredio(Predio predio) {
		this.predio = predio;
	}
	
	private StringProperty descricao = new SimpleStringProperty(this, "descricao");
	@Column(name="comQua", length=300)
	public String getDescricao() {
		return descricao.get();
	}

	public void setDescricao(String descricao) {
		this.descricao.set(descricao);;
	}
	@Transient
	public StringProperty getDescricaoProperty() {
		return descricao;
	}
	
	@Transient
	private IntegerProperty codigoPredio;
	
	@Transient
	public IntegerProperty getCodigoPredio(){
		codigoPredio = getPredio().getCodigoPredioProperty(); 
		return codigoPredio;
	}
	
	public Quarto exists(){
		QuartoDAO dao = new QuartoDAO();
		Quarto quarto = dao.getById(getCodigo());
		
		if(quarto == null){
			setErrors("Quarto não cadastrado!");
		}
		return quarto;
	}
}
