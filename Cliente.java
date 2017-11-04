
public class Cliente{
	private String nome;
	private String cpf;
	private int id;
	private static int quantidade = 0;
	
	public Cliente(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
		quantidade++;
		setId(quantidade);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	// aicionar exceção para cpf ter 11 digitos?
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
