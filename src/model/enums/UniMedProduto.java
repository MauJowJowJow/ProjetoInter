package model.enums;

public enum UniMedProduto {
	KG("KG"),LT("LT"),PC("PC"),MT("MT"),UN("UN");
	
	public String uniMedProduto;
	UniMedProduto(String uniMedProduto){
		this.uniMedProduto = uniMedProduto;
	}
	public String getStatusQuarto() {
		return uniMedProduto;
	}
	public void setStatusQuarto(String statusQuarto) {
		this.uniMedProduto = statusQuarto;
	}
}
