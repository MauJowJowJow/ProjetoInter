package model.bean.formatters;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.DatePicker;

public class CustomDatePicker extends DatePicker {

	public void inicia(){
		this.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (!newValue.booleanValue()) {
				this.setValue(this.getConverter().fromString(this.getEditor().getText()));
				Event.fireEvent(this, new ActionEvent());
			}
		});
	}
}
