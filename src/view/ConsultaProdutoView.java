package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ConsultaProdutoView extends ViewDefault {
	public void iniciaTela(Scene parent, Modality modality){
		try {
			start(parent, "fxml/ConsultaProdutoView.fxml", "Consulta de Produtos", modality);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
