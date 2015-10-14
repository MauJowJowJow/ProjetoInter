package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Pessoa;
import model.enums.TipoPessoa;
import view.PessoaView;

public class PessoaController {
	private Pessoa model;
	private PessoaView view;
	private ActionListener actionListener;	

	PessoaController() {
		this.model = new Pessoa();
		this.view = new PessoaView();
	}
	
	PessoaController(Pessoa model, PessoaView view) {
		this.model = model;
		this.view = view;
	}

	public void setModel(Pessoa model) {
		this.model = model;
	}
	
	public void iniciaPessoa(){
		this.view.setVisible(true);
		
		this.addActionListenerSave();
	}

	private void addActionListenerSave() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setNome(view.getNome().getText());
				
				System.out.println(model.isValidPerson());
			}

		};

		view.getBtnSalvar().addActionListener(actionListener);
	}

}
