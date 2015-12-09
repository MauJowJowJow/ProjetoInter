package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class RelatorioCheckOutView extends ViewDefault {
	public void start(Scene parent){
		try{
			start(parent, "/view/fxml/RelatorioCheckOutView.fxml","Relatório de CheckOut", Modality.NONE);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
