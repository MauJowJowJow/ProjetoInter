package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.util.converter.NumberStringConverter;
import java.util.ResourceBundle;
import org.apache.commons.beanutils.BeanUtils;
import model.Predio;
import model.Quarto;
import model.dao.QuartoDAO;
import util.Alerta;
import view.QuartoView;
import view.ConsultaPredioView;
import view.ConsultaQuartoView;
import model.enums.StatusQuarto;


public class QuartoController extends ControllerDefault implements Initializable  {
	private Quarto quarto = new Quarto();
	private Predio predio = new Predio();
	
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnProcurarQuarto;
	@FXML
	private Button btnProcurar;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtPredio;
	@FXML
	private TextField txtNomePredio;
	@FXML
	private TextField txtAndar;
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtDormitorios;
	@FXML
	private TextField txtValorDiaria;
	@FXML
	private ComboBox<StatusQuarto> cbStatus;
	@FXML
	private TextArea txtDescricao;
	
	public void setQuarto(Quarto quarto){
		try{
			BeanUtils.copyProperties(this.quarto, quarto);
			setPredio(quarto.getPredio());
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Quarto getQuarto(){
		return quarto;
	}
	
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
	
	public QuartoController(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		super.initialize(arg0, arg1);
		
		Bindings.bindBidirectional(txtCodigo.textProperty(), getQuarto().getCodigoProperty(), new NumberStringConverter());
		txtNome.textProperty().bindBidirectional(getQuarto().getNomeProperty());
		txtDormitorios.textProperty().bindBidirectional(getQuarto().getDormitoriosProperty(),new NumberStringConverter());
		txtAndar.textProperty().bindBidirectional(getQuarto().getAndarQuartoProperty(),new NumberStringConverter());
		txtValorDiaria.textProperty().bindBidirectional(getQuarto().getValorQuartoProperty(),new NumberStringConverter());
		txtDescricao.textProperty().bindBidirectional(getQuarto().getDescricaoProperty());
		cbStatus.valueProperty().bindBidirectional(getQuarto().getStatusQuartoProperty());
		txtPredio.textProperty().bindBidirectional(getPredio().getCodigoPredioProperty(),new NumberStringConverter());
		txtNomePredio.textProperty().bindBidirectional(getPredio().getDescricaoProperty());
		
		cbStatus.getItems().addAll(StatusQuarto.values());
		cbStatus.setValue(StatusQuarto.Disponivel);
		
		imagensBotoes();
		
	    txtPredio.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if(newValue) return;
    		
			Predio predio = getPredio().exists();
    		
    		if(predio != null){
    			setPredio(predio);
    		}
    		else{
        		Alerta alerta = new Alerta(getStage().getTitle(), getPredio().getErrors());
        		
        		alerta.Erro(getStage());
    		}
	    });
	    
	    btnProcurarQuarto.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
            public void handle(ActionEvent event) {
				ConsultaQuartoView consultaQuartoView = new ConsultaQuartoView();
				
				try {
					consultaQuartoView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
					
					ConsultaQuartoController controller = consultaQuartoView.getFxmlLoader().<ConsultaQuartoController>getController();
					if(controller.getModel() != null){
						setQuarto((Quarto)controller.getModel());
					} 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
		
		btnProcurar.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
            public void handle(ActionEvent event) {
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
            }
        });
		
		
		btnSalvar.setOnAction(evt -> {
			Quarto model = getQuarto();
			
			if(model == null)
				model = new Quarto();
			QuartoView view = (QuartoView) getView();

			QuartoDAO dao = new QuartoDAO();
			
			if(model.getCodigo() == 0){
				quarto.setPredio(getPredio());
    			setQuarto(dao.insert(model));
    			
    			Alerta alerta = new Alerta("Inserção", "Quarto inserido com o código " + model.getCodigo() + "!");
        		alerta.Mensagem(view.getStage());
    		}else{
    			setQuarto(dao.update(model));
    			
    			Alerta alerta = new Alerta("Atualização", "Quarto Atualizado!");
        		alerta.Mensagem(view.getStage());
    		}
		});
	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		btnProcurarQuarto.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		btnProcurar.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
	}
}
