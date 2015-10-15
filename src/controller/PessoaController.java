package controller;

import java.awt.EventQueue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import model.Pessoa;
import model.enums.TipoPessoa;
import view.PessoaView;
import model.enums.PessoaSexo;

public class PessoaController implements Initializable{
	private Pessoa model;
	private PessoaView view;

	@FXML
	private Button btnSalvar;
	@FXML
	private TextField txtNomeCliente;
	@FXML
	private ComboBox<PessoaSexo> cbSexo;
	@FXML
	private TextField txtCGC;
	
	public PessoaController() {
		this.model = new Pessoa();
		this.view = new PessoaView();
	}
	
	public PessoaController(Pessoa model, PessoaView view) {
		this.model = model;
		this.view = view;
	}

	public void setModel(Pessoa model) {
		this.model = model;
	}
	
	public void iniciaMenu() throws Exception{
		view.start();
	}
	
	public void iniciaPessoa(){
		//this.view.setVisible(true);
		
		//this.addActionListenerSave();
	}

	/*
	private void addActionListenerSave() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//model.setNome(view.getNome().getText());
				
				System.out.println(model.isValidPerson());
			}

		};

		//view.getBtnSalvar().addActionListener(actionListener);
	}*/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		assert txtNomeCliente != null : "fx:id=\"mntmPessoas\" was not injected: check your FXML file 'PrincipalMenu.fxml'.";
		assert cbSexo != null : "fx:id=\"mntmPessoas\" was not injected: check your FXML file 'PrincipalMenu.fxml'.";
		assert btnSalvar != null : "fx:id=\"mntmPessoas\" was not injected: check your FXML file 'PrincipalMenu.fxml'.";
		
		cbSexo.getItems().addAll(PessoaSexo.Feminino, 
								 PessoaSexo.Masculino
								);
		cbSexo.setValue(PessoaSexo.Masculino);
		
		btnSalvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	model.setNome(txtNomeCliente.getText());
            	model.setCNPJCPF(txtCGC.getText());
            	System.out.println(model.isValidPerson());            	            
            }
        });
	}
	
	public TextField getTxtNomeCliente(){
		return txtNomeCliente;
	}
	
	public ComboBox getCbSexo(){
		return cbSexo;
	}
	
	public TextField getTxtCGC(){
		return txtCGC;
	}
	
	public Button getBtnSalvar(){
		return btnSalvar;
	}
}
