package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdutoView {

	private Stage window;
	
	public ProdutoView() {
	}

	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		Parent root = FXMLLoader.load(getClass().getResource("fxml/ProdutoView.fxml"));
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle("Cadastro de Produtos");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
	
	public Stage getStage(){
		return window;
	}
}
