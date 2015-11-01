package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.stage.WindowEvent;
import model.ModelDefault;
import view.ViewDefault;

public class ControllerDefault implements Initializable {
		private ModelDefault model;
		private ViewDefault view;
		private StatusScene statusScene;
				
		public ControllerDefault() {
			this.model = new ModelDefault();
			this.view = new ViewDefault();
		}

		public void setModel(ModelDefault model) {
			this.model = model;
		}
		
		public ModelDefault getModel() {
			return model;
		}		
		
		public StatusScene getStatus(){
			return statusScene;
		}
		
		public void setStatus(StatusScene statusScene){
			this.statusScene = statusScene;
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
		
		}
}
