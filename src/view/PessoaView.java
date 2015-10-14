package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PessoaView extends Application{

	Stage window;
	BorderPane layout; 
	
	public Object Main(String[] args){
		launch(args);
		return this;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("PessoaView.fxml"));
		
		root.autosize();
		window.setTitle("HotelApp");
		
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		
		window.setMaximized(true);
		window.show();
		
	}
	
	public PessoaView() {
	}
}
