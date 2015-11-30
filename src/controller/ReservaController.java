package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import model.Item_reserva;
import model.Pessoa;
import model.Quarto;
import model.Reserva;
import model.dao.Item_reservaDAO;
import model.dao.ReservaDAO;
import model.enums.StatusReserva;
import model.pk.Item_reservaPK;
import util.Alerta;
import view.ConsultaPessoaView;
import view.ConsultaQuartoView;

public class ReservaController extends ControllerDefault{
	private Pessoa pessoa = new Pessoa();
	private Quarto quarto = new Quarto();
	
	private boolean reservaValida;
	private boolean item_reservaValido;
	
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
	private Button btnNovo;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private TableView<Item_reserva> tbvItemReserva;
	@FXML
	private TableColumn<Item_reserva, Integer> colCodigoQuarto;
	@FXML
	private TableColumn<Item_reserva, String> colDescricaoQuarto;
	@FXML
	private TableColumn<Item_reserva, String> colCheckIn;
	@FXML
	private TableColumn<Item_reserva, String> colCheckOut;
	@FXML
	private TableColumn<Item_reserva, Integer> colDiasEstadia;	
	@FXML
	private TableColumn<Item_reserva, Double> colValor;
	
	public Reserva getReserva(){
		if(getModel() == null)
			setModel(new Reserva());
		
		return (Reserva) getModel();
	}

