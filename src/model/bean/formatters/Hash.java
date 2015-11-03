package model.bean.formatters;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.eclipse.persistence.annotations.Struct;
import org.eclipse.persistence.platform.database.oracle.annotations.NamedPLSQLStoredFunctionQuery;
import org.eclipse.persistence.platform.database.oracle.annotations.PLSQLParameter;

@NamedPLSQLStoredFunctionQuery(name="geraHash", functionName="ENCRYPTED_KEY.get_ups",parameters={@PLSQLParameter(name = "p_input", databaseType = "VARCHAR2")},
	returnParameter=@PLSQLParameter(name="encrypted_table", databaseType="ENCRYPTED_KEY.encrypted"))
@Embeddable
@Struct(name="encrypted", fields={"v_hash", "v_key"})
public class Hash {
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
