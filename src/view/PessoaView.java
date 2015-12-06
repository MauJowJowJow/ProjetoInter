package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class PessoaView extends ViewDefault{
	
	public PessoaView() {
	}	
	
	public void iniciaTela(Scene parent){
		try {
			start(parent, "/view/fxml/PessoaView.fxml", "Cadastro de Pessoas", Modality.NONE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}
