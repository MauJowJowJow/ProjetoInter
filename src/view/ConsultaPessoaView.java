package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ConsultaPessoaView extends ViewDefault {
	
	public ConsultaPessoaView() {
	}

	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/ConsultaPessoaView.fxml", "Consulta de Pessoas", Modality.NONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
