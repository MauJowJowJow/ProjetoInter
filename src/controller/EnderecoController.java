package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.converter.NumberStringConverter;
import model.Endereco;
import model.Pessoa;
import model.dao.EnderecoDAO;
import util.Alerta;
import view.EnderecoView;

public class EnderecoController extends ControllerDefault implements Initializable{
	private Pessoa pessoa = new Pessoa();
	
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
	
	public Pessoa getPessoa(){
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.pessoa, pessoa);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			btnProcurarEndereco.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
			btnProcurarCidade.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
        
			txtCodPessoa.textProperty().bindBidirectional(getPessoa().getCodigoProperty(), new NumberStringConverter());
			txtNomePessoa.textProperty().bindBidirectional(getPessoa().getNomeProperty());
			
			btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Endereco model = (Endereco) getModel();
				EnderecoView view = (EnderecoView) getView();
				
				model.setCEP(txtCEP.getText());
				model.setLogradouro(txtRua.getText());
				model.setNumeroEnd(Integer.parseInt(txtNumero.getText()));
				model.setBairro(txtBairro.getText());
				model.setComplemento(txtComplemento.getText());

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

			}
		});
	}

}
