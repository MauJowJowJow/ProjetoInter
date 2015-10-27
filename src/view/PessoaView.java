package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PessoaView{

	private Stage window;
	BorderPane layout; 

	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initOwner(parent.getWindow());
		
		Parent root = FXMLLoader.load(getClass().getResource("fxml/PessoaView.fxml"));
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle("Cadastro de Pessoas");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
	
	public Stage getStage(){
		return window;
	}
	
	public PessoaView() {
	}
}