	public void setPessoa(Pessoa pessoa){
		// Copia Propriedades para não perder os binbings com um novo objeto
		try{
			BeanUtils.copyProperties(this.pessoa, pessoa);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Pessoa getPessoa(){
		return pessoa;
	}

	public void setQuarto(Quarto quarto){
		// Copia Propriedades para não perder os binbings com um novo objeto
		try{
			BeanUtils.copyProperties(this.quarto, quarto);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}

	public Quarto getQuarto(){
		Quarto quarto = new Quarto();
		
		// Copia Propriedades para não pegar a mesma referencia em memória
		try{
			BeanUtils.copyProperties(quarto, this.quarto);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
		
		return quarto;
	}
	
	public Quarto getQuartoReference(){
		return quarto;
	}

	public boolean isItem_reservaValido() {
		return item_reservaValido;
	}

	public void setItem_reservaValido(boolean item_reservaValido) {
		this.item_reservaValido = item_reservaValido;
	}

	public boolean isReservaValida() {
		return reservaValida;
	}

	public void setReservaValida(boolean reservaValida) {
		this.reservaValida = reservaValida;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		super.initialize(arg0, arg1);
		
		criaBinding();
		imagensBotoes();
		eventosPesquisa();
		eventosBotoes();
		eventosCampos();
	    iniciaTableView();
	}
	
	private void criaBinding(){
		// Binda Pessoa
		txtCodigoPessoa.textProperty().bindBidirectional(getPessoa().getCodigoProperty(), new NumberStringConverter());
		txtNomePessoa.textProperty().bindBidirectional(getPessoa().getNomeProperty());
		
		// Binda Quarto
		txtCodigoQuarto.textProperty().bindBidirectional(getQuartoReference().getCodigoProperty(), new NumberStringConverter());
		txtDescricaoQuarto.textProperty().bindBidirectional(getQuartoReference().getNomeProperty());

	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
	    btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    btnPesquisaQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    
	    btnAddLinha.setGraphic(new ImageView(classLoader.getResource("Plus.png").toString()));
		btnDelLinha.setGraphic(new ImageView(classLoader.getResource("minus.png").toString()));
		
	}
	
	public void eventosPesquisa(){
	    btnPesquisaPessoa.setOnAction(evt -> {
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
	    });

	    btnPesquisaQuarto.setOnAction(evt -> {
	    	ConsultaQuartoView consultaQuartoView = new ConsultaQuartoView();
			
			try {
				consultaQuartoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
				
				ConsultaQuartoController controller = consultaQuartoView.getFxmlLoader().<ConsultaQuartoController>getController();
				if(controller.getModel() != null){
					setQuarto((Quarto) controller.getModel());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	}
	
	public void limpaTela(){		
		txtCodigo.setText("");
		
		setPessoa(new Pessoa());
		setQuarto(new Quarto());
		
		txtCheckIn.setValue(null);
		txtCheckOut.setValue(null);
		
		txtDiasEstadia.setText("");
		txtValor.setText("");
		
		tbvItemReserva.getItems().clear();
		
		setModel(new Reserva());
		setPessoa(new Pessoa());
		setQuarto(new Quarto());
	}
	
	public void eventosBotoes(){
		
	    btnAddLinha.setOnAction(evt -> {
    		Item_reserva item_reserva = new Item_reserva();
    		   		
    		if(!validaItemReserva())
    			return;
    		
    		if(txtCodigoQuarto.getText().isEmpty())
    			txtCodigoQuarto.setText("0");
    		
    		item_reserva.getPK().setQuarto(getQuarto());
    		
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
    		
    		if(quartoJaExiste(item_reserva)){
    			Alerta alerta = new Alerta(getStage().getTitle(), "Quarto já informado nesta reserva!");
    			alerta.Erro(getStage());
    		}else{
    			tbvItemReserva.getItems().add(item_reserva);
    			setQuarto(null);
    		}
	    });
	
	    btnDelLinha.setOnAction(evt -> {
	    		ObservableList<Item_reserva> list = tbvItemReserva.getSelectionModel().getSelectedItems();
	
	    		if(list.size() > 0){
	    			Alerta alerta = new Alerta(getStage().getTitle(), "Deseja mesmo excluir este(s) quarto(s) da reserva?");
	
	    			if(alerta.Confirm(getStage()))
	    				tbvItemReserva.getItems().removeAll(list);
	    		}
	        }
	    );
	    
	    btnSalvar.setOnAction(evt -> {
	    	if(!validaReserva()){
	    		return;
	    	}
	    	Alerta alerta;
	    	
	    	// Nivel 1
	    	Reserva reserva = getReserva();
	    	ReservaDAO reservaDAO = new ReservaDAO();
	    	
	    	reserva.setPessoa(getPessoa());
	    	
	    	if(reserva.getCodigoReserva() == 0){
	    		reserva.setEmissaoReserva(new Date(new java.util.Date().getTime()));
	    		
	    		reservaDAO.insert(reserva);
	    		alerta = new Alerta(getStage().getTitle(), "Código da reserva cadastrada " + reserva.getCodigoReserva());
	    	}else{
	    		reservaDAO.update(reserva);
	    		alerta = new Alerta(getStage().getTitle(), "Reserva " + reserva.getCodigoReserva() + " atualizada!");
	    	}
	    	
	    	if(reserva.getCodigoReserva() != 0){
		    	// Nivel 2
		    	Item_reservaDAO item_reservaDAO = new Item_reservaDAO();
		    	Item_reservaPK pk;
	    		pk = new Item_reservaPK();
	    		pk.setReserva(reserva);
	    		
	    		HashMap<String, Object> parametros = new HashMap<String, Object>();
	    		parametros.put("reserva", pk.getReserva().getCodigoReserva());
	    		
	    		List<Item_reserva> listaBD = item_reservaDAO.query("SELECT itr FROM Item_reserva itr" 
	    										+ " WHERE itr.pk.reserva.codigoReserva = :reserva", parametros);
	    		
	    		int index;
		    	for(Item_reserva item_reserva : tbvItemReserva.getItems()){
		    		pk.setQuarto(item_reserva.getPK().getQuarto());
		    		
		    		item_reserva.getPK().setReserva(reserva);
		    		item_reserva.setStatusReserva(StatusReserva.Aberta);
		    		
		    		index = item_reserva.existeNaLista(listaBD); 
		    		if(index == -1){
		    			item_reservaDAO.insert(item_reserva);
		    		}else{
		    			item_reservaDAO.update(item_reserva);
		    			listaBD.remove(index);
		    		}
		    	}
		    	
		    	// Se sobrou itens, exclui, não estão mais na tabela.
		    	for(Item_reserva itr : listaBD){
		    		item_reservaDAO.delete(itr);
		    	}
		    	
		    	setModel(reserva);
		    	txtCodigo.setText(Integer.toString(reserva.getCodigoReserva()));
	    		
	    		alerta.Mensagem(getStage());
	    		
	    	}else{
	    		alerta = new Alerta(getStage().getTitle(), "Erro ao cadastrar a reserva.");
	    		alerta.Erro(getStage());
	    	}
	    });
	    
	    btnNovo.setOnAction(evt -> {
	    	if(getReserva().getCodigoReserva() == 0){
	    		Alerta alerta = new Alerta(getStage().getTitle(), "Reserva ainda não foi salva, deseja mesmo limpar tela?");
	    				
	    		if(alerta.Confirm(getStage()))
	    			limpaTela();
	    	}else
	    		limpaTela();
	    });
		
	    btnCancelar.setOnAction(evt -> {
	    	Alerta alerta;
	    	if(getReserva().getCodigoReserva() == 0){
	    		alerta = new Alerta(getStage().getTitle(), "Nenhuma reserva finalizada escolhida");
	    		alerta.Alertar(getStage());
	    	}else{
		    	alerta = new Alerta(getStage().getTitle(), "Deseja realmente cancelar a reserva?");
				
				if(alerta.Confirm(getStage())){
					cancelaReserva();
				}
	    	}
	    });
	}
	
	public void eventosCampos(){
	    txtCodigoPessoa.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
	    	if(newValue) return;
	    	
	    	setReservaValida(true);
			
			if(!validaPessoa()){
				setReservaValida(false);
			}else{
				Pessoa pessoa = getPessoa().exists();
	    		
	    		if(pessoa != null){
	    			setPessoa(pessoa);
	    		}
	    		else{
	    			setReservaValida(false);
	        		Alerta alerta = new Alerta(getStage().getTitle(), getPessoa().getErrors());
	        		
	        		alerta.Erro(getStage());
	    		}
			}
			
			if(!isReservaValida()){
	    		txtNomePessoa.setText("");
			}
	    });

	    txtCodigoQuarto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
	    			if(newValue) return;
	    			
	    			setItem_reservaValido(true);
		    		
	    			if(!validaQuarto()){
	    				setItem_reservaValido(false);
	    			}else{
	    				Quarto quarto = getQuartoReference().exists();
	    	    		
	    	    		if(quarto != null){
	    	    			setQuarto(quarto);
	    	    		}
	    	    		else{
	    	    			setItem_reservaValido(false);
	    	        		Alerta alerta = new Alerta(getStage().getTitle(), getQuartoReference().getErrors());
	    	        		
	    	        		alerta.Erro(getStage());
	    	    		}	    			}
	    			
	    			if(!isItem_reservaValido()){
	    				txtDescricaoQuarto.setText("");
	    			}
		});
	    
	    txtCheckIn.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
	    	if(newValue) return;
	    	
	    	if(txtCheckIn.getValue() == null){
	    		setItem_reservaValido(false);
	    		
	    		Alerta alerta = new Alerta(getStage().getTitle(), "Data do Check-In não informada!");
	    		alerta.Erro(getStage());
	    	}
    	});
	    
	    txtCheckOut.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
	    	if(newValue) return;
	    	
	    	txtDiasEstadia.setDisable(false);
			if(txtCheckIn.getValue() == null) return;
			
	    	if(!validaCheckOut()) return;

	    	long diasDiferenca = 1;
	    	diasDiferenca += Math.abs(txtCheckOut.getValue().toEpochDay() - txtCheckIn.getValue().toEpochDay());
	    	
	    	if(diasDiferenca == 0) diasDiferenca = 1;
	    	
	    	txtDiasEstadia.setText(Long.toString(diasDiferenca));
	    	
	    	if(diasDiferenca > 0){
	    		txtDiasEstadia.setDisable(true);
	    		
	    		if(txtValor.getText().isEmpty()) txtValor.setText("0");
	    		double valor= Double.parseDouble(txtValor.getText());
	    		
	    		if(getQuartoReference() != null)
	    			valor = diasDiferenca * getQuartoReference().getValorQuarto();
	    		
	    		txtValor.setText(Double.toString(valor));
	    	}
	    });
	}
	
