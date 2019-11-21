package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import modelo.Comum;
import modelo.Coordenador;
import modelo.Gestor;
import modelo.Reuniao;
import modelo.Sala;
import modelo.Usuario;
import view.UsuarioComum;
import view.UsuarioCoordenador;
import view.UsuarioGestor;
import controle.Validacao;

public class MetodosEstaticos {

	// Metodo de logar no sistema!!
	public static Usuario logandoGui(Connection coneccao, Statement st, ResultSet resultSet, String nome, String senha) {
		Usuario user = null;
		try {
			String tipoUsuario = null;
			st = coneccao.createStatement();
			resultSet = st.executeQuery("SELECT * FROM USUARIO");
			while(resultSet.next()) {
				if(nome.equalsIgnoreCase(resultSet.getString("NOME")) && senha.equalsIgnoreCase(resultSet.getString("SENHA"))) { 
					tipoUsuario = resultSet.getString("TIPO_USUARIO");
					// Verificar o tipo do Usuario, depois ir para a tela correspondente!
					if(tipoUsuario.equalsIgnoreCase("COMUM")) {
						user = new Comum(resultSet.getInt("IDUSUARIO"), resultSet.getString("NOME"), resultSet.getString("CPF"), 
								tipoUsuario, resultSet.getString("SENHA"), resultSet.getString("EMAIL"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							UsuarioComum frameComum  = new UsuarioComum();
							frameComum.receberUsuario((Comum) user);
							frameComum.receberConeccao(coneccao);
							frameComum.setVisible(true);
							// Recebendo os dados passados na tela de Login!
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(tipoUsuario.equalsIgnoreCase("COORDENADOR")) {
						// vou instaciar, usuario coordenador e abrir a tela dele
						user = new Coordenador(resultSet.getInt("IDUSUARIO"), resultSet.getString("NOME"), resultSet.getString("CPF"),
											   tipoUsuario, resultSet.getString("SENHA"), resultSet.getString("EMAIL"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							UsuarioCoordenador frameCoordenador = new UsuarioCoordenador();
							frameCoordenador.receberUsuario((Coordenador) user);
							frameCoordenador.receberConeccao(coneccao);
							frameCoordenador.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(tipoUsuario.equalsIgnoreCase("GESTOR")) {
						// vou instaciar, usuario gestor e abrir a tela dele
						user = new Gestor(resultSet.getInt("IDUSUARIO"), resultSet.getString("NOME"), resultSet.getString("CPF"), 
										  tipoUsuario, resultSet.getString("SENHA"), resultSet.getString("EMAIL"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							UsuarioGestor frameGestor = new UsuarioGestor();
							frameGestor.setVisible(true);
							frameGestor.receberUsuario((Gestor) user);
							frameGestor.receberConeccao(coneccao);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}		
				}
				else {
					// Add um label e fechar. ou posso mandar um label e limpar os campos e pedir para digitar novamente!
					
				}
			}
	    }catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}	
	
	// Pegar ID da SALA, retornar um objeto do tipo sala
	public static Sala getSala( int idSala, Connection coneccao, ResultSet resultSet ) {
		Sala sala = null;
		if(idSala != 0)
			try {
				PreparedStatement pst = coneccao.prepareStatement("SELECT * FROM SALA "
				          + "WHERE IDSALA = ? ");
				pst.setInt(1, idSala);
				resultSet = pst.executeQuery();
				if(resultSet.next()) {
					sala = new Sala(resultSet.getInt("IDSALA"), resultSet.getString("NOME_SALA"), resultSet.getString("PISO"), 
							resultSet.getString("NUMERO"), resultSet.getInt("ID_LOCAL"));
				}else {
					System.out.println("Esse Id não existe!");
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return sala;
	}
	// FUNCIONANDO!!!
	public static void cadastrarUsuario(Connection coneccao, PreparedStatement pst, ResultSet resultadoCadastro) {
		Scanner scCadastro = new Scanner(System.in);
		System.out.println("Digite seu CPF: ");
		String cpf = scCadastro.next();
		String tipoUsuario = "USUARIO";
		int id = Validacao.verificarUsuario(cpf, coneccao, resultadoCadastro);
		try {
		if(id == 0) {
			//scCadastro.reset();
			Scanner sc01 = new Scanner(System.in);
			System.out.println("Digite seu nome: ");
			String nome = sc01.nextLine();//RESOVIDO!!
			System.out.println("Digite sua senha: ");
			String senha = scCadastro.next();
			
				pst = coneccao.prepareStatement("INSERT INTO PESSOA VALUES(?, ?, ?, ?, ?)");
				pst.setString(1, null);
				pst.setString(2, nome);
				pst.setString(3, "USUARIO");
				pst.setString(4, cpf);
				pst.setString(5, senha);
				int ver = pst.executeUpdate();
				//System.out.println("Verificando linhas afetadas: "+ver);
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
	// FUNCIONANDO!!
	public static void addParticipante(Connection coneccao, PreparedStatement pst, ResultSet rts) {
		String horario, data, cpf = null;
		data = null;
		horario = null;
		int id = 0;
		try {
			int idReuniao = Validacao.verificarReuniao(id, data, horario, coneccao, rts);
			System.out.println("Digite o CPF do participante: ");
			//String cpf = inp.next();
			int idUsuario = Validacao.verificarUsuario(cpf, coneccao, rts);
			if((idReuniao > 0 ) && (idUsuario > 0)) {// faltou tratar alguns possiveis erros, mas o proprio MySLQ vai fazer isso!?
				pst = coneccao.prepareStatement("INSERT INTO PESSAO_REUNIAO VALUES(?, ?, ?)");
				pst.setInt(1, idReuniao);
				pst.setInt(2, idUsuario);
				pst.setString(3, null);
				// faltou o comando de executar query e de comit
				int ver = pst.executeUpdate();
				//System.out.println("Verificando linhas afetadas: "+ver);
				coneccao.commit();
				System.out.println("Participante adicionado com sucesso!");
			}else {
				System.out.println("O usuário ou a reunião não existe!");
			}
	  }catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	  	}
	}
	
	// ACHO QUE ESSE METODO NAO É USADO!!
	public static String[] getRow(Connection coneccao, Statement st, ResultSet rts) {
		System.out.println("Imprimindo as reuniões publicas");
		String linha[] = new String[7];
		//linha = {local, sala, piso, nome, periodo, data, confirmacao};
		try {
			st = coneccao.createStatement();
			rts = st.executeQuery("SELECT PERIODO, DATA_REUNIAO, NOME_SALA, NOME_LOCAL, NOME, PISO, CONFIRMAR_SALA "
								+"FROM SALA "
								+"INNER JOIN LOCAL "
								+"ON IDLOCAL = ID_LOCAL "
								+"INNER JOIN REUNIAO "
								+"ON IDSALA = ID_SALA "
								+"INNER JOIN USUARIO "
								+"ON IDUSUARIO = PROPRIETARIO "
								+"WHERE ACESSO = 'PUBLICO' ");
			int num = 1;
			
			while(rts.next()) {// AO INVES DE DÁ O PRINT VOU ADD NAS LINHAS DE CADA COLUNA!!
				//String local, sala, nome, periodo, confirmacao, piso , data;
				linha[0] =rts.getString("NOME_LOCAL");
				linha[1] = rts.getString("NOME_SALA");
				linha[2] = rts.getString("DATA_REUNIAO");
				linha[3] = rts.getString("NOME");
				linha[4] = rts.getString("PERIODO");
				linha[5] = rts.getString("PISO");
				linha[6] = rts.getString("CONFIRMAR_SALA");
				//System.out.println(local+"/"+sala+"/"+piso+"/"+nome+"/"+periodo+"/"+data+"/"+confirmacao);
				//linha = {local, sala, piso, nome, periodo, data, confirmacao};
				//linha[1] = local;
				num += 1;
		}
			System.out.println("Numero do linhas: "+num);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return linha;
		
	}
}
