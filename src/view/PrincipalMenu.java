package view;

import controller.LoginController;
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
	private FXMLLoader fxmlLoader;

	public PrincipalMenu() {
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public FXMLLoader getFxmlLoader(){
		return fxmlLoader;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		if(!loginUsuario()) System.exit(0);

		window = primaryStage;
		
		fxmlLoader = new FXMLLoader(getClass().getResource("fxml/PrincipalMenuView.fxml"));
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
		
		PrincipalMenuController myController = fxmlLoader.<PrincipalMenuController>getController();
		myController.setScene(window.getScene());
		
		window.show();

	}
	
	private boolean loginUsuario(){
		LoginView loginView = new LoginView();
		
		loginView.iniciaTela(null);
	
		LoginController Controller = (LoginController) loginView.getController();
		return Controller.getLogou();
	}
		
}
