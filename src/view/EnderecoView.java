package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class EnderecoView extends ViewDefault {	
	public EnderecoView() {
	}
	
	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/EnderecoView.fxml", "Cadastro de Endereços", Modality.WINDOW_MODAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
