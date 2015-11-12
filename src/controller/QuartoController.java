package controller;

import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import model.Quarto;
import model.dao.QuartoDAO;
import util.Alerta;
import view.QuartoView;
import model.enums.StatusQuarto;

public class QuartoController extends ControllerDefault implements Initializable  {
	
	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtPredio;
	@FXML
	private Button btnProcurar;
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
	
	public QuartoController(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		cbStatus.getItems().addAll(StatusQuarto.values());
		cbStatus.setValue(StatusQuarto.Ativo);
		
		btnSalvar.setOnAction(evt -> {
			Quarto model = (Quarto) getModel();
			if(model == null)
				model = new Quarto();
			QuartoView view = (QuartoView) getView();
			
			if(!txtNome.getText().isEmpty())
				model.setNome(txtNome.getText());
			
			model.setStatusQuarto(cbStatus.getValue());
			
			QuartoDAO dao = new QuartoDAO();
			
			if(model.getCodigo() == 0){
    			dao.insert(model);
    			
    			Alerta alerta = new Alerta("Inser��o", "Quarto inserido com o c�digo " + model.getCodigo() + "!");
        		alerta.Mensagem(view.getStage());
        		
        		txtCodigo.setText(Integer.toString(model.getCodigo()));
    		}else{
    			dao.update(model);
    			
    			Alerta alerta = new Alerta("Atualiza��o", "Quarto Atualizado!");
        		alerta.Mensagem(view.getStage());
    		}
		});
	}
}
