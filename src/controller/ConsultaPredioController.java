package controller;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.ModelDefault;
import model.Predio;
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
		
		iniciarGrid();
		
		btnSelecionar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event){
				if(tablePredio.getSelectionModel().getSelectedItems().size() > 1){
					Alerta alerta = new Alerta("Consulta de Pr�dios", "Apenas um pr�dio pode ser selecionada!");
            		
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
	
	public void iniciarGrid(){
		
	}
}