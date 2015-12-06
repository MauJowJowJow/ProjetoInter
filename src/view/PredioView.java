package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class PredioView extends ViewDefault {
	
	public PredioView(){}
	
	public void start(Scene parent){
		try{
			start(parent,"/view/fxml/PredioView.fxml","Cadastro de Prédio", Modality.NONE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}