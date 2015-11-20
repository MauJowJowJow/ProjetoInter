package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
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
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Produto model = (Produto) getModel();
				ProdutoView view = (ProdutoView) getView();
				
				
				
				//if (model.isValid()) {
					ProdutoDAO dao = new ProdutoDAO();

					if (model.getCodigo() == 0) {
						dao.insert(model);

						Alerta alerta = new Alerta("Inserção",
								"Produto inserido com o código " + model.getCodigo() + "!");
						alerta.Mensagem(view.getStage());

						txtCodigo.setText(Integer.toString(model.getCodigo()));
					} else {
						dao.update(model);

						Alerta alerta = new Alerta("Atualização", "Produto Atualizado!");
						alerta.Mensagem(view.getStage());
					}

					dao.closeEntity();
				}// else {
				//	Alerta alerta = new Alerta("Validação ", model.getErrors());

					//alerta.Erro(view.getStage());
				//}

			//}
		});
	}

}
