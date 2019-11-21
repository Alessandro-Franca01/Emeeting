package modelo;

public class Acao {

	private String codigo;
	private int ordem;
	private String descricaoAcao;
	private String dataInicial;
	private int prazo;
	private String observacao;
	
	public Acao() {
		
	}
	
	public Acao(String codigo, int ordem, String descricaoAcao, String dataInicial, int prazo, String observacao) {
		super();
		this.codigo = codigo;
		this.ordem = ordem;
		this.descricaoAcao = descricaoAcao;
		this.dataInicial = dataInicial;
		this.prazo = prazo;
		this.observacao = observacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public String getDescricaoAcao() {
		return descricaoAcao;
	}

	public void setDescricaoAcao(String descricaoAcao) {
		this.descricaoAcao = descricaoAcao;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
	
}
