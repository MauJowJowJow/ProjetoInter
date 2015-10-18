package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalMenu extends Application{
	Stage window;
	BorderPane layout; 
	
	public Object Main(String[] args){
		launch(args);
		return this;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("PrincipalMenu.fxml"));
		
		root.autosize();

		window.setTitle("Hotel");
		
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.show();
		
	}
	
	public PrincipalMenu() {
	}
}
