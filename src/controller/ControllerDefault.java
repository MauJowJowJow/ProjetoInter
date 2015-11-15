package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.ModelDefault;
import view.ViewDefault;

public class ControllerDefault implements Initializable {
		private ModelDefault model;
		private ViewDefault view;
		private Scene scene;
		private StatusScene statusScene;
		
		@FXML
		private Pane PanePrincipal;
				
		public ControllerDefault() {
		}

		public Scene getScene() {
			return scene;
		}

		public void setScene(Scene scene) {
			this.scene = scene;
		}
		
		public Stage getStage() {
			return (Stage) scene.getWindow();
		}

		public StatusScene getStatusScene() {
			return statusScene;
		}

		public void setStatusScene(StatusScene statusScene) {
			this.statusScene = statusScene;
		}

		public void setModel(ModelDefault model) {
			this.model = model;
		}
		
		public ModelDefault getModel() {
			return model;
		}		
		
		public ViewDefault getView(){
			return this.view;
		}
		
		public void setView(ViewDefault view) {
			this.view = view;
			
			if(view == null) return;
			
			statusScene = StatusScene.Aberto;
			
			this.view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					statusScene = StatusScene.Fechado;
				}
			});
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			for (Node node : PanePrincipal.getChildren()) {
				node.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
					if (!newValue.booleanValue()) {
						if(node instanceof DatePicker){
								((DatePicker)node).setValue(((DatePicker) node).getConverter().fromString(((DatePicker) node).getEditor().getText()));
						}
					}
				});
			}
		}
}
