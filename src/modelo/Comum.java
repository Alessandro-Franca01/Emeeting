package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import controle.Validacao;

public class Comum extends Usuario{

	public Comum() {
		super();
	}

	public Comum(int idUser, String nome, String cpf, String tipo, String senha) {
		super(idUser, nome, cpf, tipo, senha);
		// TODO Auto-generated constructor stub
	}

	// ADICIONAR OS METODOS DO USUARIO COMUM!!!
	// Modificar esse metodo
	public void criarReunaio(Connection c, PreparedStatement p, ResultSet r) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		int idReuniao;
		String local, sala, data;  
		System.out.println("Digite os dados para cadastrar a reunião");
		System.out.print("Local: ");
		local = sc.next();
		System.out.print("Sala:");
		sala = sc.next();
		System.out.print("Data no formato (aaaa-mm-dd):");
		data = sc.next();
		try {
			// Verficação da Reuniao, retornando o ID da reuniao 
			idReuniao = Validacao.verificarReuniao(local, sala, data, c, r);
			if(idReuniao > 0) { 
				p = c.prepareStatement("INSERT INTO REUNIAO VALUES(?, ?, ?, ?)"); // AUTO INCREMENTE NAO PRECISA, PQ É FEITO PELO PROPRIO MySQL!!
				p.setString(1, null);
				p.setString(2, local);																
				p.setString(3, sala);
				p.setDate(4, new java.sql.Date(sdf.parse(data).getTime()));
				int ver = p.executeUpdate();
				System.out.println("Verificando linhas afetadas: "+ver);
				c.commit();
				System.out.println("Reuniao criada com sucesso!");
			}else {
				System.out.println("Esse Local e Horario já estao reservados");
			}
		}catch(SQLException | ParseException e) { 
			System.out.println(e.getMessage());
		}
 }
	// AJSUTAR ESSE METODO!
	public void cadastrarUsuario(Connection coneccao, PreparedStatement pst, ResultSet resultadoCadastro, String nome, String cpf,
									String senha, String email, String tipo) {
		int id = Validacao.verificarUsuario(cpf, coneccao, resultadoCadastro);
		try {
		if(id == 0) {
				pst = coneccao.prepareStatement("INSERT INTO USUARIO VALUES(?, ?, ?, ?, ?, ?)");
				pst.setString(1, null);
				pst.setString(2, nome);
				pst.setString(3, tipo);
				pst.setString(4, cpf);
				pst.setString(5, senha);
				pst.setString(6, email);
				int ver = pst.executeUpdate();
				System.out.println("Verificando linhas afetadas: "+ver);
				coneccao.commit();
				System.out.println("Usuário cadastrado com sucesso!");
				
			}else {
				System.out.println("Usuario já cadastrado!");
			} 
		}catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
	}
	
	
}
