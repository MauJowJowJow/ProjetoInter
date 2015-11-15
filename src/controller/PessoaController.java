package controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;
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
	private Pessoa pessoa = new Pessoa();
	
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnEnderecos;
	@FXML
	private Button btnNovo;
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
	
	// Só copia as propriedades pra não perder os Bindings.
	public void setPessoa(Pessoa pessoa){
		try {
			BeanUtils.copyProperties(this.pessoa, pessoa);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa(){
		return pessoa;
	}
	
	public PessoaController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
        // Ligação entre model e view
        Bindings.bindBidirectional(txtCodigo.textProperty(), getPessoa().getCodigoProperty(), new NumberStringConverter());
        txtNomeCliente.textProperty().bindBidirectional(getPessoa().getNomeProperty());
        txtCGC.textProperty().bindBidirectional(getPessoa().getCNPJCPFProperty());
        txtIsncEstadualRG.textProperty().bindBidirectional( getPessoa().getInscricaoEstadualProperty());
        cbPessoa.valueProperty().bindBidirectional(getPessoa().getTipoPessoaProperty());
    	txtTelComercial.textProperty().bindBidirectional(getPessoa().getTelefoneComProperty());
    	txtTelResidencial.textProperty().bindBidirectional(getPessoa().getTelResProperty());
    	txtCelular.textProperty().bindBidirectional(getPessoa().getCelularProperty());
    	txtEMail.textProperty().bindBidirectional(getPessoa().getEmailProperty()); 
    	cbSexo.valueProperty().bindBidirectional(getPessoa().getSexoProperty());
    	cbPessoa.valueProperty().bindBidirectional(getPessoa().getTipoPessoaProperty());
    	cbEstCivil.valueProperty().bindBidirectional(getPessoa().getEstadoCivilProperty());
    	cbSituacao.valueProperty().bindBidirectional(getPessoa().getstatusPessoaProperty());
    	dateNascimento.valueProperty().bindBidirectional(getPessoa().getDataNascimentoProperty());
    	
    	dateCadastro.valueProperty().bindBidirectional(getPessoa().getDataCadastroProperty());
        
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
						setPessoa((Pessoa)controller.getModel());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
        		
		
		btnSalvar.setOnAction(evt -> {            	
            	PessoaView view = (PessoaView) getView();
            	
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
		
		btnNovo.setOnAction(evt -> {
			if(getPessoa().getCodigo() == 0)
				setPessoa(new Pessoa());
			else{
				Alerta alerta = new Alerta("Cadastro de Pessoas", "Cadastro pode ter sofrido alterações, deseja mesmo criar uma nova pessoa?");
				if(alerta.Confirm(getStage()))
					setPessoa(new Pessoa());				
			}
		});
	}
}
