package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class QuartoView extends ViewDefault {
	
	public QuartoView(){}
	
	public void start(Scene parent){
		try{
			start(parent,"fxml/QuartoView.fxml","Cadastro de Quarto", Modality.NONE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
