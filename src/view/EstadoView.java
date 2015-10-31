package view;

import controller.EstadoController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EstadoView {
	
	private Stage window;
	BorderPane layout;
	
	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/EstadoView.fxml"));
		Parent root = fxmlLoader.load();
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle("Cadastro de Estados");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		
		EstadoController myController = fxmlLoader.getController();
		myController.setScene(window.getScene());
		
		window.show();		
	}
	
	public Stage getStage(){
		return window;
	}
	
	public EstadoView() {
	}
}


