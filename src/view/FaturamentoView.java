package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class FaturamentoView extends ViewDefault{
	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/FaturamentoView.fxml", "Cadastro de Faturamento", Modality.NONE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
