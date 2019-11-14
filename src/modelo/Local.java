package modelo;

public class Local {
	
	private int idLocal;
	private String nomeLocal;
	private String estado;
	private String cidade;
	private String rua;
	private String bairro;
	
	public Local() {
		
	}
	
	public Local(String nomeLocal, String estado, String cidade, String rua, String bairro) {
		this.nomeLocal = nomeLocal;
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
	}
	
	public int getIdLocal() {
		return idLocal;
	}
	
	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	
	public String getNomeLocal() {
		return nomeLocal;
	}

	public void setNomeLocal(String nomeLocal) {
		this.nomeLocal = nomeLocal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	
}
