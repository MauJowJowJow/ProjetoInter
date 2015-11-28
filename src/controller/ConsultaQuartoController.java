package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Quarto;
import model.dao.QuartoDAO;
import model.enums.StatusQuarto;
import util.Alerta;

public class ConsultaQuartoController extends ControllerDefault {
	FilteredList<Quarto> filteredData;
	SortedList<Quarto> sortedData;
	
	@FXML
	private TextField filtroDescricao;
	@FXML
	private TextField txtDescricao;
	@FXML
	private ComboBox<StatusQuarto> cbStatus;
	@FXML
	private TextField txtQtdDormitorios;
	@FXML
	private TextField txtValorInicial;
	@FXML
	private TextField txtValorFinal;
	@FXML
	private TableView<Quarto> tableQuartos;
	@FXML
	private TableColumn<Quarto, Integer> colCodigo;
	@FXML
	private TableColumn<Quarto, String> colDescricao;
	@FXML
	private TableColumn<Quarto, String> colStatus;
	@FXML
	private TableColumn<Quarto, Integer> colQtdDormitorio;
	@FXML
	private TableColumn<Quarto, Double> colValor;
	
	@FXML
	private Button btnSelecionar;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnSair;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		iniciaGrid();
		iniciaBotoes();
	}

	private void iniciaGrid(){
		
		// Adiciona nulo para selecionar tudo
		cbStatus.getItems().addAll(StatusQuarto.values());
		cbStatus.getItems().add(null);
				
		colCodigo.setCellValueFactory(
			    new PropertyValueFactory<Quarto,Integer>("codigo")
			);
		colDescricao.setCellValueFactory(
			    new PropertyValueFactory<Quarto,String>("nome")
			);
		
		colStatus.setCellValueFactory(c -> {
			return new SimpleStringProperty(c.getValue().getStatusQuarto().toString());
		});
		
		colQtdDormitorio.setCellValueFactory(
			    new PropertyValueFactory<Quarto,Integer>("dormitorios")
		);
		colValor.setCellValueFactory(
			    new PropertyValueFactory<Quarto,Double>("valorQuarto")
		);
		
		bindGrid();
	}
	
	private void bindGrid(){

		QuartoDAO dao = new QuartoDAO();
		
		ObservableList<Quarto> data =
		        FXCollections.observableList(
		        			dao.query(
		        					"SELECT q FROM Quarto q"
		        						, new java.util.HashMap<String, Object>())
		        		);
		
        filteredData = new FilteredList<>(data, p -> true);

        txtDescricao.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quarto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (quarto.getNome().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        
        cbStatus.setOnAction(evt -> {
            filteredData.setPredicate(quarto -> {
            	StatusQuarto newValue = cbStatus.getValue();
            	
                if (newValue == null) {
                    return true;
                }

                if (quarto.getStatusQuarto() == newValue){
                	return true;
                }
                return false;
            });
        });
        
        txtQtdDormitorios.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quarto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                Integer qtdQuartos = Integer.parseInt(newValue);
                if (quarto.getDormitorios() == qtdQuartos || qtdQuartos == 0) {
                    return true;
                }
                return false;
            });
        });
        
        txtValorInicial.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quarto -> {                
                Integer valorInicial = 0;
                try{
                	valorInicial = Integer.parseInt(newValue);
                }catch(Exception e){
                	valorInicial = 0;
                }
                
                Integer valorFinal = 0;
                try{
                	if(!txtValorFinal.getText().isEmpty())
                		valorFinal = Integer.parseInt(txtValorFinal.getText());
                }catch(Exception e){
                	valorFinal = 0;
                }
                
                if(quarto.getValorQuarto() >= valorInicial || valorInicial == 0){
                	if (quarto.getValorQuarto() <= valorFinal || valorFinal == 0) {
                		return true;
                	}
                }
                return false;
            });
        });
        
        txtValorFinal.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(quarto -> {                
                Integer valorInicial = 0;
                try{
                	if(!txtValorInicial.getText().isEmpty())
                		valorInicial = Integer.parseInt(txtValorInicial.getText());
                }catch(Exception e){
                	valorInicial = 0;
                }
                
                Integer valorFinal = 0;
                try{
                	valorFinal = Integer.parseInt(newValue);
                }catch(Exception e){
                	valorFinal = 0;
                }
                
                if(quarto.getValorQuarto() >= valorInicial || valorInicial == 0){
                	if (quarto.getValorQuarto() <= valorFinal || valorFinal == 0) {
                		return true;
                	}
                }
                return false;
            });
        });

		sortedData = new SortedList<>(filteredData);
	
		sortedData.comparatorProperty().bind(tableQuartos.comparatorProperty());
		tableQuartos.setItems(sortedData);
	}

	private void iniciaBotoes(){

		btnSelecionar.setOnAction(evt -> {
			if (tableQuartos.getSelectionModel().getSelectedItems().size() > 1) {
				Alerta alerta = new Alerta(getStage().getTitle(), "Apenas um quarto pode ser selecionado!");

				alerta.Erro(getView().getStage());
				return;
			}

			if (tableQuartos.getSelectionModel().getSelectedItems().size() == 1)
				setModel((Quarto) tableQuartos.getSelectionModel().getSelectedItem());
			getView().getStage().close();
			return;
		});
		
		btnAtualizar.setOnAction(evt -> {
			bindGrid();
			limpaTela();
		});
		
		btnSair.setOnAction(evt -> {
			getStage().close();
		});
	}
	
	private void limpaTela(){
		txtDescricao.setText("");
		cbStatus.setValue(null);
		txtQtdDormitorios.setText("");
		txtValorInicial.setText("");
		txtValorFinal.setText("");
	}
}
