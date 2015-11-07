package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Endereco;
import model.dao.EnderecoDAO;
import util.Alerta;
import view.EnderecoView;

public class EnderecoController extends ControllerDefault implements Initializable{	
	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodPessoa;
	@FXML
	private TextField txtCodEndereco;
	@FXML
	private Button btnProcurarEndereco;
	@FXML
	private TextField txtCodCidade;
	@FXML
	private Button btnProcurarCidade;
	@FXML
	private TextField txtCEP;
	@FXML
	private TextField txtRua;
	@FXML
	private TextField txtNumero;
	@FXML
	private TextField txtBairro;
	@FXML
	private TextField txtComplemento;
	@FXML
	private TextField txtNomePessoa;
	@FXML
	private TextField txtNomeCidade;
	
	public EnderecoController() {}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {			
			btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Endereco model = (Endereco) getModel();
				EnderecoView view = (EnderecoView) getView();
				
				//if (model.isValidAdress()) {
				
				model.setCEP(txtCEP.getText());
				model.setLogradouro(txtRua.getText());
				model.setNumeroEnd(Integer.parseInt(txtNumero.getText()));
				model.setBairro(txtBairro.getText());
				model.setComplemento(txtComplemento.getText());
				
				if(true){
					EnderecoDAO dao = new EnderecoDAO();

					if (model.getPk().getCodigo() == 0) {
						model.geraCodigo();
						dao.insert(model);

						Alerta alerta = new Alerta("Inserção",
								"Produto inserido com o código " + model.getPk().getCodigo() + "!");
						alerta.Mensagem(view.getStage());

						txtCodEndereco.setText(Integer.toString(model.getPk().getCodigo()));
					} else {
						dao.update(model);

						Alerta alerta = new Alerta("Atualização", "Produto Atualizado!");
						alerta.Mensagem(view.getStage());
					}

					dao.closeEntity();
				} else {
					//Alerta alerta = new Alerta("Validação ", model.getErrors());

					//alerta.Erro(view.getStage());
				}

			}
		});
	}

}
