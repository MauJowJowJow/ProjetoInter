package model.enums;

public enum EstadoCivil{
	Solteiro(0), Casado(1), Divorciado(2), Viúvo(3), Amasiado(4);
	
	public int estadoCivil;
	EstadoCivil(int estadoCivil){
		this.estadoCivil = estadoCivil;
	}
	
	public int getEstadoCivil(){
		return estadoCivil;
	}
}