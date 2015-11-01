package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewDefault {
	private Stage window;
	private FXMLLoader fxmlLoader;
	
	public ViewDefault() {
	}
	
	public FXMLLoader getFxmlLoader(){
		return fxmlLoader;
	}
	
	public Stage getStage(){
		return window;
	}

	public void start(Scene parent, String caminhoFXML, String title, Modality modality) throws Exception{
		window = new Stage();
		
		if (parent != null) 
			window.initOwner(parent.getWindow());
		
		window.initModality(modality);
		
		fxmlLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
		Parent root = fxmlLoader.load();
		
		root.autosize();
		
		window.setResizable(false);
		window.setTitle(title);
		
		Scene scene = new Scene(root);

		window.setScene(scene);
		window.show();		
	}
}
