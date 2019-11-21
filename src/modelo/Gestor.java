package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import controle.Validacao;

public class Gestor extends Usuario{

	public Gestor() {
		super();
	}

	public Gestor(int idUser, String nome, String cpf, String tipo, String senha, String email) {
		super(idUser, nome, cpf, tipo, senha, email);
	}
	
	// Metodos do Gestor
	//Vai ser um INSERT INTO LOCAL VALUES()
	public boolean cadastrarLocal(Connection con, ResultSet pesquisa, String local, String estado, String cidade) {
		boolean retorno = false;
		int idLocal = Validacao.verificarLocal(con, pesquisa, local, estado, cidade);
		if(idLocal > 0) {
			// codigo mysql
		}
		
		return retorno;
	}
	
	//Vai ser um INSERT INTO SALA VALUES()
	public boolean cadastrarSala(Connection con, ResultSet pesquisa, String nomeLocal, String nomeSala, String cidade) {
		boolean retorno = false;
		int idSala = Validacao.verificarSala(con, pesquisa, nomeLocal, nomeSala, cidade);
		if(idSala > 0) {
			// codigo mysql
			
			retorno = true;
		}
		return retorno;
	}
	
	//Vai ser um UPDATE FROM REUNIAO SET CONFIRMAR_SALA = 'SIM';
	public void confirmarSala() {
		
	}
	
}
