package controller;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

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
import model.Item_faturado;
import model.Item_reserva;
import model.Pessoa;
import model.Produto;
import model.Quarto;
import model.dao.ProdutoDAO;
import util.Alerta;
import view.ConsultaPessoaView;

public class FaturamentoController extends ControllerDefault{
	private Pessoa pessoa;
	private Quarto quarto;
	private Produto produto;
	
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
    private TableColumn<Item_faturado, Integer> tbcCodProduto;
    @FXML
    private TableColumn<Item_faturado, String> tbcNomeProduto;
    @FXML
    private TableColumn<Item_faturado, Double> tbcQuantidade;
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
	
	public void setProduto(Produto produto){
		this.produto = produto;
	}

	public Produto getProduto(){
		return produto;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
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
		
	}
	
	private void eventosCampos(){
	    btnAddLinha.setOnAction(evt -> {
    		Item_faturado item_faturado = new Item_faturado();

    		if(txtCodigoProduto.getText().isEmpty())
    			txtCodigoProduto.setText("0");    		
    		
    		//if(!validaItemReserva())
    		//	return;
    		ProdutoDAO dao = new ProdutoDAO();
    		setProduto(dao.getById(Integer.parseInt(txtCodigoProduto.getText())));
    		
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
	}
	
    private void iniciaTableView(){
    	tbcCodProduto.setCellValueFactory(
			    new PropertyValueFactory<Item_faturado, Integer>("Item_faturadoPK.getCodigoPro()")
		);
    }
    
    private boolean produtoJaExiste(Item_faturado item_faturado){
    	for(Item_faturado ttem_faturado2 : tbvItem_faturado.getItems()){
			if(ttem_faturado2.getPK().getProduto().getCodigo() == item_faturado.getPK().getProduto().getCodigo())
				return true;
		}
		return false;
    }
}
