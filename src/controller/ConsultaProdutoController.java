package controller;

import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Produto;
import model.dao.ProdutoDAO;
import util.Alerta;
import javafx.event.EventHandler;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;


public class ConsultaProdutoController extends ControllerDefault {
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtNome;
	
	@FXML
	private TableView<Produto> tableProduto;
	@FXML
	private TableColumn<Produto,Integer> colCodigo;
	@FXML
	private TableColumn<Produto,String> colNome;
	@FXML
	private TableColumn<Produto,String> colUnidade;
	@FXML
	private TableColumn<Produto,Integer> colQtdEstoque;
	
	@FXML
	private Button btnSelecionar;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnSair;
	
	public ConsultaProdutoController(){}
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		iniciaGrid();
		iniciaBotoes();
	}
	
	public void iniciaGrid(){
		colCodigo.setCellValueFactory(
				new PropertyValueFactory<Produto,Integer>("codigo"));
		
		colNome.setCellValueFactory(
			    new PropertyValueFactory<Produto,String>("descProduto"));
		
		colUnidade.setCellValueFactory(c -> {
			if(c.getValue() != null)
				return new SimpleStringProperty(c.getValue().getUniProduto().toString());
			else
				return new SimpleStringProperty("");
		});
		
		colQtdEstoque.setCellValueFactory(c -> {
			if(c.getValue() != null && c.getValue().getEstoque_produto() != null)
				return c.getValue().getEstoque_produto().getQuatidadeProperty().asObject();
				//return new SimpleIntegerProperty(0).asObject();
			else
				return new SimpleIntegerProperty(0).asObject();
		});
		
		bindGrid();
	}
	
	private void bindGrid(){
		ProdutoDAO dao = new ProdutoDAO();
		
		ObservableList<Produto> data =
				FXCollections.observableList(
						dao.query("SELECT p FROM Produto p", new java.util.HashMap<String, Object>()));
		
		FilteredList<Produto>filteredData = new FilteredList<>(data, p -> true);
		
		txtNome.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produto -> {
				if (newValue == null || newValue.isEmpty()){
					return true;
				};
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if(produto.getDescProduto().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});		
		});
		
		txtCodigo.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produto -> {
				if (newValue.isEmpty()){
					return true;
				};
				int codigo = 0;
				
				try{
					codigo = Integer.parseInt(newValue);
				}catch(Exception e){
					codigo = 0;
				}
				
				if(produto.getCodigo() == codigo || codigo == 0){
					return true;
				}
				
				return false;
			});
		});
		
		
		SortedList<Produto>sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableProduto.comparatorProperty());
		tableProduto.setItems(sortedData);
	}
	
	private void iniciaBotoes(){

		btnSelecionar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event){
				if(tableProduto.getSelectionModel().getSelectedItems().size() > 1){
					Alerta alerta = new Alerta(getStage().getTitle(), "Apenas um produto pode ser selecionado!");
            		
            		alerta.Erro(getView().getStage());
            		return;
				}
			
			if(tableProduto.getSelectionModel().getSelectedItems().size() == 1)
				setModel((Produto) tableProduto.getSelectionModel().getSelectedItem());
				getView().getStage().close();
				return;
			}
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
		txtCodigo.setText("");
		txtNome.setText("");
	}
}
