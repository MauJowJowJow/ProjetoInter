package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import util.Alerta;
import view.ConsultaPessoaView;
import view.EstadoView;
import view.PessoaView;
import view.ProdutoView;
import view.QuartoView;
import view.ReservaView;


public class PrincipalMenuController implements Initializable{
	// Controllers filhos
	private PessoaController pessoaController;
	private ProdutoController produtoController;
	private ReservaController reservaController;
	private ConsultaPessoaController consultaPessoaController;
	private EstadoController estadoController;
	private QuartoController quartoController;
	
	private Scene scene;
	
	@FXML
	private MenuItem mntmPessoas;
	
	@FXML
	private MenuItem mntmProdutos;
	
	@FXML
	private MenuItem mntmReservas;
	
	@FXML
	private MenuItem mntmConsultaPessoas;
	
	@FXML
	private MenuItem mntmSair;
	
	@FXML
	private MenuItem mntmEstados;
	
	@FXML
	private MenuItem mntmQuartos;
	
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
	            	if(pessoaController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					PessoaView view = new PessoaView();
					view.iniciaTela(scene);					
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
	            	if(produtoController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					ProdutoView view = new ProdutoView();
					view.iniciaTela(scene);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		mntmReservas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	if(reservaController != null){
	            	if(reservaController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					ReservaView view = new ReservaView();
					view.iniciaTela(scene);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		mntmEstados.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
            public void handle(ActionEvent event) {
            	if(estadoController != null){
	            	if(estadoController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					EstadoView view = new EstadoView();
					view.start(scene);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			
		});
		
		mntmQuartos.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
            public void handle(ActionEvent event) {
            	if(quartoController != null){
	            	if(quartoController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					QuartoView view = new QuartoView();
					view.start(scene);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
			
		});
		
		// Consultas
		
		mntmConsultaPessoas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
            public void handle(ActionEvent event) {
            	if(consultaPessoaController != null){
	            	if(consultaPessoaController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
					ConsultaPessoaView view = new ConsultaPessoaView();
					view.iniciaTela(scene, Modality.NONE);
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
