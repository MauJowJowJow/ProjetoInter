package view;

import javafx.scene.Scene;
import javafx.stage.Modality;

public class LoginView extends ViewDefault{
	
	public LoginView(){
	}

	public void iniciaTela(Scene parent){
		try {
			start(parent, "/view/fxml/LoginView.fxml", "Login", Modality.APPLICATION_MODAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
