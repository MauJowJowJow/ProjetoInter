package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnderecoView {
private Stage window;
	
	public EnderecoView() {
	}

	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		Parent root = FXMLLoader.load(getClass().getResource("fxml/EnderecoView.fxml"));
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle("Cadastro de Endereços");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
	
	public Stage getStage(){
		return window;
	}

}
