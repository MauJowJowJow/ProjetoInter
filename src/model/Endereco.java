package model;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import model.dao.GenericDAO;
import model.pk.EnderecoPK;

@Entity
@Table(name = "endereco")
public class Endereco extends ModelDefault {

	@EmbeddedId
	private EnderecoPK pk;

	@NotNull(message = "Informe o CEP (Somente números).")
	@Column(name = "CEPEnd", length = 9)
	private int CEP;

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

	public int getCEP() {
		return CEP;
	}

	public void setCEP(int cEP) {
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
		GenericDAO<EnderecoPK, Endereco> dao = new GenericDAO<EnderecoPK, Endereco>();
		EntityManager em = dao.getEntityManager();
		
		try {	
			Query query = em.createNativeQuery("select serializaEndereco(?) from dual");
			query.setParameter(1, getPk().getCodigoPessoa());
			
			java.util.List list = query.getResultList();
			
			getPk().setCodigo(((java.math.BigDecimal)list.get(0)).intValue());
			
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
	}
}
