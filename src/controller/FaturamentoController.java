package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.Persistence;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import model.Faturamento;
import model.Item_faturado;
import model.Item_reserva;
import model.Item_servico;
import model.Pessoa;
import model.Produto;
import model.Quarto;
import model.Reserva;
import model.dao.FaturamentoDAO;
import model.dao.Item_faturadoDAO;
import model.dao.Item_reservaDAO;
import model.dao.Item_servicoDAO;
import model.enums.StatusReserva;
import model.enums.StatusServico;
import model.pk.Item_faturadoPK;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.Alerta;
import view.ConsultaPessoaView;
import view.ConsultaProdutoView;
import view.ConsultaQuartoView;

public class FaturamentoController extends ControllerDefault{
	private Reserva reserva = new Reserva();
	private Pessoa pessoa = new Pessoa();
	private Quarto quarto = new Quarto();
	private Produto produto = new Produto();
	
	private boolean faturamentoValido;
	private boolean item_faturadoValido;
	private ObservableList<Item_faturado> tableViewData;
	
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
	
	@FXML
	private Button btnImprimir;
	
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
	
	public Produto getProdutoReference(){
		Produto produto = new Produto();
		// Copia Propriedades para não perder os binbings com um novo objeto
		try{
			BeanUtils.copyProperties(produto, getProduto());
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
		return produto;
	}

	public Produto getProduto(){
		return produto;
	}
	
	
	public Faturamento getFaturamento(){
		if(getModel() == null)
			setModel(new Faturamento());
		
		return (Faturamento) getModel();
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
		
		// Lista observavel de itens na table view
		List<Item_faturado> lista = new ArrayList<Item_faturado>();
		tableViewData = FXCollections.observableList(lista);
		
		// Adiciona listener pra somar total
		tableViewData.addListener((ListChangeListener<Item_faturado>) pChange -> {
			ObservableList<? extends Item_faturado> list = pChange.getList();
		    
			double valorServico=0;
			for(Item_faturado its : list){
				valorServico += its.getValorTotal();
			}
			
			if(txtValorReserva.getText().isEmpty())
				txtValorReserva.setText("0");
			
			double valorReserva = Double.parseDouble(txtValorReserva.getText());
			txtValorServico.setText(Double.toString(valorServico));
			txtValorFaturado.setText(Double.toString(valorServico + valorReserva));
		});
		
		tbvItem_faturado.setItems(tableViewData);
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
	    
	    btnPesquisaProduto.setOnAction(evt -> {
			ConsultaProdutoView consultaProdutoView = new ConsultaProdutoView();

			try {
				consultaProdutoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);

				ConsultaProdutoController controller = consultaProdutoView.getFxmlLoader()
						.<ConsultaProdutoController> getController();
				if (controller.getModel() != null) {
					setProduto((Produto) controller.getModel());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    });
	}
	
	private void eventosBotoes(){
		 btnAddLinha.setOnAction(evt -> {
	    		Item_faturado item_faturado = new Item_faturado();

	    		if(txtCodigoProduto.getText().isEmpty())
	    			txtCodigoProduto.setText("0");    		
	    		
	    		if(!validaItemFaturamento())
	    			return;
	    		
	    		item_faturado.getPK().setProduto(getProdutoReference());
	    		
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
	    			tableViewData.add(item_faturado);
	    			setProduto(new Produto());
	    			
					txtQuantidade.setText("0");
					txtValorUnitario.setText("0");
					txtValorTotal.setText("0");
					
					txtCodigoProduto.requestFocus();
	    		}
		    });
		 
			btnSalvar.setOnAction(evt -> {
		    	if(!validaFaturamento()){
		    		return;
		    	}
		    	Alerta alerta;
		    	
		    	// Nivel 1
		    	Faturamento faturamento = getFaturamento();
		    	FaturamentoDAO faturamentoDAO = new FaturamentoDAO();

		    	if(getReserva().getCodigoReserva() != 0)
		    		faturamento.setReserva(getReserva());
		    	else{
		    		faturamento.setReserva(null);
		    	}
		    	
		    	faturamento.setPessoa(getPessoa());
		    	
		    	if(getQuarto().getCodigo() != 0)
		    		faturamento.setQuarto(getQuarto());
		    	else{
		    		faturamento.setQuarto(null);
		    	}
		    	faturamento.setDataEmissao(txtDataEmissao.getValue());

		    	faturamento.setValorReserva(Double.parseDouble(txtValorReserva.getText()));		    	
		    	faturamento.setValorServico(Double.parseDouble(txtValorServico.getText()));
		    	faturamento.setValorTotal(Double.parseDouble(txtValorFaturado.getText()));
		    	if(faturamento.getCodigo() == 0){
		    		
		    		faturamento = faturamentoDAO.insert(faturamento);
		    		alerta = new Alerta(getStage().getTitle(), "Código do faturamento cadastrado " + faturamento.getCodigo());
		    	}else{
		    		faturamento = faturamentoDAO.update(faturamento);
		    		alerta = new Alerta(getStage().getTitle(), "Faturamento " + faturamento.getCodigo() + " atualizado!");
		    	}
		    	
		    	if(faturamento.getCodigo() != 0){
			    	// Nivel 2
			    	Item_faturadoDAO item_faturadoDAO = new Item_faturadoDAO();
			    	Item_faturadoPK pk;
		    		pk = new Item_faturadoPK();
		    		pk.setFaturamento(faturamento);
		    		
		    		HashMap<String, Object> parametros = new HashMap<String, Object>();
		    		parametros.put("faturamento", pk.getFaturamento().getCodigo());
		    		
		    		List<Item_faturado> listaBD = item_faturadoDAO.query("SELECT itf FROM Item_faturado itf" 
		    										+ " WHERE itf.pk.faturamento.codigo = :faturamento", parametros);
		    		
		    		int index;
			    	for(Item_faturado item_faturado : tableViewData){
			    		if(item_faturado.getPK().getServico().getCodigo() != 0)
			    			pk.setServico(item_faturado.getPK().getServico());
			    		else{
			    			pk.setServico(null);
			    		}
			    		pk.setProduto(item_faturado.getPK().getProduto());
			    		
			    		item_faturado.setPK(pk);		    		
			    		index = item_faturado.existeNaLista(listaBD);
			    		
			    		if(index == -1){
			    			item_faturadoDAO.insert(item_faturado);
			    		}else{
			    			item_faturadoDAO.update(item_faturado);
			    			listaBD.remove(index);
			    		}
			    	}
			    	
			    	// Se sobrou itens, exclui, não estão mais na tabela.
			    	for(Item_faturado itf : listaBD){
			    		item_faturadoDAO.delete(itf);
			    	}
			    	
			    	setModel(faturamento);
			    	txtCodigo.setText(Integer.toString(faturamento.getCodigo()));
		    		
		    		alerta.Mensagem(getStage());
		    		
		    	}else{
		    		alerta = new Alerta(getStage().getTitle(), "Erro ao cadastrar o faturamento.");
		    		alerta.Erro(getStage());
		    	}
		    });
		
		    btnDelLinha.setOnAction(evt -> {
		    		ObservableList<Item_faturado> list = tbvItem_faturado.getSelectionModel().getSelectedItems();
		
		    		if(list.size() > 0){
		    			Alerta alerta = new Alerta("Faturamento", "Deseja mesmo excluir este(s) produto(s) do faturamento?");
		
		    			if(alerta.Confirm(getStage()))
		    				tableViewData.removeAll(list);
		    		}
		        }
		    );
		    
		    btnCarregarServico.setOnAction(evt -> {
		    	buscaServicosAbertos();
		    });
		    
		    btnImprimir.setOnAction(evt ->{
		    	imprimeFaturamento();
		    });
	}
	
	private void eventosCampos(){
		txtCodigoReserva.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
    		txtCodigoQuarto.setDisable(false);			
			
			validaReserva();
		
		});
		
