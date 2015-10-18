package model.enums;

public enum PessoaSexo {
	Feminino(0), Masculino(1);
	
	public int pessoaSexo;
	PessoaSexo(int pessoaSexo){
		this.pessoaSexo = pessoaSexo;
	}
	
	public int getPessoaSexo(){
		return pessoaSexo;
	}
}
