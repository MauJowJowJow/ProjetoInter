package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ConsultaPredioView extends ViewDefault{
	
	public ConsultaPredioView(){}
	
	public void iniciaTela(Scene parent, Modality modality){
		try {
			start(parent, "fxml/ConsultaPredio.fxml", "Consulta de Predios", modality);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
