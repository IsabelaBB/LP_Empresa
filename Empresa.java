import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Empresa {
	private String nome;
	private String CNPJ;
	private ArrayList<Cliente> pessoas;
	private ArrayList<Serviço> serviços;
	
	public Empresa(String nome, String CNPJ) {
		setNome(nome);
		setCNPJ(CNPJ);
	}

	// Adiciona cliente ou prestador
	// necessita de tratamento de exceção
	public void add(Cliente cliente) {
		if(consultaPessoa(cliente.getCpf()) == null) {
			if(cliente instanceof Prestador) pessoas.add(new Prestador(cliente.getNome(), cliente.getCpf()));
			else pessoas.add(new Cliente(cliente.getNome(), cliente.getCpf()));
		}
		else {
			System.out.println("Pessoa já cadastrada");
		}
	}
	
	// Adiciona um serviço na lista de serviços que podem ser prestados
	// necessita de tratamento de exceção
	public void add(String nome, String descricao, int duracao) {
		if(consultaServico(nome) == null) serviços.add(new Serviço(nome, descricao, duracao));
		else System.out.println("Serviço já existente!");
	}
	
	// consulta pessoa cliente ou prestador
	public Cliente consultaPessoa(String cpf) {
		for(Cliente i : pessoas) {
			if(i.getCpf().equals(cpf)) return i;
		}
		return null;
	}
	
	// consulta servico
	public Serviço consultaServico(String nome) {
		for(Serviço i : serviços) {
			if(i.getNome().equalsIgnoreCase(nome)) return i;
		}
		return null;
	}
	
	// Retorna a lista dos prestadores que prestam um determinado serviço e que possuem agenda livre
	public ArrayList<Prestador> buscarPrestadoresServico(String nomeServico, LocalDate dataInicio){
		ArrayList<Prestador> listaPrestadores = new ArrayList<>();
		Serviço servico = consultaServico(nomeServico);
		
		// criar tratamento de erro para serviço inexistente
		if(servico == null) return null;
		
		// criar tratamento de erro para quando não tem prestadores disponíveis
		for(Cliente i : pessoas) {
			if((i instanceof Prestador) && (((Prestador)i).consultaAgenda(dataInicio, servico.getDuracao()) == null)) 
				listaPrestadores.add((Prestador) i);
		}
		return listaPrestadores;
	}
	
	// Método incompleto
	public boolean agendarServico(String CpfCliente, String nomeServico, LocalDate dataInicio, Prestador prest) throws ExcecaoID{
		Cliente c = null;
		Serviço s = null;
		Prestador p = null;
		
		// testa se o cliente existe
		try{
			c = consultaPessoa(CpfCliente);
		}
		catch(ExcecaoID e){
			System.out.println(e.getMessage());
			return false;
		}
		
		// testa se o serviço existe
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
		
		// Se a agenda do prestador está vazia, adiciona o servico na agenda
		// talvez precise de tratamento de exceção aqui
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
	
	// todos os prestadores também são clientes
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
