package model.enums;

public enum StatusReserva {
	Aberta("A"), Faturada("F"), Cancelada("C");
	
	public String statusReserva;
	StatusReserva(String statusReserva){
		this.statusReserva = statusReserva;
	}
	
	public String getStatusReserva(){
		return statusReserva;
	}
}
