package modelo;

import java.util.Date;

public class Reuniao {
	private int idReuniao;
	private int  idLocal;  // Posso pegar um objeto Localaaaaaaaa
	private String data;
	private Ata ata;    
	
	public Reuniao() {
		
	}
	
	public Reuniao(int idReuniao, int idLocal, String data, Ata ata) {
		this.idReuniao = idReuniao;
		this.idLocal = idLocal;
		this.data = data;
		this.ata = ata;
	}

}
