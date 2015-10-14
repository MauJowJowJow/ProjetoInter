package model.enums;

public enum TipoPessoa {
	Fisica(0), Juridica(1);
		
	public int tipoPessoa;
	TipoPessoa(int tipoPessoa){
		this.tipoPessoa = tipoPessoa;
	}
	
	public int getTipoPessoa(){
		return tipoPessoa;
	}
}
