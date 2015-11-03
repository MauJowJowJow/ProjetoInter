package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

import model.bean.formatters.Hash;
import model.dao.GenericDAO;
import model.pk.EnderecoPK;

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
		
		try {	
			Query query = em.createNamedQuery("geraHash");
			Hash result = (Hash)query.getSingleResult();
			
			if(result != null){
				//Hash hash = (Hash) list.get(0);
				
				usuario.setHashPass(result.getHash());
				usuario.setChaveHash(result.getChave());
			}
			
	    } catch (Exception e) {       
	        e.printStackTrace();
	    }
		
		dao.closeEntity();
		
		return usuario;

	}
}
