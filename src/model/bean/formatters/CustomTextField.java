package model.bean.formatters;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.TextField;

public class CustomTextField extends TextField{		
		public void inicia(){
			this.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
				if (!newValue.booleanValue()) {
					Event.fireEvent(this, new ActionEvent());
				}
			});			
		}
}
