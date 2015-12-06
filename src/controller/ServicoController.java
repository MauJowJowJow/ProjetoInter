package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
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
import model.Item_servico;
import model.Pessoa;
import model.Produto;
import model.Quarto;
import model.Servico;
import model.enums.StatusServico;
import model.dao.Item_servicoDAO;
import model.dao.ServicoDAO;
import model.pk.Item_servicoPK;
import util.Alerta;
import view.ConsultaPessoaView;
import view.ConsultaQuartoView;

public class ServicoController extends ControllerDefault {
	private Pessoa pessoa = new Pessoa();
	private Quarto quarto = new Quarto();
	private Produto produto = new Produto();

	private boolean servicoValido;
	private boolean item_servicoValido;

	@FXML
	private TextField txtCodigo;

	@FXML
	private DatePicker txtDataEmissao;

	@FXML
	private TextField txtCodigoServico;

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
	private TableView<Item_servico> tbvItem_servico;

	@FXML
	private TableColumn<Item_servico, Integer> tbcCodProduto;
	@FXML
	private TableColumn<Item_servico, String> tbcNomeProduto;
	@FXML
	private TableColumn<Item_servico, Integer> tbcQuantidade;
	@FXML
	private TableColumn<Item_servico, Double> tbcValorUnitario;
	@FXML
	private TableColumn<Item_servico, Double> tbcValorTotal;

	@FXML
	private TextField txtValorServico;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCarregarServico;
	
	@FXML
	private Button btnSair;
	
	ObservableList<Item_servico> data;
	
	private ObservableList<? extends Item_servico> list;

	public boolean isServicoValido() {
		return servicoValido;
	}

	public void setServicoValido(boolean servicoValido) {
		this.servicoValido = servicoValido;
	}

	public boolean isItem_servicoValido() {
		return item_servicoValido;
	}

	public void setItem_servicoValido(boolean item_servicoValido) {
		this.item_servicoValido = item_servicoValido;
	}
	
	public Servico getServico(){
		if(getModel() == null)
			setModel(new Servico());
		
		return (Servico) getModel();
	}

