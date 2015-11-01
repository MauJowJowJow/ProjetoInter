package controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import model.Pessoa;
import model.dao.PessoaDAO;
import view.ConsultaPessoaView;
import view.PessoaView;
import model.bean.formatters.*;

public class ConsultaPessoaController extends ControllerDefault {

	private Pessoa model;
	private ConsultaPessoaView view;
	private StatusScene statusScene;
	
	@FXML
	private TextField filtroNome;
	
	@FXML
	private TextField filtroCPFCNPJ;
	
	@FXML
	private TableView tablePessoas;
	
	@FXML
	private TableColumn colCodigo;
	
	@FXML
	private TableColumn colNome;
	
	@FXML
	private TableColumn colCPFCNPJ;
	
	private ObservableList<Pessoa> data;
	
	public ConsultaPessoaController() {
		this.model = new Pessoa();
		this.view = new ConsultaPessoaView();
	}
	
	public ConsultaPessoaController(Pessoa model, ConsultaPessoaView view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		iniciaGrid();
		
		/*
		
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
        });*/
	}

	private void iniciaGrid(){
		colCodigo.setCellValueFactory(
			    new PropertyValueFactory<Pessoa,String>("codigo")
			);
		colNome.setCellValueFactory(
		    new PropertyValueFactory<Pessoa,String>("nome")
		);
		colCPFCNPJ.setCellValueFactory(
		    new PropertyValueFactory<Pessoa,String>("CNPJCPF")
		);
		
		
		PessoaDAO dao = new PessoaDAO();
		
		ObservableList<Pessoa> data =
		        FXCollections.observableList(
		        			dao.query(
		        					"SELECT p FROM Pessoa p"
		        						, new java.util.ArrayList())
		        		);
		
        FilteredList<Pessoa> filteredData = new FilteredList<>(data, p -> true);

        filtroNome.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pessoa -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (pessoa.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        
        filtroCPFCNPJ.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(pessoa -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String cpfcnpj = newValue.replace(".", "").replace("-", ""); 

                if (pessoa.getCNPJCPF().replace(".", "").replace("-", "").contains(cpfcnpj)) {
                    return true;
                }
                return false;
            });
        });
 
        SortedList<Pessoa> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablePessoas.comparatorProperty());
        tablePessoas.setItems(sortedData);
        
	}

}
