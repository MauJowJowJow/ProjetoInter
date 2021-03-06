package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;
import model.Produto;
import model.dao.Estoque_produtoDAO;
import model.dao.ProdutoDAO;
import util.Alerta;
import view.ConsultaProdutoView;
import view.Estoque_produtoView;
import view.ProdutoView;
import model.enums.UniMedProduto;
import model.Estoque_produto;

public class ProdutoController extends ControllerDefault implements Initializable{	
	private Produto produto = new Produto();
	private Estoque_produto estoque = new Estoque_produto();
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnNovo;
	
	@FXML
	private Button btnEstoque;
	
	@FXML
	private Button btnProcurar;
	
	@FXML
	private TextField txtProduto;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private TextField txtCodBarra;
	
	@FXML
	private ComboBox<UniMedProduto> cbUniMedida;
	
	public ProdutoController() {}
	
	public void setProduto(Produto produto){
		try{
			BeanUtils.copyProperties(this.produto, produto);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	public Produto getProduto(){
		return produto;
	}
	
	public void setEstoque(Estoque_produto estoque){
		try{
			BeanUtils.copyProperties(this.estoque, estoque);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Estoque_produto getEstoque(){
		return estoque;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
			
		Bindings.bindBidirectional(txtCodigo.textProperty(), getProduto().getCodigoProperty(), new NumberStringConverter());
		txtProduto.textProperty().bindBidirectional(getProduto().getDescProdutoProperty());
		cbUniMedida.valueProperty().bindBidirectional(getProduto().getUniProdutoProperty());
		txtValor.textProperty().bindBidirectional(getProduto().getValorProdutoProperty(),new NumberStringConverter());
		txtCodBarra.textProperty().bindBidirectional(getProduto().getCodBarraProperty(),new NumberStringConverter());
		
		cbUniMedida.getItems().addAll(UniMedProduto.values());
		cbUniMedida.setValue(UniMedProduto.UN);
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();	
		btnProcurar.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		
		btnProcurar.setOnAction(event -> {
			ConsultaProdutoView consultaProdutoView = new ConsultaProdutoView();
			
			try {
				consultaProdutoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
				
				ConsultaProdutoController controller = consultaProdutoView.getFxmlLoader().<ConsultaProdutoController>getController();
				if(controller.getModel() != null){
					setProduto((Produto)controller.getModel());
				} 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		btnSalvar.setOnAction(evt -> {
			Produto model = getProduto();
			ProdutoView view = (ProdutoView) getView();
			ProdutoDAO dao = new ProdutoDAO();
			
			if(model.getCodigo() == 0){
				Estoque_produto estoque_produto = new Estoque_produto();
				estoque_produto.setProduto(model);
				
				model.setEstoque_produto(estoque_produto);
    			setProduto(dao.insert(model));
    			
    			Alerta alerta = new Alerta("Inser��o", "Produto inserido com o c�digo " + model.getCodigo() + "!");
        		alerta.Mensagem(view.getStage());
    		}else{
    			setProduto(dao.update(model));
    			
    			Alerta alerta = new Alerta("Atualiza��o", "Produto Atualizado!");
        		alerta.Mensagem(view.getStage());
    		}
		});
		
		btnNovo.setOnAction(evt -> {
			if(getProduto().getCodigo() == 0)
				setProduto(new Produto());
			else{
				Alerta alerta = new Alerta(getStage().getTitle(), "Cadastro pode ter sofrido altera��es, deseja mesmo criar um novo produto?");
				if(alerta.Confirm(getStage()))
					setProduto(new Produto());				
			}
		});
		
		btnEstoque.setOnAction(evt -> {
			Produto produto = getProduto();
			
			if(produto.getCodigo() != 0){
				Estoque_produtoView estoqueView = new Estoque_produtoView();

				Estoque_produtoDAO dao = new Estoque_produtoDAO();
				Estoque_produto estoque = dao.getById(produto.getCodigo());
				
				if(estoque == null){
					estoque = new Estoque_produto();
					estoque.setProduto(produto);
				}
				
				try {
					estoqueView.iniciaTela(getScene(), estoque);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else{
				Alerta alerta = new Alerta(getStage().getTitle(), "Um produto j� cadastrado deve ser selecionado para altera��o de seu estoque!");
        		
        		alerta.Erro(getView().getStage());
			}
		});
	}

}
