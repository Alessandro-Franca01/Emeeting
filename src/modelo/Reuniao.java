package modelo;

import java.util.Date;

public class Reuniao {
	private int idReuniao;
	private String local;
	private String sala;
	private Date data;
	private Ata ata;    
	
	public Reuniao() {
		
	}
	
	public Reuniao(int idReuniao, String local, String sala, Date data, Ata ata) {
		this.idReuniao = idReuniao;
		this.local = local;
		this.sala = sala;
		this.data = data;
		this.ata = ata;
	}
	public int getIdReuniao() {
		return idReuniao;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
}
