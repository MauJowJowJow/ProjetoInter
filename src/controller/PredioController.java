package controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.beanutils.BeanUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Predio;
import model.dao.PredioDAO;
import util.Alerta;
import view.ConsultaPredioView;
import view.PredioView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;

public class PredioController extends ControllerDefault implements Initializable {
	private Predio predio = new Predio();

	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnPesquisaPredio;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtDescricao;
	
	public void setPredio(Predio predio){
		try{
			BeanUtils.copyProperties(this.predio, predio);
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Predio getPredio(){
		return predio;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		imagensBotoes();
		
		txtCodigo.textProperty().bindBidirectional(predio.getCodigoPredioProperty(), new NumberStringConverter());
		txtDescricao.textProperty().bindBidirectional(predio.getDescricaoProperty());
		
		btnPesquisaPredio.setOnAction(event -> {
			ConsultaPredioView consultaPredioView = new ConsultaPredioView();
			
			try {
				consultaPredioView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
				
				ConsultaPredioController controller = consultaPredioView.getFxmlLoader().<ConsultaPredioController>getController();
				if(controller.getModel() != null){
					setPredio((Predio)controller.getModel());
				} 
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		btnSalvar.setOnAction(evt -> {
				Predio model = getPredio();
				PredioView view = (PredioView) getView();
				PredioDAO dao = new PredioDAO();
				
				if(model.getCodigoPredio() == 0){
	    			setPredio(dao.insert(model));
	    			
	    			Alerta alerta = new Alerta("Inserção", "Prédio inserido com o código " + model.getCodigoPredio() + "!");
	        		alerta.Mensagem(view.getStage());
	    		}else{
	    			setPredio(dao.update(model));
	    			
	    			Alerta alerta = new Alerta("Atualização", "Predio Atualizado!");
	        		alerta.Mensagem(view.getStage());
	    		}
		});	
	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();	
		btnPesquisaPredio.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	}
}
