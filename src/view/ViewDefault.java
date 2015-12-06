package view;

import controller.ControllerDefault;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewDefault {
	private Stage window;
	private FXMLLoader fxmlLoader;
	private ControllerDefault controller;
	
	public ViewDefault() {
	}
	
	public FXMLLoader getFxmlLoader(){
		return fxmlLoader;
	}
	
	public Stage getStage(){
		return window;
	}
	
	public ControllerDefault getController(){
		return controller;
	}

	public void carrega(Scene parent, String caminhoFXML, String title, Modality modality) throws Exception{
		carregaScene(parent, caminhoFXML, title, modality);
	}
	
	public void start(Scene parent, String caminhoFXML, String title, Modality modality) throws Exception{
		carregaScene(parent, caminhoFXML, title, modality);
		
		show(modality);
	}
	
	private void carregaScene(Scene parent, String caminhoFXML, String title, Modality modality) throws Exception{
		window = new Stage();
		window.getIcons().add(new Image(Thread.currentThread().getContextClassLoader().getResource("HotelDM.png").toString()));
		
		if (parent != null) 
			window.initOwner(parent.getWindow());
		
		window.initModality(modality);

		fxmlLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
	
		Parent root = fxmlLoader.load();
		
		controller = fxmlLoader.<ControllerDefault>getController();
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle(title);
		
		Scene scene = new Scene(root);

		if(controller != null){
			controller.setView(this);
			controller.setScene(scene);
		}
		
		window.setScene(scene);
	}
	
	public void show(Modality modality){
		if(modality == Modality.WINDOW_MODAL || modality == Modality.APPLICATION_MODAL)
			window.showAndWait();
		else
			window.show();
	}
}