	private void iniciaTableView(){
		colCodigoQuarto.setCellValueFactory(c -> {
			if(c.getValue() != null){
				return c.getValue().getPK().getQuarto().getCodigoProperty().asObject();
			}else{
				return new SimpleIntegerProperty(0).asObject();
			}
		});	

		colDescricaoQuarto.setCellValueFactory(c -> {
			if(c.getValue() != null){
				return c.getValue().getPK().getQuarto().getNomeProperty();
			}else{
				return new SimpleStringProperty("");
			}
		});		

		colCheckIn.setCellValueFactory( item_reserva ->{
				SimpleStringProperty property = new SimpleStringProperty();
			      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			      if(item_reserva.getValue().getCheckIn() != null)
			    	  property.setValue(dateFormat.format(item_reserva.getValue().getCheckIn()));
			      return property;
			   }
			);
		
		colCheckOut.setCellValueFactory( item_reserva -> {
				SimpleStringProperty property = new SimpleStringProperty();
			      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			      if(item_reserva.getValue().getCheckOut() != null)
			    	  property.setValue(dateFormat.format(item_reserva.getValue().getCheckOut()));
			      return property;
			   }
			);
		
		colDiasEstadia.setCellValueFactory(new Callback<CellDataFeatures<Item_reserva, Integer>, ObservableValue<Integer>>() {
			    @Override
			    public ObservableValue<Integer> call(CellDataFeatures<Item_reserva, Integer> p) {
			        return new SimpleIntegerProperty(p.getValue().getDiasReserva()).asObject();
			    } 
			});
		
		colDiasEstadia.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, Integer>("diasReserva")
			);

