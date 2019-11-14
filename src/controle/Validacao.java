package controle;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;

//import java.sql.Statement;
import java.sql.PreparedStatement;

public class Validacao {
	//  ESSA CLASSE VAI CONTER OS METODOS USADOS EM VALIDAÇÕES OU VERIFICAÇÕES
	
	public static int verificarReuniao(String l, String s, String data, Connection conec,ResultSet re) { 
	    int id = 0;
		try {
			PreparedStatement pst = conec.prepareStatement("SELECT IDREUNIAO FROM REUNIAO "
					                  + "WHERE LOCAL = ? AND SALA = ? AND DATA = ?");
			pst.setString(1, l);
			pst.setString(2, s);
			pst.setString(3, data);
			re = pst.executeQuery();
			//resultado = re.next();
			//System.out.println(re.next());
			if (re.next()) { 
				id = re.getInt("IDREUNIAO");
				System.out.println("Essa reuniao ja existe e seu Id é = "+id);
			}else {
				System.out.println("Essa reuniao nao existe!");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		//System.out.println("O resultado é "+resultado);
		return id;
	}
	
	public static int verificarUsuario(String cpf, Connection con, ResultSet pesquisa) {
		int idUsuario = 0;
		try {
			PreparedStatement pst = con.prepareStatement("SELECT IDUSUARIO FROM USUARIO "
	                  + "WHERE CPF = ? ");
			pst.setString(1, cpf);
			pesquisa = pst.executeQuery();
			if (pesquisa.next()) { 
				idUsuario = pesquisa.getInt("IDUSUARIO");
				System.out.println("O id do usuario é: "+idUsuario);
			}else {
				System.out.println("Usuário não cadastrado!");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return idUsuario;
	}
	
	public static int verificarSala( Connection con, ResultSet pesquisa, String nomeLocal, 
									String nomeSala, String cidade, String estado) {
		int idSala = 0;
		try {// Erro na query, corrigir ainda hoje!!
			PreparedStatement pst = con.prepareStatement("SELECT NOME_LOCAL, CIDADE, NOME_SALA, IDSALA "
														+"FROM LOCAL "
														+"INNER JOIN SALA "
														+"ON IDLOCAL = ID_LOCAL "
														+"WHERE NOME_LOCAL = ? "
														+"AND WHERE NOME_SALA = ? "
														+"WHERE CIDADE = ? ");
			pst.setString(1, nomeLocal);
			pst.setString(2, nomeSala);
			pst.setString(3, cidade);
			if (pesquisa.next()) { 
				idSala = pesquisa.getInt("IDSALA");
				System.out.println("O id do usuario é: "+idSala);
			}else {
				System.out.println("Usuário não cadastrado!");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		return idSala;
	}
	
}
