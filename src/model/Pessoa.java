package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;

import model.bean.BindableModel;
import model.bean.validations.CheckCGC;
import model.enums.EstadoCivil;
import model.enums.TipoPessoa;;

@Entity
@Table(name="pessoa")
@CheckCGC(tipoPessoa="tipoPessoa", CGC="CNPJCPF")
public class Pessoa extends BindableModel {
	
	@Id
	@Column(name="codPes", length=7)
    @SequenceGenerator(name = "PessoaSequence", sequenceName = "hotel.pessoa_sequence", allocationSize=1)
	@GeneratedValue(generator = "PessoaSequence", strategy = GenerationType.SEQUENCE)
	private int codigo;

	@NotNull(message = "Informe o nome da pessoa!")
	@Column(name="nomPes", length=45)
	private String nome;
	
	@Column(name="tipPes", length=1)
	private TipoPessoa tipoPessoa;
	
	@NotNull(message = "Informe o CPF/CNPJ da pessoa!")
	@CheckCGC(tipoPessoa = "tipoPessoa", CGC = "CNPJCPF")
	@Column(name="CGCPes", length=18)
	private String CNPJCPF;
	
	@Column(name="iscEst", length=10)
	private int inscricaoEstadual;
	
	@Column(name="emaPes", length=45)
	private String email;
	
	@Column(name="datNas")
	private Date dataNascimento;
	
	@Column(name="telPes", length=13)
	private String telefone;
	
	@Column(name="telPes2", length=13)
	private String celular;
	
	@Column(name="claPes", length=20)// Verificar Enum
	private String classe;
	
	@Column(name="datCad") // Verificar Calendar
	private Date dataCadastro;
	
	@Column(name="sexPes")
	private String sexo;
	
	@Column(name="estCiv")
	private EstadoCivil estadoCivil;
	
	@Column(name="codEnd")
	private int codigoEndereco;

	public int getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
        final String oldValue = this.nome;
		this.nome = nome;
		this.firePropertyChange("nome", oldValue, nome);
	}


	public String getCNPJCPF() {
		return CNPJCPF;
	}

	public void setCNPJCPF(String CNPJCPF) {
		final String oldValue = this.CNPJCPF;
		this.CNPJCPF = CNPJCPF;
		this.firePropertyChange("CNPJCPF", oldValue, CNPJCPF);
	}


	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public int getInscricaoEstadual() {
		return inscricaoEstadual;
	}


	public void setInscricaoEstadual(int inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public int getCodigoEndereco() {
		return codigoEndereco;
	}


	public void setCodigoEndereco(int codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}
	
	public static boolean validaCPF(String vrCPF){
		return true;
	}
	
	public static boolean validaCNPJ(String CPF){
		return true;
	}

	public boolean isValidPerson() {	 
	    // valores sao setados no objeto
	 
	    final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	 
	    final Set<ConstraintViolation<Pessoa>> violations = validator.validate(this);
	 
	    if (!violations.isEmpty()) {
	        for (ConstraintViolation violation : violations) {
	            System.out.println(violation.getMessage());
	        }
	        return false;
	    }
	    return true;
	}
}

