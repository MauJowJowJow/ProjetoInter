package model;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.bean.validations.CheckCGC;
import model.dao.PessoaDAO;
import model.enums.EstadoCivil;
import model.enums.PessoaSexo;
import model.enums.SitCadPessoa;
import model.enums.TipoPessoa;;

@CheckCGC(tipoPessoa="tipoPessoa", CGC="CNPJCPF")
@Entity
@Table(name="pessoa")
public class Pessoa extends ModelDefault{

	private final IntegerProperty codigo = new SimpleIntegerProperty(this, "codigo");

	@Id
	@Column(name="codPes", length=7)
    @SequenceGenerator(name = "PessoaSequence", sequenceName = "hotel.pessoa_sequence", allocationSize=1)
	@GeneratedValue(generator = "PessoaSequence", strategy = GenerationType.SEQUENCE)
	public int getCodigo() {
		return codigo.get();
	}
	
	public void setCodigo(int codigo){
		this.codigo.set(codigo);
	}
	
	@Transient
	 public IntegerProperty getCodigoProperty() {
	        return codigo;
	}

	private StringProperty nome = new SimpleStringProperty(this, "nome");
	
	@NotNull(message = "Informe o nome da pessoa!")
	@Column(name="nomPes", length=45)
	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	@Transient
	public StringProperty getNomeProperty(){
		return this.nome;
	}
	
	private ObjectProperty<TipoPessoa> tipoPessoa = new SimpleObjectProperty<TipoPessoa>(this, "tipoPessoa");

	@Column(name="tipPes", length=1)
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa.get();
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa.set(tipoPessoa);
	}
	
	@Transient
	public ObjectProperty<TipoPessoa> getTipoPessoaProperty() {
		return tipoPessoa;
	}
	
	private StringProperty CNPJCPF = new SimpleStringProperty(this, "CNPJCPF");
	@Column(name="CGCPes", length=18)
	public String getCNPJCPF() {
		return CNPJCPF.get();
	}

	public void setCNPJCPF(String CNPJCPF) {
		this.CNPJCPF.set(CNPJCPF);
	}
	
	@Transient
	public StringProperty getCNPJCPFProperty() {
		return CNPJCPF;
	}

	private StringProperty inscricaoEstadual = new SimpleStringProperty(this, "inscricaoEstadual");
	@Column(name="iscEst", length=13)
	public String getInscricaoEstadual() {
		return inscricaoEstadual.get();
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual.set(inscricaoEstadual);
	}
	
	@Transient
	public StringProperty getInscricaoEstadualProperty() {
		return inscricaoEstadual;
	}
	
	private StringProperty email = new SimpleStringProperty(this, "email");
	@Column(name="emaPes", length=45)
	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	
	@Transient
	public StringProperty getEmailProperty() {
		return email;
	}
	
	private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<LocalDate>(this, "dataNascimento");
	@Column(name="datNas")
	public LocalDate getDataNascimento() {		
		return dataNascimento.get();
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento.set(dataNascimento);
	}
	
	@Transient
	public ObjectProperty<LocalDate> getDataNascimentoProperty() {
		return dataNascimento;
	}
	
	private StringProperty telefoneCom = new SimpleStringProperty(this, "telefoneCom");
	@Column(name="telPes", length=15)
	public String getTelefoneCom() {
		return telefoneCom.get();
	}

	public void setTelefoneCom(String telefoneCom) {
		this.telefoneCom.set(telefoneCom);
	}
	
	@Transient
	public StringProperty getTelefoneComProperty() {
		return telefoneCom;
	}
	
	private StringProperty celular = new SimpleStringProperty(this, "celular");
	@Column(name="telPes2", length=15)
	public String getCelular() {
		return celular.get();
	}

	public void setCelular(String celular) {
		this.celular.set(celular);
	}
	
	@Transient
	public StringProperty getCelularProperty() {
		return celular;
	}
	
	private StringProperty telRes = new SimpleStringProperty(this, "telRes");
	@Column(name="telPes3", length=15)
	public String getTelRes() {
		return telRes.get();
	}
	
	public void setTelRes(String telRes) {
		this.telRes.set(telRes);
	}

	@Transient
	public StringProperty getTelResProperty() {
		return telRes;
	}
	
	private ObjectProperty<LocalDate> dataCadastro = new SimpleObjectProperty<LocalDate>(this, "dataCadastro");
	@Column(name="datCad") 
	public LocalDate getDataCadastro() {		
		return dataCadastro.get();
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro.set(dataCadastro);
	}
	
	@Transient
	public ObjectProperty<LocalDate> getDataCadastroProperty() {
		return dataCadastro;
	}
	
	private ObjectProperty<PessoaSexo> sexo = new SimpleObjectProperty<PessoaSexo>(this, "sexo");
	@Column(name="sexPes")
	public PessoaSexo getSexo() {
		return sexo.get();
	}
	
	public void setSexo(PessoaSexo sexo) {
		this.sexo.set(sexo);
	}

	@Transient
	public ObjectProperty<PessoaSexo> getSexoProperty() {
		return sexo;
	}
	
	private ObjectProperty<EstadoCivil> estadoCivil = new SimpleObjectProperty<EstadoCivil>(this, "estadoCivil");
	@Column(name="estCiv")
	public EstadoCivil getEstadoCivil() {
		return estadoCivil.get();
	}
	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil.set(estadoCivil);
	}
	
	@Transient
	public ObjectProperty<EstadoCivil> getEstadoCivilProperty() {
		return estadoCivil;
	}
	
	private ObjectProperty<SitCadPessoa> statusPessoa = new SimpleObjectProperty<SitCadPessoa>(this, "statusPessoa");
	@Column(name="sitPes")
	public SitCadPessoa getstatusPessoa(){
		return statusPessoa.get();
	}
	
	public void setstatusPessoa(SitCadPessoa statusPessoa){
		this.statusPessoa.set(statusPessoa);
	}
	
	@Transient
	public ObjectProperty<SitCadPessoa> getstatusPessoaProperty(){
		return statusPessoa;
	}
	
	public Pessoa exists(){
		PessoaDAO dao = new PessoaDAO();
		Pessoa model = dao.getById(getCodigo());
		
		if(model == null){
			setErrors("Pessoa não cadastrada!");
		}
		return model;
	}
}

