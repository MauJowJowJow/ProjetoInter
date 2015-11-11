package model.enums;

public enum StatusQuarto {
	Ativo("A"), Inativo("I");
	
	public String statusQuarto;
	StatusQuarto(String statusQuarto){
		this.statusQuarto = statusQuarto;
	}
	
	public String getStatusQuarto(){
		return statusQuarto;
	}

}
