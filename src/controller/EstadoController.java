package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Estado;
import model.dao.EstadoDAO;
import util.Alerta;
import view.EstadoView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EstadoController extends ControllerDefault implements Initializable {
	
	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtCodigoEst;
	@FXML
	private TextField txtNomeEstado;
	@FXML
	private TextField txtUF;
	@FXML
	private TextField txtPais;
	
	public EstadoController(){}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		btnSalvar.setOnAction(evt -> {
				Estado model = (Estado) getModel();
				if(model == null)
					model = new Estado();
				EstadoView view = (EstadoView) getView();
				
				if(!txtNomeEstado.getText().isEmpty())
					model.setNomeEstado(txtNomeEstado.getText());
				
				
				model.setSiglaEstado(txtUF.getText());
				model.setNomePais(txtPais.getText());
				
				EstadoDAO dao = new EstadoDAO();
				
				if(model.getCodigoEstado() == 0){
	    			dao.insert(model);
	    			
	    			Alerta alerta = new Alerta("Inserção", "Estado inserido com o código " + model.getCodigoEstado() + "!");
	        		alerta.Mensagem(view.getStage());
	        		
	        		txtCodigoEst.setText(Integer.toString(model.getCodigoEstado()));
	    		}else{
	    			dao.update(model);
	    			
	    			Alerta alerta = new Alerta("Atualização", "Estado Atualizado!");
	        		alerta.Mensagem(view.getStage());
	    		}
		});	
	}
}
