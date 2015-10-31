package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Estado;
import view.EstadoView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;

public class EstadoController implements Initializable {
	
	private Estado model;
	private EstadoView view;
	private StatusScene statusScene;
	private Scene scene;
	
	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodigoEst;
	@FXML
	private TextField txtNomeEstado;
	@FXML
	private TextField txtUF;
	@FXML
	private TextField txtPais;
	
	public void setScene(Scene scene){
		this.scene = scene;
	}
	
	public EstadoController() {
		this.model = new Estado();
		this.view = new EstadoView();
	}
	
	public EstadoController(Estado model, EstadoView view) {
		this.model = model;
		this.view = view;
	}

	public void setModel(Estado model) {
		this.model = model;
	}

	public StatusScene getStatus() {
		return statusScene;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
