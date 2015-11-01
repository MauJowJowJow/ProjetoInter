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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import model.Endereco;
import model.Pessoa;
import model.dao.PessoaDAO;
import model.enums.TipoPessoa;
import model.pk.EnderecoPK;
import util.Alerta;
import view.ConsultaPessoaView;
import view.EnderecoView;
import view.PessoaView;
import view.ProdutoView;
import model.enums.PessoaSexo;
import model.enums.EstadoCivil;
import model.enums.SitCadPessoa;

public class PessoaController extends ControllerDefault implements Initializable{
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnEnderecos;
	@FXML
	private Button btnPesquisaPessoa;
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNomeCliente;
	@FXML
	private TextField txtCGC;
	@FXML
	private TextField txtIsncEstadualRG;
	@FXML
	private TextField txtTelComercial;
	@FXML
	private TextField txtTelResidencial;
	@FXML
	private TextField txtCelular;
	@FXML
	private TextField txtEMail; 
	
	@FXML
	private ComboBox<PessoaSexo> cbSexo;
	@FXML
	private ComboBox<TipoPessoa> cbPessoa;
	@FXML
	private ComboBox<EstadoCivil> cbEstCivil;
	@FXML
	private ComboBox<SitCadPessoa> cbSituacao;
	
	@FXML
	private DatePicker dateNascimento;	
	@FXML
	private DatePicker dateCadastro;
	
	public PessoaController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTxtCodigo(txtCodigo);
		
		cbSexo.getItems().addAll(PessoaSexo.values());
		cbSexo.setValue(PessoaSexo.Masculino);

		cbPessoa.getItems().addAll(TipoPessoa.values());
		cbPessoa.setValue(TipoPessoa.Fisica);

		cbEstCivil.getItems().addAll(EstadoCivil.values());
		cbEstCivil.setValue(EstadoCivil.Solteiro);

		cbSituacao.getItems().addAll(SitCadPessoa.values());
		cbSituacao.setValue(SitCadPessoa.Ativo);
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();	
        btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
        
        btnPesquisaPessoa.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
				ConsultaPessoaView consultaPessoaView = new ConsultaPessoaView();
				
				try {
					consultaPessoaView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
					
					ConsultaPessoaController controller = consultaPessoaView.getFxmlLoader().<ConsultaPessoaController>getController();
					if(controller.getModel() != null){
						setModel(controller.getModel());

						carregaModel();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
        		
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	Pessoa model = (Pessoa) getModel();
            	PessoaView view = (PessoaView) getView();
            	
            	if(!txtNomeCliente.getText().equals(""))
            		model.setNome(txtNomeCliente.getText());
            	
            	model.setSexo(cbSexo.getValue());
            	model.setTipoPessoa(cbPessoa.getValue());
            	model.setEstadoCivil(cbEstCivil.getValue());
            	model.setstatusPessoa(cbSituacao.getValue());
            	
            	model.setInscricaoEstadual(txtIsncEstadualRG.getText());
            	model.setTelefoneCom(txtTelComercial.getText());
            	model.setTelRes(txtTelResidencial.getText());
            	model.setCelular(txtCelular.getText());
            	model.setEmail(txtEMail.getText());
            	
            	if(dateNascimento.getValue() != null)
	            	model.setDataNascimento(Date.from(
	            			dateNascimento.getValue().atStartOfDay()
	            			.atZone(ZoneId.systemDefault()).toInstant()
	            			));
            	
            	if(dateCadastro.getValue() != null)
	            	model.setDataCadastro(Date.from(
	            			dateCadastro.getValue().atStartOfDay()
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
					Pessoa model = (Pessoa) getModel();
					
					if(model.getCodigo() != 0){
						EnderecoView enderecoView = new EnderecoView();
						
						EnderecoPK enderecoPK = new EnderecoPK();
						enderecoPK.setCodigoPessoa(model.getCodigo());
						
						Endereco endereco = new Endereco(enderecoPK);
						
						try {
							enderecoView.iniciaTela(getScene(), endereco);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else{
						Alerta alerta = new Alerta("Cadastro de Pessoas", "Uma pessoa já cadastrada deve ser selecionada para alteração de seus endereços!");
	            		
	            		alerta.Erro(getView().getStage());
					}
			}
		});
	}
	
	public void setTxtCodigo(TextField txtCodigo){
		this.txtCodigo = txtCodigo;
	}
	
	public TextField getTxtCodigo(){
		return this.txtCodigo;
	}
	
	private void carregaModel(){
		Pessoa model = (Pessoa) getModel();
		
		String codigo = Integer.toString(model.getCodigo());
		
		//getTxtCodigo().setText(codigo);
		txtCodigo.setText(codigo);
	}
}
