package modelo;

public class Sala {
	
	private int idSala;
	private String nomeSala;
	private String piso;
	private String numeroSala;
	private int idLocal;
	
	public Sala() {
		
	}
	
	public Sala(int idSala, String nomeSala, String piso, String numeroSala, int idlocal) {
		super();
		this.idSala = idSala;
		this.nomeSala = nomeSala;
		this.piso = piso;
		this.numeroSala = numeroSala;
		this.idLocal = idlocal;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}

	public int getIdLocal() {
		return idLocal;
	}

	public void setIdLocal(int idLocal) {
		this.idLocal = idLocal;
	}
	
	
}
