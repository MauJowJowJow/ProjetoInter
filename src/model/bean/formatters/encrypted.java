package model.bean.formatters;

import javax.persistence.Column;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

public class encrypted {
	@Column(name = "v_hash")
	private byte[] hash;
	
	@Column(name = "v_key")
	private String chave;

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}
}
