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
				e.printStackTrace();
			}
		return user;
				
	}
	// TESTAR, AMANHA !!
	public Object[] pesquisarReuniao(String local, String sala ,String data, String periodo, Connection conec ,ResultSet rt) {
		Object dados[] = new Object[8];
		try {
			PreparedStatement pst = conec.prepareStatement("SELECT PERIODO, DATA_REUNIAO, NOME_SALA, PISO, NOME_LOCAL, CIDADE, ESTADO, IDREUNIAO "
														+"FROM SALA "
														+"INNER JOIN LOCAL "
														+"ON IDLOCAL = ID_LOCAL "
														+"INNER JOIN REUNIAO "
														+"ON IDSALA = ID_SALA "
														+"WHERE PERIODO = ? AND DATA_REUNIAO = ? "
														+"AND NOME_SALA = ? AND NOME_LOCAL = ? ");
			
					pst.setString(1, periodo);
					pst.setString(2, data);
					pst.setString(3, sala);
					pst.setString(4, local);
					rt = pst.executeQuery();
					if (rt.next()) { 
						dados[0] = rt.getInt("IDREUNIAO");
						dados[1] = rt.getString("PERIODO");
						dados[2] = rt.getString("DATA_REUNIAO");
						dados[3] = rt.getString("NOME_SALA");
						dados[4] = rt.getString("PISO");
						dados[5] = rt.getString("NOME_LOCAL");
						dados[6] = rt.getString("CIDADE");
						dados[7] = rt.getString("ESTADO");						
					}else {
						System.out.println("Essa reuniao nao existe!");
					}
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
		return dados;
	}
	// Ajustar, verificação - Quando tentar confirmar uma reuniao que vc nao é convidado - TESTAR!! 
	public String confirmarParticipante(Connection coneccao, ResultSet rt, int idReuniao, int idUsuario, String confirmacao ) {
		String participacao = null;
		try {
			
			PreparedStatement pstPesquisa = coneccao.prepareStatement("SELECT PARTICIPACAO FROM USUARIO__REUNIAO "
															+"WHERE ID_USUARIO = ? AND ID_REUNIAO = ? ");
			pstPesquisa.setInt(1, idUsuario);
			pstPesquisa.setInt(2, idReuniao);
			rt = pstPesquisa.executeQuery();
			// to entendendo isso aqui nao!!!
			if(rt.next()) {
				participacao = rt.getString("PARTICIPACAO");
				
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
				}
			}else {
				System.out.println("Usuario nao esta convidado ou reuniao não existe!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return participacao;
	}
	// FAZER TESTES!!
	public boolean AddParticipante(String cpf, int idReuniao, Connection con, ResultSet pesquisa) {
		int idUser =Validacao.verificarUsuario(cpf, con, pesquisa);
		boolean retorno = false;
		if(idUser > 0) {
			try {
				PreparedStatement pst = con.prepareStatement("INSERT INTO USUARIO__REUNIAO VALUES( ?,  ?, ?) "); // testar essa query!
				
						pst.setInt(1, idReuniao);
						pst.setInt(2, idUser);
						pst.setString(3, "AGUARDANDO");
						int ver = pst.executeUpdate();
						con.commit();
						retorno = true;
						System.out.println("Linhas afetas: "+ver);
					}catch(SQLException e) {
						System.out.println(e.getMessage());
					}
			}
		return retorno;
		}
	
			
}
