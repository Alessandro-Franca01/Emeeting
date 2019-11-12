package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import com.sun.javafx.font.freetype.FTFactory;
import modelo.Usuario;
import modelo.Comum;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Action;

public class TelaUsuarioComum extends JFrame {

	private JPanel contentPane;
	private JTextField tfCadastroNome;
	private JLabel lblCadastroConfirmacao;
	private JPasswordField pfCadastroSenha;
	private JPasswordField pfCadastroConfirmarSennha;
	private JFormattedTextField ftCadastroCpf;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private final Action fazerCadastro = new SwingAction();

	// Declarando objetos uteis
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rt = null;
	Comum usuario ;
	Connection conect = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarioComum frameComum = new TelaUsuarioComum();
					frameComum.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void receberUsuario(Comum userComum) {
		System.out.println("Recebendo o objeto usuario!");
		usuario = userComum;	
	}
	
	public void receberConeccao(Connection con) {
		conect = con;
	}
	public TelaUsuarioComum() {
		setTitle("Usu\u00E1rio Comum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		tabbedPane.setBounds(10, 11, 665, 387);
		contentPane.add(tabbedPane);
		
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Cadastro e Participação", null, panelCadastro, null);
		panelCadastro.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fazer Cadastro do Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(68, 11, 200, 23);
		panelCadastro.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(22, 48, 45, 14);
		panelCadastro.add(lblNome);
		
		JLabel lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(22, 73, 45, 14);
		panelCadastro.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(22, 98, 45, 14);
		panelCadastro.add(lblSenha);
		
		JLabel lblConfirmaSenha = new JLabel("Confirma Senha");
		lblConfirmaSenha.setBounds(22, 123, 95, 14);
		panelCadastro.add(lblConfirmaSenha);
		
		tfCadastroNome = new JTextField();
		tfCadastroNome.setBounds(90, 45, 189, 20);
		panelCadastro.add(tfCadastroNome);
		tfCadastroNome.setColumns(10);
		
		pfCadastroSenha = new JPasswordField();
		pfCadastroSenha.setBounds(90, 95, 189, 20);
		panelCadastro.add(pfCadastroSenha);
		
		pfCadastroConfirmarSennha = new JPasswordField();
		pfCadastroConfirmarSennha.setBounds(127, 120, 152, 20);
		panelCadastro.add(pfCadastroConfirmarSennha);
		
		ftCadastroCpf = new JFormattedTextField();
		ftCadastroCpf.setBounds(90, 70, 189, 20);
		panelCadastro.add(ftCadastroCpf);
		
		JButton btCadastrar = new JButton("Finalizar");
		btCadastrar.setAction(fazerCadastro);
		btCadastrar.setBounds(43, 199, 89, 23);
		panelCadastro.add(btCadastrar);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(179, 199, 89, 23);
		panelCadastro.add(btnNewButton_1);
		
		JLabel lblAdicionarParticipantes = new JLabel("Adicionar Participantes");
		lblAdicionarParticipantes.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblAdicionarParticipantes.setBounds(391, 11, 200, 23);
		panelCadastro.add(lblAdicionarParticipantes);
		
		JLabel label = new JLabel("Cpf");
		label.setBounds(370, 48, 45, 14);
		panelCadastro.add(label);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setBounds(421, 45, 169, 20);
		panelCadastro.add(formattedTextField_1);
		
		JLabel lblLocal = new JLabel("Local");
		lblLocal.setBounds(370, 73, 35, 14);
		panelCadastro.add(lblLocal);
		
		textField_1 = new JTextField();
		textField_1.setBounds(422, 70, 169, 20);
		panelCadastro.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setBounds(370, 98, 35, 14);
		panelCadastro.add(lblSala);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(422, 95, 169, 20);
		panelCadastro.add(textField_2);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(369, 123, 35, 14);
		panelCadastro.add(lblData);
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setBounds(422, 120, 169, 20);
		panelCadastro.add(formattedTextField_2);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(391, 187, 89, 23);
		panelCadastro.add(btnAdicionar);
		
		JButton button = new JButton("Cancelar");
		button.setBounds(502, 187, 89, 23);
		panelCadastro.add(button);
		
		JLabel lblConfirmarPresena = new JLabel("Confirmar Presen\u00E7a ");
		lblConfirmarPresena.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblConfirmarPresena.setBounds(249, 232, 145, 23);
		panelCadastro.add(lblConfirmarPresena);
		
		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(370, 148, 45, 14);
		panelCadastro.add(lblHorario);
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setBounds(423, 151, 168, 20);
		panelCadastro.add(formattedTextField_3);
		
		lblCadastroConfirmacao = new JLabel("            Digite os dados corretamente!");
		lblCadastroConfirmacao.setBounds(32, 154, 257, 14);
		panelCadastro.add(lblCadastroConfirmacao);
		
		JPanel panelReuniao = new JPanel();
		tabbedPane.addTab("Reuniao e Local", null, panelReuniao, null);
		panelReuniao.setLayout(null);
		
		JLabel lblFazerCadastroDe = new JLabel("Fazer Cadastro de Reuni\u00E3o");
		lblFazerCadastroDe.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblFazerCadastroDe.setBounds(34, 21, 200, 23);
		panelReuniao.add(lblFazerCadastroDe);
		
		JLabel lblNewLabel_1 = new JLabel("Id Local");
		lblNewLabel_1.setBounds(24, 66, 46, 14);
		panelReuniao.add(lblNewLabel_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(111, 63, 86, 20);
		panelReuniao.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSala_1 = new JLabel("Sala");
		lblSala_1.setBounds(24, 97, 39, 14);
		panelReuniao.add(lblSala_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(111, 94, 86, 20);
		panelReuniao.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setBounds(24, 122, 59, 23);
		panelReuniao.add(lblDescrio);
		
		textField_5 = new JTextField();
		textField_5.setBounds(111, 123, 86, 20);
		panelReuniao.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblData_1 = new JLabel("Data");
		lblData_1.setBounds(24, 156, 33, 14);
		panelReuniao.add(lblData_1);
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setBounds(111, 153, 86, 20);
		panelReuniao.add(formattedTextField_4);
		
		JLabel lblHorrio = new JLabel("Hor\u00E1rio");
		lblHorrio.setBounds(24, 191, 46, 14);
		panelReuniao.add(lblHorrio);
		
		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setBounds(111, 188, 86, 20);
		panelReuniao.add(formattedTextField_5);
		
		JLabel lblSugestoDeLocal = new JLabel("Pesquisar Local de Reuni\u00E3o");
		lblSugestoDeLocal.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblSugestoDeLocal.setBounds(345, 21, 200, 23);
		panelReuniao.add(lblSugestoDeLocal);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(320, 66, 46, 14);
		panelReuniao.add(lblNome_1);
		
		textField_6 = new JTextField();
		textField_6.setBounds(399, 63, 146, 20);
		panelReuniao.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Cidade");
		lblNewLabel_2.setBounds(320, 100, 46, 14);
		panelReuniao.add(lblNewLabel_2);
		
		textField_7 = new JTextField();
		textField_7.setBounds(399, 94, 146, 20);
		panelReuniao.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblEsado = new JLabel("Estado");
		lblEsado.setBounds(320, 131, 46, 14);
		panelReuniao.add(lblEsado);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"PB", "PE", "AL"}));
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(399, 124, 90, 21);
		panelReuniao.add(comboBox);
		
		JTextPane txtpnNomeEstadoCidade = new JTextPane();
		txtpnNomeEstadoCidade.setText("Nome:\r\nID local:\r\nEstado:\r\nCidade:\r\nRua/Avenida:\r\nBairro:\r\nObs:");
		txtpnNomeEstadoCidade.setBounds(320, 196, 278, 103);
		panelReuniao.add(txtpnNomeEstadoCidade);
		
		JLabel lblDadosDoLocal = new JLabel("Dados do Local Pesquisado");
		lblDadosDoLocal.setBounds(382, 171, 172, 14);
		panelReuniao.add(lblDadosDoLocal);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10, 244, 89, 23);
		panelReuniao.add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(124, 244, 89, 23);
		panelReuniao.add(btnCancelar);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(512, 124, 99, 23);
		panelReuniao.add(btnPesquisar);
		
