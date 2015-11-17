package controller;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import model.Pessoa;
import model.Predio;
import model.dao.PredioDAO;
import util.Alerta;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;


public class ConsultaPredioController extends ControllerDefault {
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNome;
	@FXML
	private TableView<Predio> tablePredio;
	@FXML
	private TableColumn<Predio,String> colCodigo;
	@FXML
	private TableColumn<Predio,String> colNome;
	@FXML
	private TableColumn<Predio,String> colQuartos;
	@FXML
	private Button btnSelecionar;
	
	public ConsultaPredioController(){}
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		iniciaGrid();
		
		btnSelecionar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event){
				if(tablePredio.getSelectionModel().getSelectedItems().size() > 1){
					Alerta alerta = new Alerta("Consulta de Prédios", "Apenas um prédio pode ser selecionada!");
            		
            		alerta.Erro(getView().getStage());
            		return;
				}
			
			if(tablePredio.getSelectionModel().getSelectedItems().size() == 1)
				setModel((Predio) tablePredio.getSelectionModel().getSelectedItem());
				getView().getStage().close();
				return;
			}
		});
	}
	
	public void iniciaGrid(){
		colCodigo.setCellValueFactory(
				new PropertyValueFactory<Predio,String>("codigoPredio"));
		
		colNome.setCellValueFactory(
			    new PropertyValueFactory<Predio,String>("descricao"));
		
		colQuartos.setCellValueFactory(
			    new PropertyValueFactory<Predio,String>("quartos"));
		
		PredioDAO dao = new PredioDAO();
		
		ObservableList<Predio> data =
				FXCollections.observableList(
						dao.query("SELECT p FROM Predio p", new java.util.ArrayList<String>()));
		
		FilteredList<Predio>filteredData = new FilteredList<>(data, p -> true);
		
		txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(predio -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				};
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if(predio.getDescricao().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});		
		});
		
		
		txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(predio -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				};
				return false;
			});		
		});
		
		
		SortedList<Predio>sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablePredio.comparatorProperty());
		tablePredio.setItems(sortedData);
	}
}
