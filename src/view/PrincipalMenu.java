package view;

import controller.PrincipalMenuController;
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
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Parent root = fxmlLoader.load(getClass().getResource("PrincipalMenu.fxml"));
		
		PrincipalMenuController myController = (PrincipalMenuController) fxmlLoader.getController();
		myController.setScene(primaryStage.getScene());
		
		root.autosize();

		window.setTitle("HotelDM");
		
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setMaximized(true);
		window.show();
		
	}
	
	public PrincipalMenu() {
	}
}
