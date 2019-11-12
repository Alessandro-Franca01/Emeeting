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
import modelo.Usuario;
import view.TelaUsuarioComum;
import view.TelaUsuarioCoordenador;
import view.TelaUsuarioGestor;
import view.UsuarioComum;
import controle.Validacao;

public class MetodosEstaticos {

	public static Usuario logando(String nome, String senha, Connection c, Statement st, ResultSet r) {
		Usuario u = new Usuario();
		try {
			st = c.createStatement();
			r = st.executeQuery("SELECT * FROM USUARIO");
			while(r.next()) {
				if(nome.equalsIgnoreCase(r.getString("NOME")) && senha.equalsIgnoreCase(r.getString("SENHA"))) { 
					u.setNome(r.getString("NOME"));
					u.setSenha(r.getString("SENHA"));
					u.setCpf(r.getString("CPF"));
					u.setTipo(r.getString("TIPO_USUARIO"));
					u.setIdUser(r.getInt("IDUSUARIO"));
					break;
				}
			}
			if(u.getNome() != null) {
				System.out.println("Você ESTA logado!");
			}else 
				System.out.println("Você NAO esta logado!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	// Fazer ajustes no banco e no metodo ( add coluna email, etc) - alteração no banco feito!
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
										tipoUsuario, resultSet.getString("SENHA"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							UsuarioComum frameComum = new UsuarioComum();
							frameComum.setVisible(true);
							// Recebendo os dados passados na tela de Login!
							frameComum.receberUsuario((Comum) user);
							frameComum.receberConeccao(coneccao);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(tipoUsuario.equalsIgnoreCase("COORDENADOR")) {
						// vou instaciar, usuario coordenador e abrir a tela dele
						user = new Coordenador(resultSet.getInt("IDUSUARIO"), resultSet.getString("NOME"), resultSet.getString("CPF"),
											   tipoUsuario, resultSet.getString("SENHA"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							TelaUsuarioCoordenador frameCoordenador = new TelaUsuarioCoordenador();
							frameCoordenador.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if(tipoUsuario.equalsIgnoreCase("GESTOR")) {
						// vou instaciar, usuario gestor e abrir a tela dele
						user = new Gestor(resultSet.getInt("IDUSUARIO"), resultSet.getString("NOME"), resultSet.getString("CPF"), 
										  tipoUsuario, resultSet.getString("SENHA"));
						System.out.println(user.getClass());
						System.out.println(user.toString());
						try {
							TelaUsuarioGestor frameGestor = new TelaUsuarioGestor();
							frameGestor.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}		
				}
				else {
					// Add um label e fechar. ou posso mandar um label e limpar os campos e pedir para digitar novamente!
					//lbMensagem
				}
			}
	    }catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}	
		
	// Metodo de criação de Reuniao - RODANDO SEM ERROS, SÓ FALTA COLOCAR AS HORAS NA DATA,
	//TALVEZ MODIFICAR O CAMPO SALA PARA DAR SUPORTE A NOMES DE SALA TIPO = "AUDITORIO CENTRAL" 
	public static void criarReunaio(Connection c, PreparedStatement p, ResultSet r) {
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
	// FUNCIONANDO
	public static void confirmarParticipacao(Usuario usuario, Connection con, ResultSet rt, PreparedStatement pst) {
		
		Scanner inp = new Scanner(System.in);
		String local, sala, data, confirmacao;
		boolean controle = true;
		System.out.println("Digite os dados da reunião:");// ESSA LINHA NAO FOI LIDA ?
		System.out.print("Local: ");
		local = inp.next();
		System.out.print("Sala:");
		sala = inp.next();
		System.out.print("Data:");
		data = inp.next();
		try {
			int idReuniao = Validacao.verificarReuniao(local, sala, data, con, rt);
			if(idReuniao == 0) {
				System.out.println("Nao existe essa reuniao!");
			}else if(idReuniao > 0) {
				// se entrar aqui é pq a reuniao existe!
				pst = con.prepareStatement("SELECT PARTICIPACAO FROM PESSAO_REUNIAO "
									     + " WHERE ID_PESSOA = ? AND ID_REUNIAO = ?");
				pst.setInt(1, usuario.getIdUser());
				pst.setInt(2, idReuniao);
				rt = pst.executeQuery();
				if (rt.next()) { // TA RODANDO SHOW!! TERMINAR DE IMPLEMENTAR!!!
					//System.out.println("Se entrar aqui, é por que o usuario foi convidado para a reuniao!");	
					String estatus = rt.getString("PARTICIPACAO");
					System.out.println("O estatus de confirmação é "+estatus);
					if(estatus != null) {
						if(estatus.equalsIgnoreCase("sim")) {
							System.out.println("Sua presença já foi CONFIRMADA para essa reuniao");
						}else if(estatus.equalsIgnoreCase("nao")) {
							System.out.println("Sua presença já foi NEGADA para essa reuniao");
						}
					}else{//else if(estatus == null) 
						// Se entrar aqui pq o campo PARTICIPACAO = NULL 
						System.out.println("Digite 's' ou 'n' para confirmar ou negar sua participação s/n");
						confirmacao = inp.next();
						pst = con.prepareStatement("SELECT PARTICIPACAO FROM PESSAO_REUNIAO "
							     + " WHERE ID_PESSOA = ? AND ID_REUNIAO = ?");
						while(controle) 
							if(confirmacao.equalsIgnoreCase("s")) {
								pst = con.prepareStatement("UPDATE PESSAO_REUNIAO " 
															+"SET PARTICIPACAO = 'SIM' "
															+"WHERE ID_REUNIAO = ? AND ID_PESSOA = ?");
								pst.setInt(1, idReuniao);
								pst.setInt(2, usuario.getIdUser());
								int ver = pst.executeUpdate();
								System.out.println("Verificando linhas afetadas: "+ver);
								con.commit();
								System.out.println("Participação confirmada");
								controle =false;
							}else if(confirmacao.equalsIgnoreCase("n")) {
								pst = con.prepareStatement("UPDATE PESSAO_REUNIAO " 
															+"SET PARTICIPACAO = 'NAO' "
															+"WHERE ID_REUNIAO = ? AND ID_PESSOA = ?");
								pst.setInt(1, idReuniao);
								pst.setInt(2, usuario.getIdUser());
								int ver = pst.executeUpdate();
								System.out.println("Verificando linhas afetadas: "+ver);
								con.commit();
								System.out.println("Participação negada");
								controle =false;
							}else {
								System.out.println("Digite o valor esperado: s/n");
								controle = true;
							  }
							}
					}else {
						System.out.println("Usuario NÃO convidado para a reunião");
					}
				}else {// FALTA TESTAR ESSA PARTE DO METODO!!!!
					//System.out.println("Se entrar aqui, pq na minha logica nao o usuario nao foi convidado para a reuniao!");
					System.out.println("Se entrar aqui, é pq o idReuniao é negativo. Nao tem como ser entra aqui!");
				}
				
		}catch (SQLException e) {
				e.printStackTrace();
		   }
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
		Scanner inp = new Scanner(System.in);
		String local, sala, data;
		System.out.println("Digite os dados da reunião:");
		System.out.print("Local: ");
		local = inp.next();
		System.out.print("Sala:");
		sala = inp.next();
		System.out.print("Data:");
		data = inp.next();
		try {
			int idReuniao = Validacao.verificarReuniao(local, sala, data, coneccao, rts);
			System.out.println("Digite o CPF do participante: ");
			String cpf = inp.next();
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
	
	// TRABALHAR NESSE METODOS AGORA, SEM PERCA DE TEMPO!!
	public static void listarReunioes(Connection coneccao, Statement st, ResultSet rts) {
		System.out.println("Imprimindo as reuniões publicas");
		try {
			st = coneccao.createStatement();
			rts = st.executeQuery("SELECT LOCAL, SALA, DATA, ID_PESSOA FROM REUNIAO "
					+"WHERE ACESSIBIIDADE = 'PUBLICO' ");
			int num = 1;
			while(rts.next()) {
				System.out.println("Reuniao n° "+num);
				System.out.print("LOCAL: "+rts.getString("LOCAL")+" / ");
				System.out.print("SALA: "+rts.getString("SALA")+" / ");
				System.out.print("DATA: "+rts.getString("DATA")+" / ");
				System.out.println("ID PROPRIETARIO: "+rts.getInt("ID_PESSOA"));
				num += 1;
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
