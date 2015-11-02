package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ReservaView extends ViewDefault{
	public ReservaView(){
		
	}
	
	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/ReservaView.fxml", "Cadastro de Reservas", Modality.NONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
