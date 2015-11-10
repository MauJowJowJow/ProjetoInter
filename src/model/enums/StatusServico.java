package model.enums;

public enum StatusServico {
	Aberto("A"), Fechado("F");
	
	public String statusServico;
	StatusServico(String statusServico){
		this.statusServico = statusServico;
	}
	public String getStatusServico() {
		return statusServico;
	}
}
