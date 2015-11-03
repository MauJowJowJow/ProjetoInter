package controller;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import model.Item_reserva;
import model.Pessoa;
import model.Quarto;
import model.dao.PessoaDAO;
import model.dao.QuartoDAO;
import oracle.sql.DATE;
import util.Alerta;
import view.ConsultaPessoaView;

public class ReservaController extends ControllerDefault{
	private Pessoa pessoa;
	private Quarto quarto;
	
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtCodigoPessoa;
	@FXML
	private TextField txtNomePessoa;
	
	@FXML
	private Button btnPesquisaPessoa;
	
	@FXML
	private TextField txtCodigoQuarto;
	
	@FXML
	private TextField txtDescricaoQuarto;
	
	@FXML
	private DatePicker txtCheckIn;
	
	@FXML
	private DatePicker txtCheckOut;
	
	@FXML
	private TextField txtDiasEstadia;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private Button btnPesquisaQuarto;
	
	@FXML
	private Button btnAddLinha;
	
	@FXML
	private Button btnDelLinha;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private TableView<Item_reserva> tbvItemReserva;
	@FXML
	private TableColumn<Item_reserva, Integer> colCodigoQuarto;
	@FXML
	private TableColumn<Item_reserva, String> colDescricaoQuarto;
	@FXML
	private TableColumn<Item_reserva, DatePicker> colCheckIn;
	@FXML
	private TableColumn<Item_reserva, DatePicker> colCheckOut;
	@FXML
	private TableColumn<Item_reserva, Integer> colDiasEstadia;	
	@FXML
	private TableColumn<Item_reserva, Double> colValor;

