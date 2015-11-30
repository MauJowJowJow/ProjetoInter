package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ServicoView extends ViewDefault {
	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/ServicoView.fxml", "Cadastro de Serviços", Modality.NONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
