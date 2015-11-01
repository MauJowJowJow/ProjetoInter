package controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;
import model.Pessoa;
import model.dao.PessoaDAO;
import util.Alerta;
import view.ConsultaPessoaView;
import view.PessoaView;
import model.bean.formatters.*;

public class ConsultaPessoaController extends ControllerDefault {	
	@FXML
	private Button btnSelecionar;
	
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
	
	public ConsultaPessoaController() {	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		iniciaGrid();
		
		btnSelecionar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(tablePessoas.getSelectionModel().getSelectedItems().size() > 1){
            		Alerta alerta = new Alerta("Consulta de Pessoas", "Apenas uma pessoa pode ser selecionada!");
            		
            		alerta.Erro(getView().getStage());
            		return;
            	}
            	
            	if(tablePessoas.getSelectionModel().getSelectedItems().size() == 1)
            		setModel((Pessoa) tablePessoas.getSelectionModel().getSelectedItem());
            		getView().getStage().close();
            		return;
            }
        });
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
