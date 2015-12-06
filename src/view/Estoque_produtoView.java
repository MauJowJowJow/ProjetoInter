package view;

import controller.Estoque_produtoController;
import javafx.scene.Scene;
import javafx.stage.Modality;
import model.Estoque_produto;

public class Estoque_produtoView extends ViewDefault {

	public Estoque_produtoView() {}
	
	public void iniciaTela(Scene parent, Estoque_produto model){
		try{
			carrega(parent,"/view/fxml/Estoque_produtoView.fxml","Cadastro de Estoque", Modality.NONE);
			
			Estoque_produtoController Controller = getFxmlLoader().<Estoque_produtoController>getController();
			Controller.setEstoque(model);
			
			show(Modality.WINDOW_MODAL);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
