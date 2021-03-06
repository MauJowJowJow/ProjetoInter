package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import model.dao.GenericDAOImpl;
import model.pk.EnderecoPK;

@Entity
@Table(name = "endereco")
public class Endereco extends ModelDefault {

	@EmbeddedId
	private EnderecoPK pk;

	@NotNull(message = "Informe o CEP (Somente n�meros).")
	@Column(name = "CEPEnd", length = 9)
	private String CEP;

	@NotNull(message = "Informe o nome da rua!")
	@Column(name = "logEnd", length = 50)
	private String logradouro;

	@Column(name = "numEnd", length = 9)
	private int numeroEnd;

	@Column(name = "comEnd", length = 60)
	private String complemento;

	@NotNull(message = "Informe o bairro!")
	@Column(name = "baiEnd", length = 40)
	private String bairro;

	@Column(name = "codCid")
	private int codigoCidade;

	public EnderecoPK getPk() {
		return pk;
	}

	public void setPk(EnderecoPK pk) {
		this.pk = pk;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumeroEnd() {
		return numeroEnd;
	}

	public void setNumeroEnd(int numeroEnd) {
		this.numeroEnd = numeroEnd;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(int codigoCidade) {
		this.codigoCidade = codigoCidade;
	}

	public Endereco() {

	}

	public Endereco(EnderecoPK pk) {
		this.pk = pk;
	}

	public void geraCodigo(){
		GenericDAOImpl<EnderecoPK, Endereco> dao = new GenericDAOImpl<EnderecoPK, Endereco>();
		EntityManager em = dao.getEntityManager();
		
		try {	
			Query query = em.createNativeQuery("select serializaEndereco(?) from dual");
			query.setParameter(1, getPk().getPessoa().getCodigo());
			
			java.util.List list = query.getResultList();
			
			getPk().setCodigo(((java.math.BigDecimal)list.get(0)).intValue());
			
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
	}
}
