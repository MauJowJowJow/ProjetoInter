package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;

public class LoginController extends ControllerDefault{
	private boolean logou;
	
	@FXML 
	private TextField txtLogin;
	
	@FXML 
	private PasswordField txtSenha;
	
	@FXML
	private Button btnLogin;
	
	@FXML
	private Button btnCancel;
	
	@FXML
	private ImageView imgLogin;
	
	public boolean getLogou(){
		return logou;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    imgLogin.setImage(new Image(classLoader.getResource("Login.png").toString()));
	    
	    btnLogin.setOnAction(evt -> {
	    	Usuario usuario = new Usuario();
	    	
	    	this.logou = true;
	    	
	    	usuario.setLogin(txtLogin.getText());
	    	usuario.setSenha(txtSenha.getText());
	    	
	    	//usuario.geraHash(usuario);
	    	
	    	Stage stage = (Stage) getScene().getWindow();
	    	stage.close();
	    });
	    
	    btnCancel.setOnAction(evt -> {
	    	this.logou = false;
	    	
	    	Stage stage = (Stage) getScene().getWindow();
	    	stage.close();
	    });
	    
	    btnLogin.setDisable(true);
	    txtLogin.textProperty().addListener((observable, oldValue, newValue) -> {
	    	btnLogin.setDisable(txtLogin.getText().isEmpty());
	    });
	}
}
