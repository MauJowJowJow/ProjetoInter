package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import model.Estoque_produto;
import model.dao.Estoque_produtoDAO;
import view.Estoque_produtoView;
import model.Produto;


public class Estoque_produtoController extends ControllerDefault implements Initializable {
	private Estoque_produto estoque = new Estoque_produto();
	
	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtQuantidade;
		
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
	
	public Estoque_produtoController(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		super.initialize(arg0, arg1);
	

		Bindings.bindBidirectional(txtCodigo.textProperty(), getEstoque().getCodigoProperty(), new NumberStringConverter());
		txtQuantidade.textProperty().bindBidirectional(getEstoque().getQuatidadeProperty(), new NumberStringConverter());

		
		btnSalvar.setOnAction(evt -> {
			Estoque_produto model = getEstoque();
			
			Estoque_produtoDAO dao = new Estoque_produtoDAO();			
			setEstoque(dao.update(model));
			
			getStage().close();
		});
	}
}
