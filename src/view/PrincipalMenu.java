package view;

import controller.PrincipalMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Alerta;

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
		        
		        	Alerta alerta = new Alerta("Sair do Sistema", "Deseja sair do sistema?");
		        	
		        	if(alerta.Confirm(window)){
		        		window.close();
		        		System.exit(0);
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
