package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "usuario")
public class Usuario extends ModelDefault{
	
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
	  //nomUsu VARCHAR(50),
	//senUsu RAW(150),
	//chaAce VARCHAR(10));
}
