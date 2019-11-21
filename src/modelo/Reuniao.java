package modelo;

import java.util.Date;

public class Reuniao {
	private int idReuniao;
	private Local local;
	private Sala sala;
	private String data;
	private String periodo;
	private Ata ata;    
	
	public Reuniao() {
		
	}
	
	public Reuniao(int idReuniao, Local local, String data, String periodo, Sala sala, Ata ata) {
		this.idReuniao = idReuniao;
		this.local = local;
		this.data = data;
		this.ata = ata;
		this.periodo = periodo;
		this.sala = sala;
	}

	public int getIdReuniao() {
		return idReuniao;
	}

	public void setIdReuniao(int idReuniao) {
		this.idReuniao = idReuniao;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}
	
	

}
