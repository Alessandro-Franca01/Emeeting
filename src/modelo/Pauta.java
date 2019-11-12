package modelo;

public class Pauta {
	
	private int idPauta;
	private int ordem;
	private String tema;
	private String descricaoPauta;
	
	public Pauta() {
		
	}

	public Pauta(int idPauta, int ordem, String tema, String descricaoPauta) {
		this.idPauta = idPauta;
		this.ordem = ordem;
		this.tema = tema;
		this.descricaoPauta = descricaoPauta;
	}

	public int getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(int idPauta) {
		this.idPauta = idPauta;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDescricaoPauta() {
		return descricaoPauta;
	}

	public void setDescricaoPauta(String descricaoPauta) {
		this.descricaoPauta = descricaoPauta;
	}

}
