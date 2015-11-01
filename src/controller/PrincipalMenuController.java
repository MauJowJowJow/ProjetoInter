package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Alerta;
import view.ConsultaPessoaView;
import view.EnderecoView;
import view.PessoaView;
import view.PrincipalMenu;
import view.ProdutoView;


public class PrincipalMenuController implements Initializable{
	// Controllers filhos
	private PessoaController pessoaController;
	private ProdutoController produtoController;
	private ConsultaPessoaController consultaPessoaController;
	private EstadoController estadoController;
	
	private Scene scene;
	
	@FXML
	private MenuItem mntmPessoas;
	
	@FXML
	private MenuItem mntmProdutos;
	
	@FXML
	private MenuItem mntmConsultaPessoas;
	
	@FXML
	private MenuItem mntmSair;
	
	public void setScene(Scene scene) { 
		this.scene = scene; 
	}

	public PrincipalMenuController(){
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Cadastros
		mntmPessoas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(pessoaController != null){
	            	if(pessoaController.getStatus() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					PessoaView view = new PessoaView();
					view.iniciaTela(scene);
					
					PessoaController pessoaController = view.getFxmlLoader().<PessoaController>getController();
					pessoaController.setView(view);					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		mntmProdutos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	if(produtoController != null){
	            	if(produtoController.getStatus() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					ProdutoView view = new ProdutoView();
					view.iniciaTela(scene);
					
					ProdutoController produtoController = view.getFxmlLoader().<ProdutoController>getController();
					produtoController.setView(view);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		// Consultas
		
		mntmConsultaPessoas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	if(consultaPessoaController != null){
	            	if(consultaPessoaController.getStatus() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					ConsultaPessoaView view = new ConsultaPessoaView();
					view.iniciaTela(scene);
					
					ConsultaPessoaController produtoController = view.getFxmlLoader().<ConsultaPessoaController>getController();
					consultaPessoaController.setView(view);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		mntmSair.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	Stage stage = (Stage) scene.getWindow();
            	stage.fireEvent(new WindowEvent(
            						stage,
            						WindowEvent.WINDOW_CLOSE_REQUEST
            	));
           	
            }
        });
		
	}
}