		txtCodigoPessoa.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			validaPessoa();
		});

		txtCodigoQuarto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			validaQuarto();
		});
		
		txtCodigoProduto.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if(newValue) return;
			
			validaProduto();
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
    		if(getPessoa().getCodigo() != 0 && getQuarto().getCodigo() != 0){
   				adicionaServicos(getPessoa(), getQuarto());
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
    
    private void imprimeFaturamento(){
    	if(getFaturamento().getCodigo() == 0){
    		Alerta alerta = new Alerta(getStage().getTitle(), "Nenhum faturamento selecionado!");
    		alerta.Erro(getStage());
    	}else{
    		JasperReport jasperReport;
    		try {    			
    			OpenJPAEntityManagerFactory emf = (OpenJPAEntityManagerFactory) Persistence
    	                .createEntityManagerFactory("ProjetoInterOPENJPA");
    			
    			OpenJPAEntityManager em = emf.createEntityManager();
    		    OpenJPAEntityManager oem = em.unwrap(OpenJPAEntityManager.class);
    		    Connection jdbcConnection = (Connection) oem.getConnection();
    			
    		    java.io.File file = new java.io.File("CheckOut.jrxml");
    	        String path = file.getAbsolutePath();
    	        String only_path = path.substring(0,path.lastIndexOf('\\'));
 
    			jasperReport = JasperCompileManager.compileReport(only_path + "/src/view/relatorios/CheckOut.jrxml");
				
				Map<String, Object> parametersMap = new HashMap<String, Object>();

				parametersMap.put("codigoFaturamento",getFaturamento().getCodigo());				
				parametersMap.put("codigoPessoa",getFaturamento().getPessoa().getCodigo());
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, parametersMap, jdbcConnection);
				
				JasperViewer.viewReport(jasperPrint);
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}
    }
    
    // Busca os serviços do quarto ou reserva e adiciona no tableview
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
					tableViewData.add(item_faturado);
	    		}
			}
		}

    }
    
    /* Validações especificas */
    private void validaReserva(){
    	setFaturamentoValido(true);
    	if(txtCodigoReserva.getText().isEmpty()){
			txtCodigoReserva.setText("0");
		}
		
		int codigo = Integer.parseInt(txtCodigoReserva.getText());
		getReserva().setCodigoReserva(codigo);
		
		if(codigo != 0){
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
	    		Alerta alerta = new Alerta(getStage().getTitle(), getReserva().getErrors());
	    		
	    		alerta.Erro(getStage());
			}
		}
    }
    
	private void validaPessoa() {
		setFaturamentoValido(true);
		Pessoa pessoa = getPessoa(); 
				
		if (pessoa.getCodigo() == 0){
			setFaturamentoValido(false);
			setPessoa(new Pessoa());
		}else{
			pessoa = getPessoa().exists();

			if (pessoa != null) {
				setPessoa(pessoa);
			} else {
				setFaturamentoValido(false);
				Alerta alerta = new Alerta(getStage().getTitle(), getPessoa().getErrors());

				alerta.Erro(getStage());
			}
		}
	}

	private void validaQuarto() {
		setFaturamentoValido(true);
		Quarto quarto = getQuarto();
		
		if (quarto.getCodigo() == 0) {
			if(getReserva().getCodigoReserva() == 0)
				setFaturamentoValido(false);
			
			setQuarto(new Quarto());
		}else{
			quarto = getQuarto().exists();

			if (quarto != null) {
				setQuarto(quarto);
			} else {
				setFaturamentoValido(false);
				Alerta alerta = new Alerta(getStage().getTitle(), getQuarto().getErrors());

				alerta.Erro(getStage());
			}
		}
	}
	private void validaProduto(){
		setItem_faturadoValido(true);	
		Produto produto = getProduto();
		
		if(produto.getCodigo() == 0){
			setItem_faturadoValido(false);
			setProduto(new Produto());
    	}
		else{
			produto = getProduto().exists();
			
    		if(produto != null){
    			setProduto(produto);
    			
    			txtValorUnitario.setText(getProduto().getValorProduto().toString());
    			
    			if(!txtQuantidade.getText().isEmpty() && !txtValorUnitario.getText().isEmpty()){
    				double valorTotal = Integer.parseInt(txtQuantidade.getText()) * Double.parseDouble(txtValorUnitario.getText());
    				
    				txtValorTotal.setText(Double.toString(valorTotal));
    			}
    		}
    		else{
    			setItem_faturadoValido(false);
        		Alerta alerta = new Alerta(getStage().getTitle(), getProduto().getErrors());
        		
        		alerta.Erro(getStage());
    		}
    	}
	}
	
	private boolean validaFaturamento(){
		int count = 3; // numero de Validações
		int countAux = 1;
		
		setFaturamentoValido(true);
		while(isFaturamentoValido() && countAux < count){
			switch(countAux){
				case 1:
					validaPessoa();
					break;
				case 2:
					validaReserva();
					break;
				case 3:
					validaQuarto();
					break;					
			}
			countAux++;
		}
		
		return isFaturamentoValido();
	}
	
	private boolean validaItemFaturamento(){
		int count = 1; // Número de validações
		int countAux = 1;
		
		setItem_faturadoValido(true);
		while(isItem_faturadoValido() && countAux < count){
			switch(countAux){
				case 1:
					validaProduto();
					break;
			}
			countAux++;
		}
		
		return isItem_faturadoValido();
	}
}
