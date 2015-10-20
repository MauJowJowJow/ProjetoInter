package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Pessoa;
import model.dao.PessoaDAO;
import model.enums.TipoPessoa;
import util.Alerta;
import view.PessoaView;
import model.enums.PessoaSexo;

public class PessoaController implements Initializable{
	private Pessoa model;
	private PessoaView view;

	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtNomeCliente;
	@FXML
	private ComboBox<PessoaSexo> cbSexo;
	@FXML
	private ComboBox<TipoPessoa> cbPessoa;
	@FXML
	private TextField txtCGC;
	
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
	
	public void inicia(Scene parent) throws Exception{
		view.start(parent);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbSexo.getItems().addAll(PessoaSexo.values());
		cbSexo.setValue(PessoaSexo.Masculino);
		
		cbPessoa.getItems().addAll(TipoPessoa.values());
		cbPessoa.setValue(TipoPessoa.Fisica);
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(!txtNomeCliente.getText().equals(""))
            		model.setNome(txtNomeCliente.getText());
            	
            	model.setSexo(cbSexo.getValue());
            	model.setTipoPessoa(cbPessoa.getValue());
            	
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
}
