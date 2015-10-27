package model.enums;

public enum SitCadPessoa {
	Ativo(0), Inativo(1);
	
	public int sitCadPessoa;
	SitCadPessoa(int sitCadPessoa){
		this.sitCadPessoa = sitCadPessoa;
	}
	
	public int getsitCadPesssoa(){
		return sitCadPessoa;
	}
}
