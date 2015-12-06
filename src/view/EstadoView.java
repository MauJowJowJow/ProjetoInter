package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class EstadoView extends ViewDefault {
	
	public EstadoView(){}
	
	public void start(Scene parent){
		try{
			start(parent, "/view/fxml/EstadoView.fxml","Cadastro de estado", Modality.NONE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


