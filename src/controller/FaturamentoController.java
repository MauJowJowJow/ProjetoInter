package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;
import model.Item_faturado;
import model.Item_reserva;
import model.Item_servico;
import model.Pessoa;
import model.Produto;
import model.Quarto;
import model.Reserva;
import model.dao.Item_reservaDAO;
import model.dao.Item_servicoDAO;
import model.dao.ServicoDAO;
import model.enums.StatusReserva;
import model.enums.StatusServico;
import model.pk.Item_faturadoPK;
import util.Alerta;
import view.ConsultaPessoaView;

public class FaturamentoController extends ControllerDefault{
	private Reserva reserva = new Reserva();
	private Pessoa pessoa = new Pessoa();
	private Quarto quarto = new Quarto();
	private Produto produto = new Produto();
	
	private boolean faturamentoValido;
	private boolean item_faturadoValido;
	
	@FXML
	private TextField txtCodigo;
	
	@FXML 
	private DatePicker txtDataEmissao;

	@FXML
	private TextField txtCodigoReserva;
	
	@FXML
	private TextField txtCodigoPessoa;
	
	@FXML
	private Button btnPesquisaPessoa;
	
	@FXML
	private TextField txtNomePessoa;
	
	@FXML
	private TextField txtCodigoQuarto;
	
	@FXML
	private Button btnPesquisaQuarto;
	
	@FXML
	private TextField txtNomeQuarto;
	
	@FXML
	private TextField txtCodigoProduto;
	
	@FXML
	private Button btnPesquisaProduto;
	
	@FXML
	private TextField txtNomeProduto;
	
	@FXML
	private TextField txtQuantidade;
	
	@FXML
	private TextField txtValorUnitario;
	
	@FXML
	private TextField txtValorTotal;
	
	@FXML
	private Button btnAddLinha;
	
	@FXML
	private Button btnDelLinha;
    
    @FXML
    private TableView<Item_faturado> tbvItem_faturado;
    
    @FXML
    private TableColumn<Item_faturado, Integer> tbcCodigoServico;
    @FXML
    private TableColumn<Item_faturado, Integer> tbcCodProduto;
    @FXML
    private TableColumn<Item_faturado, String> tbcNomeProduto;
    @FXML
    private TableColumn<Item_faturado, Integer> tbcQuantidade;
    @FXML
    private TableColumn<Item_faturado, Double> tbcValorUnitario;
    @FXML
    private TableColumn<Item_faturado, Double> tbcValorTotal;
          
    @FXML
    private TextField txtValorReserva;
        
	@FXML
	private TextField txtValorServico;
    
	@FXML
	private TextField txtValorFaturado;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnCarregarServico;
	
	public boolean isFaturamentoValido() {
		return faturamentoValido;
	}

	public void setFaturamentoValido(boolean faturamentoValido) {
		this.faturamentoValido = faturamentoValido;
	}

	public boolean isItem_faturadoValido() {
		return item_faturadoValido;
	}

	public void setItem_faturadoValido(boolean item_faturadoValido) {
		this.item_faturadoValido = item_faturadoValido;
	}
	
