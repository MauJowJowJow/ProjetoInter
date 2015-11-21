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
import javafx.util.converter.NumberStringConverter;
import model.Pessoa;
import model.Produto;
import model.dao.ProdutoDAO;
import util.Alerta;
import view.ProdutoView;
import model.enums.UniMedProduto;

public class ProdutoController extends ControllerDefault implements Initializable{	
	private Produto produto = new Produto();
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnNovo;
	
	@FXML
	private Button btnProcurar;
	
	@FXML
	private TextField txtProduto;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private TextField txtCodBarra;
	
	@FXML
	private ComboBox cbUniMedida;
	
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
		
		
		btnSalvar.setOnAction(evt -> {
			Produto model = getProduto();
			
			if(model == null)
				model = new Produto();
			ProdutoView view = (ProdutoView) getView();

			ProdutoDAO dao = new ProdutoDAO();
			
			if(model.getCodigo() == 0){
    			dao.insert(model);
    			
    			Alerta alerta = new Alerta("Inser��o", "Produto inserido com o c�digo " + model.getCodigo() + "!");
        		alerta.Mensagem(view.getStage());
        		
        		txtCodigo.setText(Integer.toString(model.getCodigo()));
    		}else{
    			dao.update(model);
    			
    			Alerta alerta = new Alerta("Atualiza��o", "Produto Atualizado!");
        		alerta.Mensagem(view.getStage());
    		}
		});
		
		btnNovo.setOnAction(evt -> {
			if(getProduto().getCodigo() == 0)
				setProduto(new Produto());
			else{
				Alerta alerta = new Alerta("Cadastro de Produtos", "Cadastro pode ter sofrido altera��es, deseja mesmo criar um novo produto?");
				if(alerta.Confirm(getStage()))
					setProduto(new Produto());				
			}
		});
		
		
	/*	btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Produto model = (Produto) getModel();
				ProdutoView view = (ProdutoView) getView();
				
				
				
				if (model.isValid()) {
					ProdutoDAO dao = new ProdutoDAO();

					if (model.getCodigo() == 0) {
						dao.insert(model);

						Alerta alerta = new Alerta("Inser��o",
								"Produto inserido com o c�digo " + model.getCodigo() + "!");
						alerta.Mensagem(view.getStage());

						txtCodigo.setText(Integer.toString(model.getCodigo()));
					} else {
						dao.update(model);

						Alerta alerta = new Alerta("Atualiza��o", "Produto Atualizado!");
						alerta.Mensagem(view.getStage());
					}

					dao.closeEntity();
				} else {
					Alerta alerta = new Alerta("Valida��o ", model.getErrors());

					alerta.Erro(view.getStage());
				}

			}
		});*/
	}

}
