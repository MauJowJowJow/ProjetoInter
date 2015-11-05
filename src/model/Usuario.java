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

import model.bean.formatters.encrypted;
import model.dao.GenericDAO;

@Entity
@Table(name="usuario")
public class Usuario extends ModelDefault{
	
	@Id
	@Column(name="chaUsu", length=7)
	private int codigo;
	
	@Column(name="nomUsu", length=50)
	private String nome;
	
	@Column(name="logUsu", length=20)
	private String login;
	
	@Transient
	private String senha;
	
	@Column(name="senUsu", length=10)
	private byte[] hashPass;
	
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

	public byte[] getHashPass() {
		return hashPass;
	}

	public void setHashPass(byte[] hashPass) {
		this.hashPass = hashPass;
	}

	public String getChaveHash() {
		return chaveHash;
	}

	public void setChaveHash(String chaveHash) {
		this.chaveHash = chaveHash;
	}

	static public Usuario geraHash(Usuario usuario){
		GenericDAO<Integer, Usuario> dao = new GenericDAO<Integer, Usuario>();
		EntityManager em = dao.getEntityManager();
		EntityManagerFactory emf = dao.getEntityManagerFactory();
		
		try {	
			/*DataReadQuery databaseQuery = new DataReadQuery();
			databaseQuery.setResultType(DataReadQuery.VALUE);
			PLSQLrecord record = new PLSQLrecord();
			record.setTypeName("ENCRYPTED_KEY.get_ups");
			record.setCompatibleType("encrypted_type");
			record.setJavaType(Hash.class);
			record.addField("v_hash", JDBCTypes.BINARY_TYPE, 150);
			record.addField("v_key", JDBCTypes.VARCHAR_TYPE, 10);
			PLSQLStoredFunctionCall call = new PLSQLStoredFunctionCall(record);
			call.setProcedureName("ENCRYPTED_KEY.get_ups");
			databaseQuery.setCall(call);*/
			 
			//Query query = ((JpaEntityManager) em).createQuery(databaseQuery);
			em.getTransaction().begin();
			Session session = ((Session) em.getDelegate()); 
			Query query =  session.createSQLQuery("select v_hash, v_key from table (ENCRYPTED_KEY.get_ups(:senha))"
					).addEntity(encrypted.class)
					.setParameter("senha", usuario.getSenha());
			
		
			java.util.List result = query.list();
		
			if(result != null){
				encrypted hash = (encrypted) result.get(0);
				
				usuario.setHashPass(hash.getHash());
				usuario.setChaveHash(hash.getChave());
			}
			
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
		
		dao.closeEntity();
		
		return usuario;

	}
}