	public void setReserva(Reserva reserva){
		// Copia Propriedades para não perder os binbings com um novo objeto
		try{
			BeanUtils.copyProperties(this.reserva, reserva);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Reserva getReserva(){
		return this.reserva;
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
		return quarto;
	}
	
	public void setProduto(Produto produto){
		// Copia Propriedades para não perder os binbings com um novo objeto
		try{
			BeanUtils.copyProperties(this.produto, produto);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}

	public Produto getProduto(){
		return produto;
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		txtDataEmissao.setValue(LocalDate.now());
		
		imagensBotoes();
		criaBindings();
		eventosPesquisa();
		eventosBotoes();
		eventosCampos();
	    iniciaTableView();
	}
	
	private void criaBindings(){
		// Binda Pessoa
		txtCodigoPessoa.textProperty().bindBidirectional(getPessoa().getCodigoProperty(), new NumberStringConverter());
		txtNomePessoa.textProperty().bindBidirectional(getPessoa().getNomeProperty());
		
		// Binda Quarto
		txtCodigoQuarto.textProperty().bindBidirectional(getQuarto().getCodigoProperty(), new NumberStringConverter());
		txtNomeQuarto.textProperty().bindBidirectional(getQuarto().getNomeProperty());
		
		// Binda Produto
		txtCodigoProduto.textProperty().bindBidirectional(getProduto().getCodigoProperty(), new NumberStringConverter());
		txtNomeProduto.textProperty().bindBidirectional(getProduto().getDescProdutoProperty());
	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
	    btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    btnPesquisaQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    btnPesquisaProduto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	    
	    btnAddLinha.setGraphic(new ImageView(classLoader.getResource("Plus.png").toString()));
		btnDelLinha.setGraphic(new ImageView(classLoader.getResource("minus.png").toString()));
	}
	
	private void eventosPesquisa(){
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
	    
	    btnPesquisaProduto.setOnAction(evt -> {
	    	
	    });
	}
	
	private void eventosBotoes(){
		 btnAddLinha.setOnAction(evt -> {
	    		Item_faturado item_faturado = new Item_faturado();

	    		if(txtCodigoProduto.getText().isEmpty())
	    			txtCodigoProduto.setText("0");    		
	    		
	    		//if(!validaItemReserva())
	    		//	return;
	    		
	    		item_faturado.getPK().setProduto(getProduto());
	    		
	    		if(txtQuantidade.getText().isEmpty())
	    			txtQuantidade.setText("0");
	    		
	    		item_faturado.setQuantidadeItem(Integer.parseInt(txtQuantidade.getText()));
	    		
	    		if(txtValorUnitario.getText().isEmpty())
	    			txtValorUnitario.setText("0");
	    		
	    		item_faturado.setValorUnitario(Double.parseDouble(txtValorUnitario.getText()));
	    		
	    		if(txtValorTotal.getText().isEmpty())
	    			txtValorTotal.setText("0");
	    		
	    		item_faturado.setValorTotal(Double.parseDouble(txtValorTotal.getText()));
	    		
	    		if(produtoJaExiste(item_faturado)){
	    			Alerta alerta = new Alerta("Faturamento", "Produto já foi inserido!");
	    			alerta.Erro(getStage());
	    		}else{
	    			tbvItem_faturado.getItems().add(item_faturado);
	    			setProduto(null);
	    		}
		    });
		
		    btnDelLinha.setOnAction(evt -> {
		    		ObservableList<Item_faturado> list = tbvItem_faturado.getSelectionModel().getSelectedItems();
		
		    		if(list.size() > 0){
		    			Alerta alerta = new Alerta("Faturamento", "Deseja mesmo excluir este(s) produto(s) do faturamento?");
		
		    			if(alerta.Confirm(getStage()))
		    				tbvItem_faturado.getItems().removeAll(list);
		    		}
		        }
		    );
		    
		    btnCarregarServico.setOnAction(evt -> {
		    	buscaServicosAbertos();
		    });
	}
	
	private void eventosCampos(){
		txtCodigoReserva.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
    		txtCodigoQuarto.setDisable(false);			
			
			if(txtCodigoReserva.getText().isEmpty()){
				txtCodigoReserva.setText("0");
				return;
			}
			
			int codigo = Integer.parseInt(txtCodigoReserva.getText());
			getReserva().setCodigoReserva(codigo);
			
			Reserva reserva = getReserva().exists();
    		
    		if(reserva != null){
    			setReserva(reserva);
    			
    			txtValorReserva.setText(Double.toString(reserva.getValorTotal()));
    			
    			setPessoa(getReserva().getPessoa());
    			atualizaValorTotal();
    			
    			setQuarto(new Quarto());
    			txtCodigoQuarto.setDisable(true);
    		}
    		else{
    			setFaturamentoValido(false);
        		Alerta alerta = new Alerta("Faturamento", getReserva().getErrors());
        		
        		alerta.Erro(getStage());
    		}
		
		});
		
		txtCodigoPessoa.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
			setFaturamentoValido(true);
    		
			if(!validaPessoa()){
				setItem_faturadoValido(false);
			}else{
				Pessoa pessoa = getPessoa().exists();
	    		
	    		if(pessoa != null){
	    			setPessoa(pessoa);
	    		}
	    		else{
	    			setFaturamentoValido(false);
	        		Alerta alerta = new Alerta("Faturamento", getPessoa().getErrors());
	        		
	        		alerta.Erro(getStage());
	    		}
			}
		});
		
