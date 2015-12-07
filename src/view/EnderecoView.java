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
			carrega(parent, "/view/fxml/EnderecoView.fxml", "Cadastro de Endere�os", Modality.WINDOW_MODAL);
			
			EnderecoController Controller = getFxmlLoader().<EnderecoController>getController();
			Controller.setModel(model);
			Controller.setPessoa(model.getPk().getPessoa());
			
			show(Modality.WINDOW_MODAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
