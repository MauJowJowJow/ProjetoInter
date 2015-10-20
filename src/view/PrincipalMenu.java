package view;

import java.util.Optional;

import controller.PrincipalMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PrincipalMenu extends Application{
	Stage window;
	BorderPane layout;
	

	public PrincipalMenu() {
	}
	
	public Object Main(String[] args){
		launch(args);
		return this;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		loginUsuario();
		
		window = primaryStage;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrincipalMenu.fxml"));
		Parent root = fxmlLoader.load();

		root.autosize();
		
		Platform.setImplicitExit(false);
		
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        event.consume();
		        
		        Alert alert = new Alert(AlertType.CONFIRMATION);
            	alert.setTitle("Sair do Sistema");
            	alert.setContentText("Deseja realmente sair do Sistema?");
            	alert.initModality(Modality.WINDOW_MODAL);
            	alert.initOwner(window);
            	
            	Optional<ButtonType> result = alert.showAndWait();
            	if (result.get() == ButtonType.OK){
            		window.close();
            	}
		    }
		});

		window.setTitle("HotelDM");
		
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.setMaximized(true);
		
		PrincipalMenuController myController = fxmlLoader.getController();
		myController.setScene(primaryStage.getScene());
		
		window.show();
		
	

	}
	
	private void loginUsuario(){
		Login login = new Login();
		
		login.login();
	}
}
