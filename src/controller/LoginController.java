package controller;

import java.util.List;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Usuario;
import model.dao.UsuarioDAO;
import util.Alerta;

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
	    imgLogin.setImage(new Image(classLoader.getResource("login.png").toString()));
	    
	    btnLogin.setOnAction(evt -> {
	    	Usuario usuario = new Usuario();
	    	UsuarioDAO dao = new UsuarioDAO();
	    	
	    	usuario.setLogin(txtLogin.getText());
	    	usuario.setSenha(txtSenha.getText());
	    	
	    	usuario.geraHash();
	    	
	    	HashMap<String, Object> parametros = new HashMap<String, Object>();
	    	parametros.put("login", usuario.getLogin());
	    	
	    	List<Usuario> usuarios = dao.query("Select u FROM Usuario u WHERE u.login = :login", parametros);
	    	Usuario usuarioDB = null;
	    	Alerta alerta;
	    	switch(usuarios.size()){
		    	case 0:
		    		alerta = new Alerta(getStage().getTitle(), "Usuário não encontrado!");
		    		alerta.Erro(getStage());
		    		txtLogin.requestFocus();
		    		break;
		    	case 1:
		    		usuarioDB = usuarios.get(0);
			    	
			    	if(usuarioDB.getChaveHash().equals(usuario.getChaveHash())){
			    		this.logou = true;
			    	
			    		getStage().close();
			    	}else{
			    		alerta = new Alerta(getStage().getTitle(), "Senha informada inválida!");
			    		alerta.Erro(getStage());
			    		
			    		txtSenha.requestFocus();
			    	}
			    	
		    		break;
	    		default:
	    			alerta = new Alerta(getStage().getTitle(), "Usuário não encontrado!");
	    			alerta.Erro(getStage());
	    			System.exit(0);
	    	}
	    });

	    btnCancel.setOnAction(evt -> {
	    	this.logou = false;
	    	
	    	getStage().close();
	    });
	    
	    btnLogin.setDisable(true);
	    txtLogin.textProperty().addListener((observable, oldValue, newValue) -> {
	    	btnLogin.setDisable(txtLogin.getText().isEmpty());
	    });
	}
}
