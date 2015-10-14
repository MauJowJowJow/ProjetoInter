package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PessoaView extends JFrame {
	private JButton btnSalvar;
	private JLabel lblCodigo;
	private JLabel lblNome;

	private JPanel contentPane;
	private JTextField lbCodigo;
	private JTextField lbNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	}

	/**
	 * Create the frame.
	 */
	public PessoaView() {
		this.initComponents();
	}
	
	private void initComponents(){
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(20, 11, 46, 14);
		contentPane.add(lblCodigo);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(20, 36, 46, 14);
		contentPane.add(lblNome);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnSalvar.setBounds(77, 159, 89, 23);
		contentPane.add(btnSalvar);
		
		lbCodigo = new JTextField();
		lbCodigo.setBounds(63, 8, 86, 20);
		contentPane.add(lbCodigo);
		lbCodigo.setColumns(10);
		
		lbNome = new JTextField();
		lbNome.setBounds(63, 33, 86, 20);
		contentPane.add(lbNome);
		lbNome.setColumns(10);
	}

	public JTextField getNome(){
		return lbNome;
	}

	public JButton getBtnSalvar(){
		return btnSalvar;
	}
}
