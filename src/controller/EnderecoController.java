package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Endereco;
import model.dao.EnderecoDAO;
import util.Alerta;
import view.EnderecoView;

public class EnderecoController implements Initializable{
	private Endereco model;
	private EnderecoView view;
	private StatusScene statusScene;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Button btnSalvar;
	
	public EnderecoController() {
		this.model = new Endereco();
		this.view = new EnderecoView();
	}

	public EnderecoController(Endereco model, EnderecoView view) {
		this.model = model;
		this.view = view;
	}

	public Endereco getModel() {
		return model;
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
			final Endereco model = this.getModel();
			
			btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//if (model.isValidAdress()) {
				if(true){
					EnderecoDAO dao = new EnderecoDAO();

					//if (model.getCodigo() == 0) {
					if(true){
						dao.insert(model);

						Alerta alerta = new Alerta("Inser��o",
								"Produto inserido com o c�digo " + model.getPk().getCodigo() + "!");
						alerta.Mensagem(view.getStage());

						txtCodigo.setText(Integer.toString(model.getPk().getCodigo()));
					} else {
						dao.update(model);

						Alerta alerta = new Alerta("Atualiza��o", "Produto Atualizado!");
						alerta.Mensagem(view.getStage());
					}

					dao.closeEntity();
				} else {
					//Alerta alerta = new Alerta("Valida��o ", model.getErrors());

					//alerta.Erro(view.getStage());
				}

			}
		});
	}

}
