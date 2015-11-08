package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import model.Endereco;
import model.Pessoa;
import model.dao.PessoaDAO;
import model.enums.TipoPessoa;
import model.pk.EnderecoPK;
import util.Alerta;
import view.ConsultaPessoaView;
import view.EnderecoView;
import view.PessoaView;
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
        		
		
		btnSalvar.setOnAction(evt -> {
            	if(getModel() == null)
            		setModel(new Pessoa());
            	
            	Pessoa pessoa = (Pessoa) getModel();
            	PessoaView view = (PessoaView) getView();
            	
            	if(!txtNomeCliente.getText().isEmpty())
            		pessoa.setNome(txtNomeCliente.getText());
            	
            	pessoa.setSexo(cbSexo.getValue());
            	pessoa.setTipoPessoa(cbPessoa.getValue());
            	pessoa.setEstadoCivil(cbEstCivil.getValue());
            	pessoa.setstatusPessoa(cbSituacao.getValue());
            	
            	pessoa.setInscricaoEstadual(txtIsncEstadualRG.getText());
            	pessoa.setTelefoneCom(txtTelComercial.getText());
            	pessoa.setTelRes(txtTelResidencial.getText());
            	pessoa.setCelular(txtCelular.getText());
            	pessoa.setEmail(txtEMail.getText());
            	
            	if(dateNascimento.getValue() != null)
	            	pessoa.setDataNascimento(Date.from(
	            			dateNascimento.getValue().atStartOfDay()
	            			.atZone(ZoneId.systemDefault()).toInstant()
	            			));
            	
            	if(dateCadastro.getValue() != null)
	            	pessoa.setDataCadastro(Date.from(
	            			dateCadastro.getValue().atStartOfDay()
	            			.atZone(ZoneId.systemDefault()).toInstant()
	            			));
            	
            	if(!txtCGC.getText().equals(""))
            		pessoa.setCNPJCPF(txtCGC.getText());
            	
            	
            	if(pessoa.isValid()){
            		PessoaDAO dao = new PessoaDAO();
            		
            		if(pessoa.getCodigo() == 0){
            			dao.insert(pessoa);
            			
            			Alerta alerta = new Alerta("Inserção", "Pessoa inserida com o código " + pessoa.getCodigo() + "!");
                		alerta.Mensagem(view.getStage());
                		
                		txtCodigo.setText(Integer.toString(pessoa.getCodigo()));
            		}else{
            			dao.update(pessoa);
            			
            			Alerta alerta = new Alerta("Atualização", "Pessoa Atualizada!");
                		alerta.Mensagem(view.getStage());
            		}
            		setModel(pessoa);
            		
            		dao.closeEntity();
            	}else{
            		Alerta alerta = new Alerta("Validação ", pessoa.getErrors());
            		
            		alerta.Erro(view.getStage());
            	}
        });
		
		btnEnderecos.setOnAction(evt -> {
					Pessoa pessoa = (Pessoa) getModel();
					
					if(pessoa.getCodigo() != 0){
						EnderecoView enderecoView = new EnderecoView();
						
						EnderecoPK enderecoPK = new EnderecoPK();
						enderecoPK.setCodigoPessoa(pessoa.getCodigo());
						
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
		});
	}
	
	private void carregaModel(){
		Pessoa pessoa = (Pessoa) getModel();
		
		String codigo = Integer.toString(pessoa.getCodigo());
		
		txtCodigo.setText(codigo);
	}
}
