package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConsultaPessoaView {
	private Stage window;
	BorderPane layout; 
	
	public ConsultaPessoaView() {
	}

	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		Parent root = FXMLLoader.load(getClass().getResource("fxml/ConsultaPessoa.fxml"));
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle("Consulta de Pessoas");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
	
	public Stage getStage(){
		return window;
	}
}
