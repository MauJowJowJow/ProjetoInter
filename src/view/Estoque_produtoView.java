package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class Estoque_produtoView extends ViewDefault {

	public Estoque_produtoView() {}
	
	public void iniciaTela(Scene parent){
		try{
			start(parent,"fxml/Estoque_produtoView.fxml","Cadastro de Estoque", Modality.NONE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
