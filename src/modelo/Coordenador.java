package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controle.Validacao;

public class Coordenador extends Usuario{

	public Coordenador() {
		super();
	}

	public Coordenador(int idUser, String nome, String cpf, String tipo, String senha, String email) {
		super(idUser, nome, cpf, tipo, senha, email);
	}
	
	// Mesmo metodo do usuario Comum!!! Falta implementar no tela !
	public void criarReunaio(int idSala, String data, String periodo, String acesso, Connection conect, PreparedStatement pst, ResultSet rt) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// Verficação da Reuniao, retornando o ID da reuniao 
			int idReuniao = Validacao.verificarReuniao(idSala, data, periodo, conect, rt);
			if(idReuniao == 0) { 
				pst = conect.prepareStatement("INSERT INTO REUNIAO(IDREUNIAO, ID_SALA, PROPRIETARIO, DATA_REUNIAO, CONFIRMAR_SALA, ACESSO, PERIODO)"
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
	// Mesmo metodo do UsuarioComum - Falta implementar!!!
	public void confirmarParticipante(Connection coneccao, ResultSet rt, int idReuniao, int idUsuario, String confirmacao ) {
		// Tem que fazer a verificaçao ainda!!
		try {
			String participacao = null;
			PreparedStatement pstPesquisa = coneccao.prepareStatement("SELECT PARTICIPACAO FROM USUARIO__REUNIAO "
															+"WHERE ID_USUARIO = ? AND ID_REUNIAO = ? ");
			pstPesquisa.setInt(1, idUsuario);
			pstPesquisa.setInt(2, idReuniao);
			rt = pstPesquisa.executeQuery();
			if(rt.next()) {
				participacao = rt.getString("PARTICIPACAO");
			}
			if(participacao.equalsIgnoreCase("aguardando")) {
				PreparedStatement pst = coneccao.prepareStatement("UPDATE USUARIO__REUNIAO "
																	+"SET PARTICIPACAO = ? "
																	+"WHERE ID_REUNIAO = ? AND ID_USUARIO = ?");
					pst.setString(1, confirmacao);
					pst.setInt(2, idReuniao);
					pst.setInt(3, idUsuario);
					int resultado = pst.executeUpdate();
					System.out.println("Verificando update: "+resultado);
					coneccao.commit();
					
			}else if(participacao.equalsIgnoreCase("nao")) {
				System.out.println("Participação Negada!");
				
			}else if(participacao.equalsIgnoreCase("sim")) { 
				System.out.println("Voce já confirmou essa reuniao!");
			}else {
				System.out.println("Usuario nao esta convidado ou reuniao não existe!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
