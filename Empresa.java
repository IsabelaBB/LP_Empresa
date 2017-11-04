import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Empresa {
	private String nome;
	private String CNPJ;
	private ArrayList<Cliente> pessoas;
	private ArrayList<Servi�o> servi�os;
	
	public Empresa(String nome, String CNPJ) {
		setNome(nome);
		setCNPJ(CNPJ);
	}

	// Adiciona cliente ou prestador
	// necessita de tratamento de exce��o
	public void add(Cliente cliente) {
		if(consultaPessoa(cliente.getCpf()) == null) {
			if(cliente instanceof Prestador) pessoas.add(new Prestador(cliente.getNome(), cliente.getCpf()));
			else pessoas.add(new Cliente(cliente.getNome(), cliente.getCpf()));
		}
		else {
			System.out.println("Pessoa j� cadastrada");
		}
	}
	
	// Adiciona um servi�o na lista de servi�os que podem ser prestados
	// necessita de tratamento de exce��o
	public void add(String nome, String descricao, int duracao) {
		if(consultaServico(nome) == null) servi�os.add(new Servi�o(nome, descricao, duracao));
		else System.out.println("Servi�o j� existente!");
	}
	
	// consulta pessoa cliente ou prestador
	public Cliente consultaPessoa(String cpf) {
		for(Cliente i : pessoas) {
			if(i.getCpf().equals(cpf)) return i;
		}
		return null;
	}
	
	// consulta servico
	public Servi�o consultaServico(String nome) {
		for(Servi�o i : servi�os) {
			if(i.getNome().equalsIgnoreCase(nome)) return i;
		}
		return null;
	}
	
	// Retorna a lista dos prestadores que prestam um determinado servi�o e que possuem agenda livre
	public ArrayList<Prestador> buscarPrestadoresServico(String nomeServico, LocalDate dataInicio){
		ArrayList<Prestador> listaPrestadores = new ArrayList<>();
		Servi�o servico = consultaServico(nomeServico);
		
		// criar tratamento de erro para servi�o inexistente
		if(servico == null) return null;
		
		// criar tratamento de erro para quando n�o tem prestadores dispon�veis
		for(Cliente i : pessoas) {
			if((i instanceof Prestador) && (((Prestador)i).consultaAgenda(dataInicio, servico.getDuracao()) == null)) 
				listaPrestadores.add((Prestador) i);
		}
		return listaPrestadores;
	}
	
	// M�todo incompleto
	public boolean agendarServico(String CpfCliente, String nomeServico, LocalDate dataInicio, Prestador prest) throws ExcecaoID{
		Cliente c = null;
		Servi�o s = null;
		Prestador p = null;
		
		// testa se o cliente existe
		try{
			c = consultaPessoa(CpfCliente);
		}
		catch(ExcecaoID e){
			System.out.println(e.getMessage());
			return false;
		}
		
		// testa se o servi�o existe
		try{
			s = consultaServico(nomeServico);
		}
		catch(ExcecaoID e){
			System.out.println(e.getMessage());
			return false;
		}
		
		// testa se o prestador existe
		try{
			prest = (Prestador) consultaPessoa(prest.getCpf());
		}
		catch(ExcecaoID e){
			System.out.println(e.getMessage());
			return false;
		}
		
		// Se a agenda do prestador est� vazia, adiciona o servico na agenda
		// talvez precise de tratamento de exce��o aqui
		if(prest.consultaAgenda(dataInicio, s.getDuracao()) == null) {
			prest.addAgenda(dataInicio, c, s);
			return true;
		}
		
		
		return false;
	}
	
	// Retorna todos os prestadores
	public ArrayList<Prestador> getPrestadores(){
		ArrayList<Prestador> p = new ArrayList<>();
		for(Cliente i : pessoas) {
			if(i instanceof Prestador) p.add((Prestador)i);
		}
		return p;
	}
	
	// todos os prestadores tamb�m s�o clientes
	public ArrayList<Cliente> getClientes(){
		return pessoas;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

}
