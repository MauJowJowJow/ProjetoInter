package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ConsultaQuartoView extends ViewDefault {
	public void iniciaTela(Scene parent, Modality modality){
		try {
			start(parent, "/view/fxml/ConsultaQuartoView.fxml", "Consulta de Quartos", modality);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