	public void setPessoa(Pessoa pessoa){
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setQuarto(Quarto quarto){
		this.quarto = quarto;
	}

	public Quarto getQuarto(){
		return quarto;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		imagensBotoes();
		eventosPesquisa();
		eventosBotoes();
		eventosCampos();
	    iniciaTableView();
	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
	    btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    btnPesquisaQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    
	    btnAddLinha.setGraphic(new ImageView(classLoader.getResource("Plus.png").toString()));
		btnDelLinha.setGraphic(new ImageView(classLoader.getResource("minus.png").toString()));
		
	}
	
	public void eventosPesquisa(){
	    btnPesquisaPessoa.setOnAction(new EventHandler<ActionEvent>() {
	    	
	        @Override
	        public void handle(ActionEvent event) {
				ConsultaPessoaView consultaPessoaView = new ConsultaPessoaView();
				
				try {
					consultaPessoaView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
					
					ConsultaPessoaController controller = consultaPessoaView.getFxmlLoader().<ConsultaPessoaController>getController();
					if(controller.getModel() != null){
						setPessoa((Pessoa) controller.getModel());
						
						txtCodigoPessoa.setText(Integer.toString(getPessoa().getCodigo()));
						txtNomePessoa.setText(getPessoa().getNome());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        }
	    });

	    btnPesquisaQuarto.setOnAction(evt -> {/*
	    	ConsultaQuartoView consultaQuartoView = new ConsultaQuartoView();
			
			try {
				consultaQuartoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
				
				ConsultaQuartoController controller = consultaQuartoView.getFxmlLoader().<ConsultaQuartoController>getController();
				if(controller.getModel() != null){
					setQuarto((Quarto) controller.getModel());
					
					txtCodigoQuarto.setText(Integer.toString(getQuarto().getCodigo()));
					txtDescricaoQuarto.setText(getQuarto().getDescricao());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}*/
	    });
	}
	
	public void eventosBotoes(){
		
	    btnAddLinha.setOnAction(evt -> {
    		Item_reserva item_reserva = new Item_reserva();
    		
    		if(txtCodigoQuarto.getText().isEmpty())
    			txtCodigoQuarto.setText("0");
    		
    		item_reserva.setCodigoQuarto(Integer.parseInt(txtCodigoQuarto.getText()));
    		item_reserva.setDescricaoQuarto(txtDescricaoQuarto.getText());
    		
    		if(txtCheckIn.getValue() != null)
	    		item_reserva.setCheckIn(Date.from(
            			txtCheckIn.getValue().atStartOfDay()
            			.atZone(ZoneId.systemDefault()).toInstant()
            			));
    		
    		if(txtCheckOut.getValue() != null)
	    		item_reserva.setCheckOut(Date.from(
            			txtCheckOut.getValue().atStartOfDay()
            			.atZone(ZoneId.systemDefault()).toInstant()
            			));
    		
    		if(txtDiasEstadia.getText().isEmpty())
    			txtDiasEstadia.setText("0");
    		
    		item_reserva.setDiasReserva(Integer.parseInt(txtDiasEstadia.getText()));
    		
    		if(txtValor.getText().isEmpty())
    			txtValor.setText("0");
    		
    		item_reserva.setValorReserva(Double.parseDouble(txtValor.getText()));
    		
    		if(!quartoJaExiste(item_reserva))
    			tbvItemReserva.getItems().add(item_reserva);
	    });
	
	    btnDelLinha.setOnAction(evt -> {
	    		ObservableList<Item_reserva> list = tbvItemReserva.getSelectionModel().getSelectedItems();
	
	    		if(list.size() > 0){
	    			Alerta alerta = new Alerta("Cadastro de Reserva", "Deseja mesmo excluir este(s) quarto(s) da reserva?");
	
	    			if(alerta.Confirm((Stage) getScene().getWindow()))
	    				tbvItemReserva.getItems().removeAll(list);
	    		}
	        }
	    );
	    
	    btnSalvar.setOnAction(evt -> {
	    	
	    });
		
	}
	
	public void eventosCampos(){
	    txtCodigoPessoa.setOnAction(evt -> {
			if(txtCodigoPessoa.getText().isEmpty())
				txtCodigoPessoa.setText("0");

	    	int codigo = Integer.parseInt(txtCodigoPessoa.getText());

	    	if(codigo != 0){
	    		PessoaDAO dao = new PessoaDAO();
	    		setPessoa(dao.getById(codigo));
	    		if(getPessoa() != null)
	    			txtNomePessoa.setText(getPessoa().getNome());
	    		
	    	}else{
	    		txtNomePessoa.setText("");
	    	}
	    });

	    txtCodigoQuarto.setOnAction(evt-> {
	    			if(txtCodigoQuarto.getText().isEmpty())
	    				txtCodigoQuarto.setText("0");
		    		
			    	int codigo = Integer.parseInt(txtCodigoQuarto.getText());
			    	
			    	if(codigo != 0){
			    		QuartoDAO dao = new QuartoDAO();
			    		setQuarto(dao.getById(codigo));
			    		if(getQuarto() != null)
			    			txtDescricaoQuarto.setText(getQuarto().getDescricao());
			    	}else{
			    		txtDescricaoQuarto.setText("");
			    	}
		});
	}
	
	private void iniciaTableView(){
		colCodigoQuarto.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, Integer>("codigoQuarto")
		);

		colCodigoQuarto.setOnEditCommit(evt -> {
				QuartoDAO dao = new QuartoDAO();
				
				if(colCodigoQuarto.getText() == null)
					colCodigoQuarto.setText("0");
				else{
					int codigo = evt.getNewValue();
					Quarto quarto = dao.getById(codigo);
					
					evt.getRowValue().setDescricaoQuarto(quarto.getDescricao());
				}
			});

		colDescricaoQuarto.setCellValueFactory(
				//new ReadOnlyStringWrapper(data.getValue().getDescricao())
			    new PropertyValueFactory<Item_reserva, String>("descricaoQuarto")
			);

		colCheckIn.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, DatePicker>("checkIn")
			);
		
		colCheckOut.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, DatePicker>("checkOut")
			);
		
		colDiasEstadia.setCellValueFactory(new Callback<CellDataFeatures<Item_reserva, Integer>, ObservableValue<Integer>>() {
			    @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Item_reserva, Integer> p) {
			        return new SimpleIntegerProperty(p.getValue().getDiasReserva()).asObject();
			    } 
			});
		
		colDiasEstadia.setCellFactory(
				TextFieldTableCell.<Item_reserva, Integer>forTableColumn(new IntegerStringConverter())
			    //new PropertyValueFactory<Item_reserva, Integer>("diasReserva")
			);

		colValor.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, Double>("valorReserva")
			);
	}

	private boolean quartoJaExiste(Item_reserva item_reserva){
		for(Item_reserva item_reserva2 : tbvItemReserva.getItems()){
			if(item_reserva2.getCodigoQuarto() == item_reserva.getCodigoQuarto())
				return true;
		}
		return false;
	}
}
