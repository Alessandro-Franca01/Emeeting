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

	public Comum(int idUser, String nome, String cpf, String tipo, String senha, String email) {
		super(idUser, nome, cpf, tipo, senha, email);
	}
	
	// Arrumar o grupo radio button, e fazer testes nesse metodo!!!
	public void criarReunaio(int idSala, String data, String periodo, String acesso, Connection conect, PreparedStatement pst, ResultSet rt) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Verficação da Reuniao, retornando o ID da reuniao 
			int idReuniao = Validacao.verificarReuniao(idSala, data, periodo, conect, rt);
			if(idReuniao == 0) { 
				pst = conect.prepareStatement("INSERT INTO REUNIAO(IDREUNIAO, ID_SALA, ID_USUARIO, DATA_REUNIAO, CONFIRMAR_SALA, ACESSO, PERIODO)"
									+ " VALUES( ?, ?, ?, ?, ?, ?, ?)"); // AUTO INCREMENTE NAO PRECISA, PQ É FEITO PELO PROPRIO MySQL!!
				pst.setString(1, null);
				pst.setInt(2, idSala);																
				pst.setInt(3, this.getIdUser());
				//pst.setDate(4, new java.sql.Date(sdf.parse(data).getTime()));
				pst.setString(4, data);
				pst.setString(5, "AGUARDANDO");
				pst.setString(6, acesso);
				pst.setString(7, periodo);
				
				int ver = pst.executeUpdate();
				System.out.println("Verificando linhas afetadas: "+ver);
				conect.commit();
				System.out.println("Reuniao criada com sucesso!");
			}else {
				System.out.println("Esse Local e Horario já estao reservados");
			}
		}catch(SQLException e) { 
			System.out.println(e.getMessage());
		}
 }
	
	// FUNCIONANDO!!
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
	
	// Trabalhar nesse metodo de pesquisar de Usuario!
	public Usuario pesquisarUsuario(String cpf, Connection coneccao, ResultSet rt ) {
		Usuario user = null;
		int id = Validacao.verificarUsuario(cpf, coneccao, rt);
		if(id != 0)
			try {
				PreparedStatement pst = coneccao.prepareStatement("SELECT * FROM USUARIO "
				          + "WHERE IDUSUARIO = ? ");
				pst.setInt(1, id);
				rt = pst.executeQuery();
				if(rt.next()) {
					user = new Usuario(rt.getInt("IDUSUARIO"), rt.getString("NOME"), rt.getString("CPF"), 
							rt.getString("TIPO_USUARIO"), rt.getString("SENHA"),  rt.getString("EMAIL"));
				}else {
					System.out.println("Esse Id não existe!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return user;
				
	}
	
}
