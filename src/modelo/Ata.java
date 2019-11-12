package modelo;

public class Ata {
	private int idAta;
	private String autor;
	private boolean ataAssanada;
	private String descricaoAta;
	private Pauta pauta;       // Nao sei se a data vai ficar na Ata ou na Reuniao ???
	
	public Ata() {
	}

	public Ata(int idAta, String autor, boolean ataAssanada, String descricaoAta) {
		this.idAta = idAta;
		this.autor = autor;
		this.ataAssanada = ataAssanada;
		this.descricaoAta = descricaoAta;
	}


	public int getIdAta() {
		return idAta;
	}

	public void setIdAta(int idAta) {
		this.idAta = idAta;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public boolean isAtaAssanada() {
		return ataAssanada;
	}

	public void setAtaAssanada(boolean ataAssanada) {
		this.ataAssanada = ataAssanada;
	}

	public String getDescricaoAta() {
		return descricaoAta;
	}

	public void setDescricaoAta(String descricaoAta) {
		this.descricaoAta = descricaoAta;
	}
	
	
}
