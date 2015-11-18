package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Predio;
import model.dao.PredioDAO;
import util.Alerta;
import view.PredioView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PredioController extends ControllerDefault implements Initializable {

	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodigo;
	@FXML
	private TextField txtDescricao;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		btnSalvar.setOnAction(evt -> {
				Predio model = (Predio) getModel();
				if(model == null)
					model = new Predio();
				PredioView view = (PredioView) getView();
				
				if(!txtDescricao.getText().isEmpty())
					model.setDescricao(txtDescricao.getText());
				
				PredioDAO dao = new PredioDAO();
				
				if(model.getCodigoPredio() == 0){
	    			dao.insert(model);
	    			
	    			Alerta alerta = new Alerta("Inserção", "Predio inserido com o código " + model.getCodigoPredio() + "!");
	        		alerta.Mensagem(view.getStage());
	        		
	        		txtCodigo.setText(Integer.toString(model.getCodigoPredio()));
	    		}else{
	    			dao.update(model);
	    			
	    			Alerta alerta = new Alerta("Atualização", "Predio Atualizado!");
	        		alerta.Mensagem(view.getStage());
	    		}
		});	
	}
}