		colValor.setCellValueFactory(
			    new PropertyValueFactory<Item_reserva, Double>("valorReserva")
			);
	}

	private boolean quartoJaExiste(Item_reserva item_reserva){
		for(Item_reserva item_reserva2 : tbvItemReserva.getItems()){
			if(item_reserva2.getPK().getQuarto().getCodigo() == item_reserva.getPK().getQuarto().getCodigo())
				return true;
		}
		return false;
	}
	
	private void cancelaReserva(){
		Reserva reserva = getReserva();
		Alerta alerta;
		
		if(reserva.cancelaReserva()){
			alerta = new Alerta(getStage().getTitle(), "Reserva cancelada.");
			alerta.Mensagem(getStage());
			
			limpaTela();
		}else{
			alerta = new Alerta(getStage().getTitle(), reserva.getErrors());
			alerta.Erro(getStage());
		}
	}
	
	/* Validações especificas*/
	private boolean validaPessoa(){
		if(txtCodigoPessoa.getText() == "0" || txtCodigoPessoa.getText().isEmpty()){
    		setReservaValida(false);
    		
    		Alerta alerta = new Alerta(getStage().getTitle(), "Pessoa não informada!");
    		
    		alerta.Erro(getStage());
    	}
		return true;
	}

	private boolean validaQuarto(){
		if(txtCodigoQuarto.getText() == "0" || txtCodigoQuarto.getText().isEmpty()){
    		setItem_reservaValido(false);
    		
    		Alerta alerta = new Alerta(getStage().getTitle(), "Quarto não informado!");
    		
    		alerta.Erro(getStage());
    	}
		return true;
	}
	
	private boolean validaCheckOut(){
    	if(txtCheckOut.getValue() == null) return true;
    	
    	if(txtCheckIn.getValue().isAfter(txtCheckOut.getValue())){
    		Alerta alerta = new Alerta(getStage().getTitle(), "Data do Check-Out não pode ser menor que data do Check-In!");
    		
    		alerta.Erro(getStage());
    		return false;
    	}
    	return true;
	}	
	
	private boolean validaItemReserva(){
		int count = 3; // Número de validações
		int countAux = 1;
		
		setItem_reservaValido(true);
		while(isItem_reservaValido() && countAux < count){
			switch(countAux){
				case 1:
					Event.fireEvent(txtCodigoQuarto, new ActionEvent());
					break;
				case 2:
					Event.fireEvent(txtCheckIn, new ActionEvent());
					break;				
				case 3:
					//Event.fireEvent(txtCheckOut, new ActionEvent());
					setItem_reservaValido(validaCheckOut());
					break;
			}
			countAux++;
		}
		
		return isItem_reservaValido();
	}
	
	private boolean validaReserva(){
		int count = 2; // numero de Validações
		int countAux = 1;
		
		setReservaValida(true);
		while(isReservaValida() && countAux < count){
			switch(countAux){
				case 1:
					Event.fireEvent(txtCodigoPessoa, new ActionEvent());
					break;
				case 2:
					setReservaValida(validaNivel2());
					break;				
			}
			countAux++;
		}
		
		return isReservaValida();
	}

	private boolean validaNivel2(){
		if(tbvItemReserva.getItems().size() == 0){
			
			Reserva reserva = getReserva();
			if(reserva.getCodigoReserva() != 0){
				Alerta alerta = new Alerta(getStage().getTitle(), "Reserva não possui nenhum quarto reservado, deseja cancela-la?");
				
				if(alerta.Confirm(getStage())){
					cancelaReserva();
				}
			}
			return false;
		}else{
			return true;
		}
	}
}