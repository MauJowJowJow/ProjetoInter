package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ConsultaPessoaView extends ViewDefault {
	
	public ConsultaPessoaView() {
	}

	public void iniciaTela(Scene parent, Modality modality){
		try {
			start(parent, "/view/fxml/ConsultaPessoaView.fxml", "Consulta de Pessoas", modality);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
