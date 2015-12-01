package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.Session;

import model.dao.GenericDAOImpl;

@Entity
@Table(name="usuario")
public class Usuario extends ModelDefault{
	
	@Id
	@Column(name="codUsu", length=7)
	private int codigo;
	
	@Column(name="nomUsu", length=50)
	private String nome;
	
	@Column(name="logUsu", length=20)
	private String login;
	
	@Transient
	private String senha;
	
	@Column(name="chaAce", length=10)
	private String chaveHash;
	
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getChaveHash() {
		return chaveHash;
	}

	public void setChaveHash(String chaveHash) {
		this.chaveHash = chaveHash;
	}

	public void geraHash(){
		GenericDAOImpl<Integer, Usuario> dao = new GenericDAOImpl<Integer, Usuario>();
		EntityManager em = dao.getEntityManager();
		EntityManagerFactory emf = dao.getEntityManagerFactory();
		
		try {
			
			// Feito com query padrão, Hibernate não coperou pra utilização de retornos do oracle TYPE por mapeamento 
			em.getTransaction().begin();
			Session session = ((Session) em.getDelegate()); 
			Query query =  session.createSQLQuery("SELECT CRIPTOGRAFAR(:senha) FROM DUAL")
					.setParameter("senha", getSenha());
			
		
			java.util.List result = query.list();
		
			if(result != null){
				String hash = (String) result.get(0);
				
				setChaveHash(hash);
			}
			
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
		
		dao.closeEntity();
	}
}
