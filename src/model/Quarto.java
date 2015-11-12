package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import model.enums.StatusQuarto;
import model.enums.StatusQuartoConverter;
import model.dao.QuartoDAO;
import util.Alerta;

@Entity
@Table(name="quarto")
public class Quarto extends ModelDefault{

	@Id
	@Column(name="codQua", length=7)
	@SequenceGenerator(name="QuartoSequence", sequenceName="hotel.quarto_sequence", allocationSize=1)
	@GeneratedValue(generator="QuartoSequence", strategy=GenerationType.SEQUENCE)
	private int codigo;
	
	@NotNull(message="Informe o nome do quarto!")
	@Column(name="desQua", length=35)
	private String nome;
	
	@NotNull(message="Informe o andar do quarto!")
	@Column(name="andQua", length=3)
	private int andarQuarto;
	
	@NotNull(message="Informe a quantidade de dormitorios no quarto!")
	@Column(name="qtdDor", length=2)
	private int dormitorios;
	
	@NotNull(message="Informe o valor da diária!")
	@Column(name="vlrQua", length=12)
	private int valorQuarto;
	
	@NotNull(message="Informe o status do quarto!")
	@Column(name="staQua", length=2)
	@Convert(converter = StatusQuartoConverter.class)
	private StatusQuarto statusQuarto;
	
	@NotNull(message="Informe o código do predio!")
	@Column(name="codPre", length=7)
	private int codigoPredio;
	
	@Column(name="comQua", length=300)
	private String descricao ;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getAndarQuarto() {
		return andarQuarto;
	}

	public void setAndarQuarto(int andarQuarto) {
		this.andarQuarto = andarQuarto;
	}

	public int getDormitorios() {
		return dormitorios;
	}

	public void setDormitorios(int dormitorios) {
		this.dormitorios = dormitorios;
	}

	public int getValorQuarto() {
		return valorQuarto;
	}

	public void setValorQuarto(int valorQuarto) {
		this.valorQuarto = valorQuarto;
	}

	public StatusQuarto getStatusQuarto() {
		return statusQuarto;
	}

	public void setStatusQuarto(StatusQuarto statusQuarto) {
		this.statusQuarto = statusQuarto;
	}

	public int getCodigoPredio() {
		return codigoPredio;
	}

	public void setCodigoPredio(int codigoPredio) {
		this.codigoPredio = codigoPredio;
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
