package controller;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Produto;
import model.dao.ProdutoDAO;
import model.enums.EstadoCivil;
import model.enums.PessoaSexo;
import model.enums.TipoPessoa;
import util.Alerta;
import view.ProdutoView;

public class ProdutoController extends ControllerDefault implements Initializable{	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Button btnSalvar;
	
	public ProdutoController() {}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Produto model = (Produto) getModel();
				ProdutoView view = (ProdutoView) getView();
				
				if (model.isValid()) {
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
