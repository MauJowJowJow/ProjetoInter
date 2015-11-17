package controller;

import javafx.beans.binding.Bindings;
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

import model.Pessoa;
import model.Predio;
import model.dao.PredioDAO;
import model.Quarto;
import model.Reserva;
import model.dao.QuartoDAO;
import util.Alerta;
import view.QuartoView;
import view.ConsultaPessoaView;
import view.ConsultaPredioView;
import model.enums.StatusQuarto;


public class QuartoController extends ControllerDefault implements Initializable  {
	private Predio predio;
	private Quarto quarto;
	
	@FXML
	private Button btnSalvar;
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
		}catch (IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
	}
	
	public Quarto getQuarto(){
		if(getModel() == null)
			setModel(new Quarto());
		
		return (Quarto) getModel();
	}
	
	public Predio getPredio(){
		return predio;
	}
	
	public void setPredio(Predio predio){
		this.predio = predio;
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
		//txtNomePredio.textProperty().bindBidirectional(getPredio().getDescricaoProperty());
		//txtPredio.textProperty().bindBidirectional(getQuarto().getCodigoPredioProperty(),new NumberStringConverter());
		
		cbStatus.getItems().addAll(StatusQuarto.values());
		cbStatus.setValue(StatusQuarto.Ativo);
		
		imagensBotoes();
		
		btnProcurar.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
            public void handle(ActionEvent event) {
				ConsultaPredioView consultaPredioView = new ConsultaPredioView();
				
				try {
					consultaPredioView.iniciaTela(getScene(), Modality.WINDOW_MODAL);
					
					ConsultaPredioController controller = consultaPredioView.getFxmlLoader().<ConsultaPredioController>getController();
					if(controller.getModel() != null){
						setPredio((Predio)controller.getModel());
						
						txtPredio.setText(Integer.toString(getPredio().getCodigoPredio()));
						//txtNomePredio.textProperty().bindBidirectional(getPredio().getDescricaoProperty());
						//txtPredio.textProperty().bindBidirectional(getPredio().getCodigoPredioProperty(),new NumberStringConverter());
						txtNomePredio.setText(getPredio().getDescricao());
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
            }
        });
		
		
		btnSalvar.setOnAction(evt -> {
			Quarto model = (Quarto) getModel();
			if(model == null)
				model = new Quarto();
			QuartoView view = (QuartoView) getView();
			
		/*	if(!txtNome.getText().isEmpty())
				model.setNome(txtNome.getText());
			
			model.setStatusQuarto(cbStatus.getValue());
			
			model.setDescricao(txtDescricao.getText());
			
			if(txtAndar.getText().isEmpty())
				txtAndar.setText("0");
			
			model.setAndarQuarto(Integer.parseInt(txtAndar.getText()));
			
			if(txtDormitorios.getText().isEmpty())
				txtDormitorios.setText("0");
			
			model.setDormitorios(Integer.parseInt(txtAndar.getText()));
			
			if(txtValorDiaria.getText().isEmpty())
				txtValorDiaria.setText("0");
			
			model.setValorQuarto(Double.parseDouble(txtValorDiaria.getText())); */
			
			QuartoDAO dao = new QuartoDAO();
			
			if(model.getCodigo() == 0){
    			dao.insert(model);
    			
    			Alerta alerta = new Alerta("Inserção", "Quarto inserido com o código " + model.getCodigo() + "!");
        		alerta.Mensagem(view.getStage());
        		
        		txtCodigo.setText(Integer.toString(model.getCodigo()));
    		}else{
    			dao.update(model);
    			
    			Alerta alerta = new Alerta("Atualização", "Quarto Atualizado!");
        		alerta.Mensagem(view.getStage());
    		}
		});
	}
	
	private void imagensBotoes(){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();	
		btnProcurar.setGraphic(new ImageView(classLoader.getResource("Search.png").toString()));
		}
}
