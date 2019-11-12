package dao;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Db {
	
	public static Connection conn = null;
	
	// conectando com o banco de dados
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = carregarPropriedades();
			String url = props.getProperty("url");
			conn = DriverManager.getConnection(url, props);
			System.out.println(conn.toString());
			conn.setAutoCommit(false);
			System.out.println(conn.getNetworkTimeout());
			Date atualizando = new Date();
			System.out.println(atualizando);
			}catch(SQLException erro1) {
				throw new DbExeption(erro1.getMessage());
			}
			
		}
		return conn;
	}
	
	// desconectando do banco
	public static void Desconectando() {
		if(conn != null) {
			try {
			conn.close();
			}catch(SQLException erro2){
				throw new DbExeption(erro2.getMessage());
			}finally {
				System.out.println("DESCONECTANDO");
			}
		}
			
	}
	
	
	// Esse metodo vai carregar as propriedades para a classe Properties
	private static Properties carregarPropriedades() {
		try (FileInputStream fileInp = new FileInputStream("propriedades.properties")){
			Properties pros = new Properties();
			pros.load(fileInp); // carregando as propriedades
			return pros; // retorna o objeto com as propriedades
		}catch(IOException erro) {
			throw new DbExeption(erro.getMessage());
		}
	}
	
	
	
	
}
