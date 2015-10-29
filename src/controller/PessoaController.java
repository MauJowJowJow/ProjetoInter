package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import model.Endereco;
import model.Pessoa;
import model.dao.PessoaDAO;
import model.enums.TipoPessoa;
import model.pk.EnderecoPK;
import util.Alerta;
import view.EnderecoView;
import view.PessoaView;
import model.enums.PessoaSexo;
import model.enums.EstadoCivil;
import model.enums.SitCadPessoa;

public class PessoaController implements Initializable{
	private Pessoa model;
	private PessoaView view;
	private StatusScene statusScene;
	private Scene scene;
	
	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnEnderecos;

	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNomeCliente;
	@FXML
	private ComboBox<PessoaSexo> cbSexo;
	@FXML
	private ComboBox<TipoPessoa> cbPessoa;
	@FXML
	private ComboBox<EstadoCivil> cbEstCivil;
	@FXML
	private TextField txtCGC;
	@FXML
	private TextField txtIsncEstadualRG;
	@FXML
	private DatePicker dateNascimento;
	@FXML
	private ComboBox<SitCadPessoa> cbSituacao;
	@FXML
	private TextField txtTelComercial;
	
	public PessoaController() {
		this.model = new Pessoa();
		this.view = new PessoaView();
	}
	
	public PessoaController(Pessoa model, PessoaView view) {
		this.model = model;
		this.view = view;
	}

	public void setModel(Pessoa model) {
		this.model = model;
	}
	
	public void setScene(Scene scene){
		this.scene = scene;
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
		cbSexo.getItems().addAll(PessoaSexo.values());
		cbSexo.setValue(PessoaSexo.Masculino);

		cbPessoa.getItems().addAll(TipoPessoa.values());
		cbPessoa.setValue(TipoPessoa.Fisica);

		cbEstCivil.getItems().addAll(EstadoCivil.values());
		cbEstCivil.setValue(EstadoCivil.Solteiro);

		cbSituacao.getItems().addAll(SitCadPessoa.values());
		cbSituacao.setValue(SitCadPessoa.Ativo);
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(!txtNomeCliente.getText().equals(""))
            		model.setNome(txtNomeCliente.getText());
            	
            	model.setSexo(cbSexo.getValue());
            	model.setTipoPessoa(cbPessoa.getValue());
            	model.setEstadoCivil(cbEstCivil.getValue());
            	model.setstatusPessoa(cbSituacao.getValue());
            	
            	model.setInscricaoEstadual(txtIsncEstadualRG.getText());
            	
            	if(dateNascimento.getValue() != null)
	            	model.setDataNascimento(Date.from(
	            			dateNascimento.getValue().atStartOfDay()
	            			.atZone(ZoneId.systemDefault()).toInstant()
	            			));
            	
            	if(!txtCGC.getText().equals(""))
            		model.setCNPJCPF(txtCGC.getText());
            	
            	
            	if(model.isValidPerson()){
            		PessoaDAO dao = new PessoaDAO();
            		
            		if(model.getCodigo() == 0){
            			dao.insert(model);
            			
            			Alerta alerta = new Alerta("Inserção", "Pessoa inserida com o código " + model.getCodigo() + "!");
                		alerta.Mensagem(view.getStage());
                		
                		txtCodigo.setText(Integer.toString(model.getCodigo()));
            		}else{
            			dao.update(model);
            			
            			Alerta alerta = new Alerta("Atualização", "Pessoa Atualizada!");
                		alerta.Mensagem(view.getStage());
            		}
            		
            		dao.closeEntity();
            	}else{
            		Alerta alerta = new Alerta("Validação ", model.getErrors());
            		
            		alerta.Erro(view.getStage());
            	}
            		
            }
        });
		
		btnEnderecos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
					if(model.getCodigo() != 0){
						EnderecoView enderecoView = new EnderecoView();
						EnderecoPK enderecoPK = new EnderecoPK(model.getCodigo(), 3);
						Endereco endereco = new Endereco(enderecoPK);						
						
						try {
							enderecoView.start(scene);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						EnderecoController enderecoController = enderecoView.getFxmlLoader().<EnderecoController>getController();
						enderecoController.setModel(endereco);
						enderecoController.setView(enderecoView);
						
					}
			}
		});
	}
}
