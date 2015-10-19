package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import view.PrincipalMenu;

public class PrincipalMenuController implements Initializable{
	private PessoaController pessoaController;
	private PrincipalMenu view;
	private Scene scene;
	
	@FXML
	private MenuItem mntmPessoas;
	
	public void setScene(Scene scene) { 
		this.scene = scene; 
	}

	public PrincipalMenuController(){
		this.view = new PrincipalMenu();
	}
	
	public PrincipalMenuController(PrincipalMenu view){
		this.view = view;
	}
	
	public void iniciaMenu(String[] args){
		view.Main(args);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert mntmPessoas != null : "fx:id=\"mntmPessoas\" was not injected: check your FXML file 'PrincipalMenu.fxml'.";
		
		mntmPessoas.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	pessoaController = new PessoaController();
        		try {
					pessoaController.inicia(scene);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}


}