		JPanel panelVisualizacao = new JPanel();
		tabbedPane.addTab("Visualizações", null, panelVisualizacao, null);
		
		JPanel panelAtas = new JPanel();
		tabbedPane.addTab("Atas", null, panelAtas, null);
		
		JPanel panelPlanoDeAcao = new JPanel();
		tabbedPane.addTab("Plano de Ação", null, panelPlanoDeAcao, null);
	}//Fim do metodo construtor!! 
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Finalizar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		// FUNCIONALIDADE CADASTRO, PEGANDO!! FAZERR MAIS TESTES!!!
		public void actionPerformed(ActionEvent e) {
			String nome, cpf, senha, confirmarSenha;
			nome = tfCadastroNome.getText();
			cpf = ftCadastroCpf.getText();
			senha = pfCadastroSenha.getText();
			confirmarSenha = pfCadastroConfirmarSennha.getText();
			System.out.println("Senha: "+senha+", Confirmar senha: "+confirmarSenha);
			//teste = 
			if(senha.equals(confirmarSenha)) {
				//Entrar com a verificação de usuario
				usuario.toString();
				//usuario.cadastrarUsuario(conect, ps, rt, nome, cpf, senha);
				lblCadastroConfirmacao.setText("Cadastro feito com sucesso!");
			}else {
				// Messagem dizendo que as senhas não sao iguais!
				System.out.println("As senhas não estao corretas");
			}
		}
	}


}
