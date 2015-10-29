package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EnderecoView {
	private Stage window;
	private FXMLLoader fxmlLoader;
	
	public EnderecoView() {
	}
	
	public FXMLLoader getFxmlLoader(){
		return fxmlLoader;
	}
	
	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EnderecoView.fxml"));
		Parent root = fxmlLoader.load();

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
