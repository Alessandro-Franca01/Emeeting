package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import dao.Db;
import controle.MetodosEstaticos;
import modelo.Usuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class TelaDeLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfSenha;
	private JLabel lbMensagem;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	// Iniciando valores
	String nome = null; 
	String senha;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rt = null;
    Usuario usuario;
	Connection con = Db.getConnection();
	
	public static void main(String[] args) {
		
		//Scanner input = new Scanner(System.in);
		//Usuario user = new Usuario();
		//con = Db.getConnection();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeLogin frame = new TelaDeLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaDeLogin() {
		setTitle("Tela de Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginUsuario = new JLabel("LOGIN USUARIO");
		lblLoginUsuario.setBounds(274, 91, 92, 14);
		contentPane.add(lblLoginUsuario);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(156, 144, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(156, 181, 46, 14);
		contentPane.add(lblSenha);
		
		tfNome = new JTextField();
		tfNome.setBounds(212, 141, 210, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setColumns(10);
		tfSenha.setBounds(212, 178, 210, 20);
		contentPane.add(tfSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEntrar.setAction(action_1);
		btnEntrar.setBounds(212, 262, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(319, 262, 89, 23);
		contentPane.add(btnSair);
		
		lbMensagem = new JLabel("Digite seus dados corretamente");
		lbMensagem.setEnabled(false);
		lbMensagem.setBounds(212, 225, 210, 14);
		contentPane.add(lbMensagem);
	}
	// Sem utilidade 
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	// FUNCIONALIDADE LOGAR - RODANDO!!
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Entrar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			nome = tfNome.getText();
			senha = tfSenha.getText();
			usuario = MetodosEstaticos.logandoGui(con, st, rt, nome, senha);
			
			if(usuario == null) {
				lbMensagem.setText("Digite seus dados novamente!");
				tfNome.setText("");
				tfSenha.setText("");
			System.out.println("Nome: "+nome+", Senha: "+senha);
			}
		}
	}
}
