
public class Serviço {
	private String nome;
	private String descricao;
	private int duracao;
	
	public Serviço(String nome, String descricao, int duracao) {
		setNome(nome);
		setDescricao(descricao);
		setDuracao(duracao);
	}
	
	public int getDuracao() {
		return duracao;
	}

	// talvez precise adicionar uma exceção aqui para duração menor ou igual a zero
	public void setDuracao(int duracao) {
		if(duracao < 1) this.duracao = 1;
		else this.duracao = duracao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
}
