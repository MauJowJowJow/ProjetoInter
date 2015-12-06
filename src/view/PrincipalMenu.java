package view;

import controller.LoginController;
import controller.PrincipalMenuController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
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
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		window.getIcons().add(new Image(classLoader.getResource("HotelDM.png").toString()));		
		
		fxmlLoader = new FXMLLoader(getClass().getResource("/view/fxml/PrincipalMenuView.fxml"));
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

		window.setTitle("DM Hotel");
		Scene scene = new Scene(root);
	
        /*
        root.setStyle(
            "-fx-background-image: url(" +
                "'Fundo.jpg'" +
            "); "
            + "-fx-background-position: center, center;"
            + "-fx-background-repeat: stretch, stretch;"
            + "-fx-background-size: contain;"
        );*/
		
		
		window.setScene(scene);
		
		PrincipalMenuController myController = fxmlLoader.<PrincipalMenuController>getController();
		myController.setScene(window.getScene());

		Image image = new Image(classLoader.getResource("Fundo.jpg").toString());
		
		BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		Background background = new Background(backgroundImage);
		
		myController.getPanePrincipal().setBackground(background);
		
	    
		window.setMaximized(true);    
		
		window.show();

	}
	
	private boolean loginUsuario(){
		LoginView loginView = new LoginView();
		
		loginView.iniciaTela(null);
	
		LoginController Controller = (LoginController) loginView.getController();
		return Controller.getLogou();
	}
		
}
