package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Faturamento;
import util.Alerta;

public class RelatorioCheckOutController extends ControllerDefault {
	
	@FXML
	private TextField txtCodigoFaturamento;
	@FXML
	private Button btnImprimir;
	@FXML
	private Button btnSair;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		btnImprimir.setOnAction(evt -> {
			Faturamento faturamento = new Faturamento();
			
			int codigo = Integer.parseInt(txtCodigoFaturamento.getText());
			
			faturamento.setCodigo(codigo);
			faturamento = faturamento.exists();
			
			if(faturamento != null){
				faturamento.imprimir();
			}else{
				Alerta alerta = new Alerta(null, "Faturamento não cadastrado");
				
				alerta.Alertar(null);
			}
		});
		
		btnSair.setOnAction(evt -> {
			getStage().close();
		});
	}
}
