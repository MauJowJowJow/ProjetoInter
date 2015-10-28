package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class Login {
	
	public boolean login(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login");
		dialog.setHeaderText("Realize o login no sistema");
	
		// Set the icon (must be included in the project).
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		dialog.setGraphic(new ImageView(classLoader.getResource("login.png").toString()));
		// Set the button types.
		
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField username = new TextField();
		username.setPromptText("Usuário");
		PasswordField password = new PasswordField();
		password.setPromptText("Senha");
	
		grid.add(new Label("Usuário:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Senha:"), 0, 1);
		grid.add(password, 1, 1);
	
		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
	
		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
	
		dialog.getDialogPane().setContent(grid);
	
		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());
	
		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});
	
		Optional<Pair<String, String>> result = dialog.showAndWait();
	
		result.ifPresent(usernamePassword -> {
		    System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
		});
		
		return true;
	}
}