		txtCodigoQuarto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
			setFaturamentoValido(true);
    		
			if(!validaQuarto()){
				setFaturamentoValido(false);
			}else{
				Quarto quarto = getQuarto().exists();
	    		
	    		if(quarto != null){
	    			setQuarto(quarto);
	    		}
	    		else{
	    			setFaturamentoValido(false);
	        		Alerta alerta = new Alerta("Faturamento", getQuarto().getErrors());
	        		
	        		alerta.Erro(getStage());
	    		}
			}
		});
		
		txtCodigoProduto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
			setItem_faturadoValido(true);
    		
			if(!validaProduto()){
				setItem_faturadoValido(false);
			}else{
				Produto produto = getProduto().exists();
	    		
	    		if(produto != null){
	    			setProduto(produto);
	    			
	    			if(txtValorUnitario.getText().isEmpty())
	    				txtValorUnitario.setText(getProduto().getValorProduto().toString());
	    		}
	    		else{
	    			setItem_faturadoValido(false);
	        		Alerta alerta = new Alerta("Faturamento", getProduto().getErrors());
	        		
	        		alerta.Erro(getStage());
	    		}
			}
		});
		
		txtQuantidade.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
			if(txtQuantidade.getText().isEmpty())
				txtQuantidade.setText("0");
			
			if(txtValorUnitario.getText().isEmpty())
				txtValorUnitario.setText("0");
			
			txtValorTotal.setText(
					Double.toString(
							Integer.parseInt(txtQuantidade.getText()) * Double.parseDouble(txtValorUnitario.getText())
					));
		});
	}
	
    private void iniciaTableView(){
    	
    	tbcCodigoServico.setCellValueFactory(c -> {
			if(c.getValue() != null){
				return new SimpleIntegerProperty(c.getValue().getPK().getServico().getCodigo()).asObject();
			}else{
				return new SimpleIntegerProperty(0).asObject();
			}
		});
    	
    	tbcCodProduto.setCellValueFactory(c -> {
			if(c.getValue() != null){
				return c.getValue().getPK().getProduto().getCodigoProperty().asObject();
			}else{
				return new SimpleIntegerProperty(0).asObject();
			}
		});
    	
    	tbcNomeProduto.setCellValueFactory(c -> {
			if(c.getValue() != null){
				return c.getValue().getPK().getProduto().getDescProdutoProperty();
			}else{
				return new SimpleStringProperty("");
			}
		});
    	
    	tbcQuantidade.setCellValueFactory(new PropertyValueFactory<Item_faturado, Integer>("quantidadeItem"));   	
    	tbcValorUnitario.setCellValueFactory(new PropertyValueFactory<Item_faturado, Double>("valorUnitario"));
    	tbcValorTotal.setCellValueFactory(new PropertyValueFactory<Item_faturado, Double>("valorTotal"));
    }
    
    private boolean produtoJaExiste(Item_faturado item_faturado){
    	for(Item_faturado item_faturado2 : tbvItem_faturado.getItems()){
			if(item_faturado2.getPK().getProduto().getCodigo() == item_faturado.getPK().getProduto().getCodigo() &&
			   item_faturado2.getPK().getServico().getCodigo() == item_faturado.getPK().getServico().getCodigo())
				
				return true;
		}
		return false;
    }

    private void atualizaValorTotal(){
    	if(txtValorReserva.getText().isEmpty())
    		txtValorReserva.setText("0");
    	
    	if(txtValorServico.getText().isEmpty())
    		txtValorServico.setText("0");
    	
		txtValorFaturado.setText(
				Double.toString(
						Double.parseDouble(txtValorReserva.getText()) + Double.parseDouble(txtValorServico.getText())
				));
    }
    
    private void buscaServicosAbertos(){
    	// Se Não tem reserva, pega pelo Quarto. Se não varre todos os quartos reservados
    	if(getReserva().getCodigoReserva()== 0){
    		if(validaPessoa()){
    			if(validaQuarto()){
    				adicionaServicos(getPessoa(), getQuarto());
    			}
    		}
    	}else{
    		Item_reservaDAO item_reservaDAO = new Item_reservaDAO();
    		
    		Map<String, Object> parametros = new HashMap<String, Object>();
    		
    		parametros.put("reserva", getReserva().getCodigoReserva());
    		parametros.put("status", StatusReserva.Aberta);

    		List<Item_reserva> arr = item_reservaDAO.query("select itr from Item_reserva as itr, Reserva as r"
    				+ " WHERE itr.pk.reserva.codigoReserva = r.codigoReserva"
    				+ " AND r.codigoReserva = :reserva"
    				+ " AND itr.statusReserva = :status", parametros);
    		
    		for(Item_reserva item_reserva : arr){
    			adicionaServicos(item_reserva.getPK().getReserva().getPessoa(), item_reserva.getPK().getQuarto());
    		}
    	}
    }
    
    private void adicionaServicos(Pessoa pessoa, Quarto quarto){
		Item_servicoDAO item_servicoDAO = new Item_servicoDAO();
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("status", StatusServico.Aberto);
		parametros.put("pessoa", pessoa.getCodigo());
		parametros.put("quarto", quarto.getCodigo());
		
		List<Item_servico> arr = item_servicoDAO.query("select its from Item_servico as its, Servico as s"
				+ " WHERE its.pk.servico.codigo = s.codigo AND"
				+ " s.statusServico = :status AND"
				+ " s.pessoa.codigo = :pessoa AND"
				+ " s.quarto.codigo = :quarto", parametros);

		if(!arr.isEmpty()){
			for(Item_servico its : arr){
				Item_faturadoPK item_faturadoPK = new Item_faturadoPK();
				Item_faturado item_faturado = new Item_faturado();
				
				item_faturadoPK.setProduto(its.getPK().getProduto());
				item_faturadoPK.setServico(its.getPK().getServico());
				item_faturado.setPK(item_faturadoPK);
				
				item_faturado.setQuantidadeItem(its.getQuantidadeVendido());
				item_faturado.setValorUnitario(its.getValorUnitario());
				item_faturado.setValorTotal(its.getValorTotal());
				
				if(!produtoJaExiste(item_faturado)){
	    			tbvItem_faturado.getItems().add(item_faturado);
	    			setProduto(null);
	    		}
			}
		}

    }
    
    /* Validações especificas*/
	private boolean validaPessoa(){
		if(txtCodigoPessoa.getText() == "0" || txtCodigoPessoa.getText().isEmpty()){
    		setFaturamentoValido(false);
    		
    		Alerta alerta = new Alerta("Faturamento", "Pessoa não informada!");
    		
    		alerta.Erro(getStage());
    	}
		return true;
	}

	private boolean validaQuarto(){
		if(txtCodigoQuarto.getText() == "0" || txtCodigoQuarto.getText().isEmpty()){
			setFaturamentoValido(false);
    		
    		Alerta alerta = new Alerta("Faturamento", "Quarto não informado!");
    		
    		alerta.Erro(getStage());
    	}
		return true;
	}

	private boolean validaProduto(){
		if(txtCodigoProduto.getText() == "0" || txtCodigoProduto.getText().isEmpty()){
			setItem_faturadoValido(false);
    		
    		Alerta alerta = new Alerta("Faturamento", "Produto não informado!");
    		
    		alerta.Erro(getStage());
    	}
		return true;
	}
}
