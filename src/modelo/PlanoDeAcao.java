package modelo;

import java.util.Date;

public class PlanoDeAcao {
	// Definir como vai ser essa classe ainda!!
	private Usuario responsavel;
	private String descricaoPlanoAcao;
	private String dataFinal;
	private Acao[] listaDeAcoes;
	private String dataInicial;
	
	public PlanoDeAcao() {
		
	}
	
	public PlanoDeAcao(Usuario responsavel, String descricaoPlanoAcao, String dataFinal, Acao[] listaDeAcoes,
			String dataInicial) {
		super();
		this.responsavel = responsavel;
		this.descricaoPlanoAcao = descricaoPlanoAcao;
		this.dataFinal = dataFinal;
		this.listaDeAcoes = listaDeAcoes;
		this.dataInicial = dataInicial;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public String getDescricaoPlanoAcao() {
		return descricaoPlanoAcao;
	}

	public void setDescricaoPlanoAcao(String descricaoPlanoAcao) {
		this.descricaoPlanoAcao = descricaoPlanoAcao;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Acao[] getListaDeAcoes() {
		return listaDeAcoes;
	}

	public void setListaDeAcoes(Acao[] listaDeAcoes) {
		this.listaDeAcoes = listaDeAcoes;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	
	
}
