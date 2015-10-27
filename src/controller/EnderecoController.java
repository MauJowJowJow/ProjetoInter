package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import util.Alerta;
import view.ProdutoView;

public class EnderecoController {
	private Produto model;
	private ProdutoView view;
	private StatusScene statusScene;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Button btnSalvar;
	
	public ProdutoController() {
		this.model = new Produto();
		this.view = new ProdutoView();
	}

	public ProdutoController(Produto model, ProdutoView view) {
		this.model = model;
		this.view = view;
	}

	public void setModel(Produto model) {
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
				if (model.isValidProduct()) {
					ProdutoDAO dao = new ProdutoDAO();

					if (model.getCodigo() == 0) {
						dao.insert(model);

						Alerta alerta = new Alerta("Inserção",
								"Produto inserido com o código " + model.getCodigo() + "!");
						alerta.Mensagem(view.getStage());

						txtCodigo.setText(Integer.toString(model.getCodigo()));
					} else {
						dao.update(model);

						Alerta alerta = new Alerta("Atualização", "Produto Atualizado!");
						alerta.Mensagem(view.getStage());
					}

					dao.closeEntity();
				} else {
					Alerta alerta = new Alerta("Validação ", model.getErrors());

					alerta.Erro(view.getStage());
				}

			}
		});
	}

}
