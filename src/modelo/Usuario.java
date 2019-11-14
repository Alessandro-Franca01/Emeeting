package modelo;

public class Usuario {
	private int idUser;
	private String nome;
	private String cpf;
	private String tipo;
	private String senha;
	private String email;
	
	public Usuario() {
		
	}
	
	public Usuario(int idUser, String nome, String cpf, String tipo, String senha, String email) {
		this.idUser = idUser;
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
		this.senha = senha;
		this.email = email;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int id) {
		this.idUser = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Usuario [idUser=" + idUser + ", nome=" + nome + ", cpf=" + cpf + ", tipo=" + tipo + ", senha=" + senha
				+ ", email=" + email + "]";
	}
	
	
}