	public void setPessoa(Pessoa pessoa) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.pessoa, pessoa);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setQuarto(Quarto quarto) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.quarto, quarto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setProduto(Produto produto) {
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(this.produto, produto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public Produto getProdutoReference() {
		Produto produto = new Produto();
		// Copia Propriedades para não perder os binbings com um novo objeto
		try {
			BeanUtils.copyProperties(produto, this.produto);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return produto;
	}

	public Produto getProduto() {
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

	private void criaBindings() {
		// Binda Pessoa
		txtCodigoPessoa.textProperty().bindBidirectional(getPessoa().getCodigoProperty(), new NumberStringConverter());
		txtNomePessoa.textProperty().bindBidirectional(getPessoa().getNomeProperty());

		// Binda Quarto
		txtCodigoQuarto.textProperty().bindBidirectional(getQuarto().getCodigoProperty(), new NumberStringConverter());
		txtNomeQuarto.textProperty().bindBidirectional(getQuarto().getNomeProperty());

		// Binda Produto
		txtCodigoProduto.textProperty().bindBidirectional(getProduto().getCodigoProperty(),
				new NumberStringConverter());
		txtNomeProduto.textProperty().bindBidirectional(getProduto().getDescProdutoProperty());
	}

	private void imagensBotoes() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		btnPesquisaPessoa.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		btnPesquisaQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		btnPesquisaProduto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));

		btnAddLinha.setGraphic(new ImageView(classLoader.getResource("Plus.png").toString()));
		btnDelLinha.setGraphic(new ImageView(classLoader.getResource("minus.png").toString()));
	}

	private void eventosPesquisa() {
		btnPesquisaPessoa.setOnAction(evt -> {
			ConsultaPessoaView consultaPessoaView = new ConsultaPessoaView();

			try {
				consultaPessoaView.iniciaTela(getScene(), Modality.WINDOW_MODAL);

				ConsultaPessoaController controller = consultaPessoaView.getFxmlLoader()
						.<ConsultaPessoaController> getController();
				if (controller.getModel() != null) {
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

				ConsultaQuartoController controller = consultaQuartoView.getFxmlLoader()
						.<ConsultaQuartoController> getController();
				if (controller.getModel() != null) {
					setQuarto((Quarto) controller.getModel());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		btnPesquisaProduto.setOnAction(evt -> {

		});
	}

	private void eventosBotoes() {
		btnAddLinha.setOnAction(evt -> {
			Item_servico item_servico = new Item_servico();

			if (txtCodigoProduto.getText().isEmpty())
				txtCodigoProduto.setText("0");

			if(!validaItemServico())
				return;

			item_servico.getPK().setProduto(getProdutoReference());

			if (txtQuantidade.getText().isEmpty())
				txtQuantidade.setText("0");

			item_servico.setQuantidadeVendido(Integer.parseInt(txtQuantidade.getText()));

			if (txtValorUnitario.getText().isEmpty())
				txtValorUnitario.setText("0");

			item_servico.setValorUnitario(Double.parseDouble(txtValorUnitario.getText()));

			if (txtValorTotal.getText().isEmpty())
				txtValorTotal.setText("0");

			item_servico.setValorTotal(Double.parseDouble(txtValorTotal.getText()));

			if (produtoJaExiste(item_servico)) {
				Alerta alerta = new Alerta(getStage().getTitle(), "Produto já foi inserido!");
				alerta.Erro(getStage());
			} else {
				//tbvItem_servico.getItems().add(item_servico);
				data.add(item_servico);
				setProduto(new Produto());
				txtQuantidade.setText("0");
				txtValorUnitario.setText("0");
				txtValorTotal.setText("0");
				
				txtCodigoProduto.requestFocus();
			}
		});
		
		btnSalvar.setOnAction(evt -> {
	    	if(!validaServico()){
	    		return;
	    	}
	    	Alerta alerta;
	    	
	    	// Nivel 1
	    	Servico servico = getServico();
	    	ServicoDAO servicoDAO = new ServicoDAO();

	    	servico.setPessoa(getPessoa());	    	
	    	servico.setQuarto(getQuarto());
	    	servico.setStatusServico(StatusServico.Aberto);
    		servico.setDataServico(txtDataEmissao.getValue());
	    	
	    	if(servico.getCodigo() == 0){
	    		
	    		servico = servicoDAO.insert(servico);
	    		alerta = new Alerta(getStage().getTitle(), "Código da servico cadastrado " + servico.getCodigo());
	    	}else{
	    		servico = servicoDAO.update(servico);
	    		alerta = new Alerta(getStage().getTitle(), "Servico " + servico.getCodigo() + " atualizado!");
	    	}
	    	
	    	if(servico.getCodigo() != 0){
		    	// Nivel 2
		    	Item_servicoDAO item_servicoDAO = new Item_servicoDAO();
		    	Item_servicoPK pk;
	    		pk = new Item_servicoPK();
	    		pk.setServico(servico);
	    		
	    		HashMap<String, Object> parametros = new HashMap<String, Object>();
	    		parametros.put("servico", pk.getServico().getCodigo());
	    		
	    		List<Item_servico> listaBD = item_servicoDAO.query("SELECT its FROM Item_servico its" 
	    										+ " WHERE its.pk.servico.codigo = :servico", parametros);
	    		
	    		int index;
		    	for(Item_servico item_servico : tbvItem_servico.getItems()){
		    		pk.setProduto(item_servico.getPK().getProduto());
		    		
		    		item_servico.getPK().setServico(servico);		    		
		    		index = item_servico.existeNaLista(listaBD);
		    		
		    		if(index == -1){
		    			item_servicoDAO.insert(item_servico);
		    		}else{
		    			item_servicoDAO.update(item_servico);
		    			listaBD.remove(index);
		    		}
		    	}
		    	
		    	// Se sobrou itens, exclui, não estão mais na tabela.
		    	for(Item_servico its : listaBD){
		    		item_servicoDAO.delete(its);
		    	}
		    	
		    	setModel(servico);
		    	txtCodigo.setText(Integer.toString(servico.getCodigo()));
	    		
	    		alerta.Mensagem(getStage());
	    		
	    	}else{
	    		alerta = new Alerta(getStage().getTitle(), "Erro ao cadastrar a servico.");
	    		alerta.Erro(getStage());
	    	}
	    });

		btnDelLinha.setOnAction(evt -> {
			ObservableList<Item_servico> list = tbvItem_servico.getSelectionModel().getSelectedItems();

			if (list.size() > 0) {
				Alerta alerta = new Alerta(getStage().getTitle(),
						"Deseja mesmo excluir este(s) produto(s) do serviço?");

				if (alerta.Confirm(getStage()))
					//tbvItem_servico.getItems().removeAll(list);
					data.removeAll(list);
			}
		});
		
		btnSair.setOnAction(evt -> {
			getStage().close();
		});
	}

	private void eventosCampos() {
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
			if (newValue)
				return;
			
			validaProduto();
		});

		txtQuantidade.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			// Só executa quanto perde o foco
			if (newValue)
				return;

			if (txtQuantidade.getText().isEmpty())
				txtQuantidade.setText("0");

			if (txtValorUnitario.getText().isEmpty())
				txtValorUnitario.setText("0");

			txtValorTotal.setText(Double.toString(
					Integer.parseInt(txtQuantidade.getText()) * Double.parseDouble(txtValorUnitario.getText())));
		});
	}

	private void iniciaTableView() {
		tbcCodProduto.setCellValueFactory(c -> {
			if (c.getValue() != null) {
				return c.getValue().getPK().getProduto().getCodigoProperty().asObject();
			} else {
				return new SimpleIntegerProperty(0).asObject();
			}
		});

		tbcNomeProduto.setCellValueFactory(c -> {
			if (c.getValue() != null) {
				return c.getValue().getPK().getProduto().getDescProdutoProperty();
			} else {
				return new SimpleStringProperty("");
			}
		});

		tbcQuantidade.setCellValueFactory(new PropertyValueFactory<Item_servico, Integer>("quantidadeVendido"));
		tbcValorUnitario.setCellValueFactory(new PropertyValueFactory<Item_servico, Double>("valorUnitario"));
		tbcValorTotal.setCellValueFactory(new PropertyValueFactory<Item_servico, Double>("valorTotal"));

		// Lista observavel de itens na table view
		List<Item_servico> lista = new ArrayList<Item_servico>();
		data = FXCollections.observableList(lista);
		
		// Adiciona listener pra somar total
		data.addListener((ListChangeListener<Item_servico>) pChange -> {
			list = pChange.getList();
		    
			double valorServico=0;
			for(Item_servico its : list){
				valorServico += its.getValorTotal();
			}
			txtValorServico.setText(Double.toString(valorServico));
		});
		
		tbvItem_servico.setItems(data);
	}

	private boolean produtoJaExiste(Item_servico item_servico) {
		for (Item_servico item_servico2 : tbvItem_servico.getItems()) {
			if (item_servico2.getPK().getProduto().getCodigo() == item_servico.getPK().getProduto().getCodigo()
					&& item_servico2.getPK().getServico().getCodigo() == item_servico.getPK().getServico()
							.getCodigo())

				return true;
		}
		return false;
	}

	/* Validações especificas */
	private void validaPessoa() {
		setServicoValido(true);
		Pessoa pessoa = getPessoa(); 
				
		if (pessoa.getCodigo() == 0){
			setServicoValido(false);
			setPessoa(new Pessoa());
		}else{
			pessoa = getPessoa().exists();

			if (pessoa != null) {
				setPessoa(pessoa);
			} else {
				setServicoValido(false);
				Alerta alerta = new Alerta(getStage().getTitle(), getPessoa().getErrors());

				alerta.Erro(getStage());
			}
		}
	}

	private void validaQuarto() {
		setServicoValido(true);
		Quarto quarto = getQuarto();
		
		if (quarto.getCodigo() == 0) {
			setServicoValido(false);
			setQuarto(new Quarto());
		}else{
			quarto = getQuarto().exists();

			if (quarto != null) {
				setQuarto(quarto);
			} else {
				setServicoValido(false);
				Alerta alerta = new Alerta(getStage().getTitle(), getQuarto().getErrors());

				alerta.Erro(getStage());
			}
		}
	}

	private void validaProduto(){
		setItem_servicoValido(true);	
		Produto produto = getProduto();
		
		if(produto.getCodigo() == 0){
			setItem_servicoValido(false);
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
    			setItem_servicoValido(false);
        		Alerta alerta = new Alerta(getStage().getTitle(), getProduto().getErrors());
        		
        		alerta.Erro(getStage());
    		}
    	}
	}
	
	private boolean validaServico(){
		int count = 2; // numero de Validações
		int countAux = 1;
		
		setServicoValido(true);
		while(isServicoValido() && countAux < count){
			switch(countAux){
				case 1:
					validaPessoa();
					break;
				case 2:
					validaQuarto();
					break;				
			}
			countAux++;
		}
		
		return isServicoValido();
	}
	
	private boolean validaItemServico(){
		int count = 3; // Número de validações
		int countAux = 1;
		
		setItem_servicoValido(true);
		while(isItem_servicoValido() && countAux < count){
			switch(countAux){
				case 1:
					validaProduto();
					break;
			}
			countAux++;
		}
		
		return isItem_servicoValido();
	}
}
