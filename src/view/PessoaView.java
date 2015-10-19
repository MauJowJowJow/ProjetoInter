package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PessoaView{

	Stage window;
	BorderPane layout; 
	
	//public Object Main(String[] args){
		//launch(args);
		//return this;
	//}
	
	public void start(Scene parent) throws Exception{
		window = new Stage();
		
		window.initModality(Modality.WINDOW_MODAL);
		window.initOwner(parent.getWindow());
		
		
		Parent root = FXMLLoader.load(getClass().getResource("PessoaView.fxml"));
		
		root.autosize();
		window.setTitle("Cadastro de Pessoas");
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
	
	public PessoaView() {
	}
}
