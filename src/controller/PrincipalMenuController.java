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
import view.FaturamentoView;
import view.PessoaView;
import view.PredioView;
import view.ProdutoView;
import view.QuartoView;
import view.ReservaView;
import view.ConsultaPredioView;
import view.ConsultaProdutoView;
import view.ConsultaQuartoView;


public class PrincipalMenuController implements Initializable{
	// Controllers filhos
	private PessoaController pessoaController;
	private ProdutoController produtoController;
	private ReservaController reservaController;
	private ConsultaPessoaController consultaPessoaController;
	private EstadoController estadoController;
	private QuartoController quartoController;
	private ConsultaQuartoController consultaQuartosController;
	private ConsultaPredioController consultaPredioController;
	private PredioController predioController;
	private ConsultaProdutoController consultaProdutoController;
	private FaturamentoController faturamentoController;
	
	private Scene scene;
	
	@FXML
	private MenuItem mntmPessoas;
	
	@FXML
	private MenuItem mntmProdutos;
	
	@FXML
	private MenuItem mntmReservas;
	
	@FXML
	private MenuItem mntmFaturamento;
	
	@FXML
	private MenuItem mntmConsultaPessoas;
	
	@FXML
	private MenuItem mntmSair;
	
	@FXML
	private MenuItem mntmEstados;
	
	@FXML
	private MenuItem mntmQuartos;
	
	@FXML
	private MenuItem mntmConsultaQuartos;
	
	@FXML
	private MenuItem mntmConsultaPredios;
	
	@FXML
	private MenuItem mntmConsultaProdutos;
	
	@FXML
	private MenuItem mntmPredios;
	
	public void setScene(Scene scene) { 
		this.scene = scene; 
	}

	public PrincipalMenuController(){
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Cadastros
		mntmPessoas.setOnAction(event -> {
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
		});
		
		mntmProdutos.setOnAction(event -> {
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
		
		mntmFaturamento.setOnAction(evt -> {
            	if(faturamentoController != null){
	            	if(faturamentoController.getStatusScene() == StatusScene.Aberto){
						Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
						alerta.Alertar( (Stage) scene.getWindow());
						return;
	            	}
            	}
            	
        		try {
        			FaturamentoView view = new FaturamentoView();
					view.iniciaTela(scene);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		});
		
		mntmEstados.setOnAction(event -> {
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
		});
		
		mntmQuartos.setOnAction(event -> {
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
		});
		
		mntmPredios.setOnAction(event -> {
			if(predioController != null){
		    	if(predioController.getStatusScene() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
		    	}
			}
			
			try {
				PredioView view = new PredioView();
				view.start(scene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		// Consultas
		
		mntmConsultaPessoas.setOnAction(event -> {
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
		});
		
		mntmConsultaQuartos.setOnAction(event -> {
			if(consultaQuartosController != null){
		    	if(consultaQuartosController.getStatusScene() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
		    	}
			}
			
			try {
				ConsultaQuartoView view = new ConsultaQuartoView();
				view.iniciaTela(scene, Modality.NONE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		mntmConsultaProdutos.setOnAction(event -> {
			if(consultaProdutoController != null){
		    	if(consultaProdutoController.getStatusScene() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
		    	}
			}
			
			try {
				ConsultaProdutoView view = new ConsultaProdutoView();
				view.iniciaTela(scene, Modality.NONE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		mntmConsultaPredios.setOnAction(event -> {
			if(consultaPredioController != null){
		    	if(consultaPredioController.getStatusScene() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
		    	}
			}
			
			try {
				ConsultaPredioView view = new ConsultaPredioView();
				view.iniciaTela(scene, Modality.NONE);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		mntmSair.setOnAction(event -> {
			Stage stage = (Stage) scene.getWindow();
			stage.fireEvent(new WindowEvent(
								stage,
								WindowEvent.WINDOW_CLOSE_REQUEST
			));
		
		});
		
	}
}
