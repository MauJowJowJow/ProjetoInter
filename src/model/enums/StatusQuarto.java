package model.enums;

public enum StatusQuarto {
	Disponivel("D"), Reservado("R"), Inativo("I");
	
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
			case "D":
				return "Disponível";
			case "R":
				return "Reservado";
			case "I":
				return "Inativo";
			default:
				return "Sem Status";
		}
	}
}
