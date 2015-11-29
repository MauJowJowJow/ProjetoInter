package model.enums;

public enum StatusQuarto {
	Ativo("A"), Inativo("I");
	
	public String statusQuarto;
	StatusQuarto(String statusQuarto){
		this.statusQuarto = statusQuarto;
	}
	public String getStatusQuarto() {
		return statusQuarto;
	}
	public void setStatusQuarto(String statusQuarto) {
		this.statusQuarto = statusQuarto;
	}
	
	@Override
	public String toString(){
		switch(statusQuarto){
			case "A":
				return "Ativo";
			case "I":
				return "Inativo";
			default:
				return "Sem Status";
		}
	}
}
