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
import view.PrincipalMenu;


public class PrincipalMenuController implements Initializable{
	// Controllers filhos
	private PessoaController pessoaController;
	private ProdutoController produtoController;
	private ConsultaPessoaController consultaPessoaController;
	private EstadoController estadoController;

	private PrincipalMenu view;
	private ArrayList<Object> telasAbertas;
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
		this.view = new PrincipalMenu();
		
		telasAbertas = new ArrayList<Object>();
	}
	
	public PrincipalMenuController(PrincipalMenu view){
		this.view = view;
		
		telasAbertas = new ArrayList<Object>();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Cadastros
		mntmPessoas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(pessoaController == null)
            		pessoaController = new PessoaController();
            	
            	if(pessoaController.getStatus() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
            	}
            	
        		try {
					pessoaController.inicia(scene);
					
					telasAbertas.add(pessoaController);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		mntmProdutos.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	if(produtoController == null)
            		produtoController = new ProdutoController();
            	
            	if(produtoController.getStatus() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
            	}
            	
        		try {
					produtoController.inicia(scene);
					
					telasAbertas.add(produtoController);
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
            	if(consultaPessoaController == null)
            		consultaPessoaController = new ConsultaPessoaController();
            	
            	if(consultaPessoaController.getStatus() == StatusScene.Aberto){
					Alerta alerta = new Alerta("Menu Principal", "Tela já aberta!");
					alerta.Alertar( (Stage) scene.getWindow());
					return;
            	}
            	
        		try {
        			consultaPessoaController.inicia(scene);					
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
