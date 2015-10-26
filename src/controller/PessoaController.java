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
import model.Pessoa;
import model.dao.PessoaDAO;
import model.enums.TipoPessoa;
import oracle.sql.DATE;
import util.Alerta;
import view.PessoaView;
import model.enums.PessoaSexo;
import model.enums.EstadoCivil;

public class PessoaController implements Initializable{
	private Pessoa model;
	private PessoaView view;
	private StatusScene statusScene;

	@FXML
	private Button btnSalvar;
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
	
	public StatusScene getStatus(){
		return statusScene;
	}
	
	public void inicia(Scene parent) throws Exception{
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
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(!txtNomeCliente.getText().equals(""))
            		model.setNome(txtNomeCliente.getText());
            	
            	model.setSexo(cbSexo.getValue());
            	model.setTipoPessoa(cbPessoa.getValue());
            	model.setEstadoCivil(cbEstCivil.getValue());
            	
            	model.setInscricaoEstadual(txtIsncEstadualRG.getText());
            	
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
            		}else{
            			dao.update(model);
            		}
            		
            		dao.closeEntity();
            	}else{
            		Alerta alerta = new Alerta("Validação ", model.getErrors());
            		
            		alerta.Erro(view.getStage());
            	}
            		
            }
        });
	}
	
	public TextField getTxtNomeCliente(){
		return txtNomeCliente;
	}
	
	public ComboBox getCbSexo(){
		return cbSexo;
	}
	
	public TextField getTxtCGC(){
		return txtCGC;
	}
	
	public Button getBtnSalvar(){
		return btnSalvar;
	}
	public ComboBox getcbEstCivil(){
		return cbEstCivil;
	}
}
