package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Estado;
import view.EstadoView;
import model.dao.EstadoDAO;
import model.dao.PessoaDAO;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
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
	public void inicia(Scene parent) throws Exception {
		statusScene = StatusScene.Aberto;

		view.start(parent);

		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				statusScene = StatusScene.Fechado;
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
            	if(!txtNomeEstado.getText().equals(""))
            		model.setNomeEstado(txtNomeEstado.getText());
            	
            	model.setSiglaEstado(txtUF.getText());
            	model.setNomePais(txtPais.getText());
            	
            	if(model.getCodigoEstado() == 0){
        			dao.(model);
			}
		});
		
	}

}
