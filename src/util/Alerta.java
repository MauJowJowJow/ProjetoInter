package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alerta {
	
	public Alerta(){}
	
	public Alerta(String title, String contentText){
		this.title = title;
		this.contentText = contentText;
	}
	
	private String title;
	private String contentText;
	private AlertType alertType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public void Alertar(Stage stage){
		this.alertType = AlertType.WARNING;
		ShowAlert(stage);
	}
	
	public void Mensagem(Stage stage){
		this.alertType = AlertType.INFORMATION;
		ShowAlert(stage);
	}
	
	public void Erro(Stage stage){
		this.alertType = AlertType.ERROR;
		ShowAlert(stage);
	}
	
	private void ShowAlert(Stage stage){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setContentText(contentText);
		
		if(stage != null){
			alert.initModality(Modality.WINDOW_MODAL);
			alert.initOwner(stage);
		}

		alert.showAndWait();
	}
	
	public boolean Confirm(Stage stage){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setContentText(contentText);
    	
    	ButtonType buttonTypeYes = new ButtonType("Sim", ButtonData.YES);
    	ButtonType buttonTypeNo = new ButtonType("Não", ButtonData.NO);
    	
    	alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
    	
    	if(stage != null){
    		alert.initModality(Modality.WINDOW_MODAL);
    		alert.initOwner(stage);
    	}
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get().getButtonData() == ButtonData.YES){
    		return true;
    	}else{
    		return false;
    	}
	}

}
