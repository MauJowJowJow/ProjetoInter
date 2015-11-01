package view;

import controller.EnderecoController;
import javafx.scene.Scene;
import javafx.stage.Modality;
import model.Endereco;

public class EnderecoView extends ViewDefault {	
	public EnderecoView() {
	}
	
	public void iniciaTela(Scene parent, Endereco model){
		try {
			carrega(parent, "fxml/EnderecoView.fxml", "Cadastro de Endereços", Modality.WINDOW_MODAL);
			
			EnderecoController Controller = getFxmlLoader().<EnderecoController>getController();
			Controller.setModel(model);
			
			show(Modality.WINDOW_MODAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
