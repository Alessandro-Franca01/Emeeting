package controle;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;

//import java.sql.Statement;
import java.sql.PreparedStatement;

public class Validacao {
	// Consulta o banco e retorna o ID da reuniao, ela existir - TESTAR !!
	public static int verificarReuniao(int idSala, String data, String periodo, Connection conec,ResultSet rt) { 
	    int idReuniao = 0;
		try {
			PreparedStatement pst = conec.prepareStatement("SELECT DATA_REUNIAO, PERIODO, IDREUNIAO, IDSALA, IDREUNIAO "  
													+"FROM REUNIAO "
													+"INNER JOIN SALA "
													+"ON IDSALA = ID_SALA "
													+"WHERE DATA_REUNIAO = '?' AND ID_SALA = ? AND PERIODO = ?");
					pst.setString(1, data);
					pst.setInt(2, idSala);
					pst.setString(3, periodo);
					rt = pst.executeQuery();
					if (rt.next()) { 
						idReuniao = rt.getInt("IDREUNIAO");
						System.out.println("O ID da reuniao é = "+idReuniao);
					}else {
						System.out.println("Essa reuniao nao existe!");
					}
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				return idReuniao;
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
									String nomeSala, String cidade) {
		int idSala = 0;
		try {// Erro na query, corrigir ainda hoje!!
			PreparedStatement pst = con.prepareStatement("SELECT NOME_LOCAL, CIDADE, NOME_SALA, IDSALA "
														+"FROM LOCAL "
														+"INNER JOIN SALA "
														+"ON IDLOCAL = ID_LOCAL "
														+"WHERE NOME_SALA = ? AND NOME_LOCAL = ? AND CIDADE = ?");
			pst.setString(1, nomeSala);
			pst.setString(2, nomeLocal);
			pst.setString(3, cidade);
			pesquisa = pst.executeQuery();
			if (pesquisa.next()) { // erro aqui!!???
				idSala = pesquisa.getInt("IDSALA");
				System.out.println("O id da sala é: "+idSala);
			}else {
				System.out.println("Usuário não cadastrado!");
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
			
		return idSala;
	}
	
}
