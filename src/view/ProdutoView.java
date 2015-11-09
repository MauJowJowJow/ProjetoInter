package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class ProdutoView extends ViewDefault{
	public ProdutoView() {
	}

	public void iniciaTela(Scene parent){
		try {
			start(parent, "fxml/ProdutoView.fxml", "Cadastro de Produtos", Modality.NONE);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
