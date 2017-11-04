import java.time.LocalDate;

public class Agenda {
	private LocalDate data;
	private Cliente cliente;
	private Servi�o servi�o;
	
	public Agenda(int dia, int mes, int ano, Cliente cliente, Servi�o servi�o) {
		setdata(dia, mes, ano);
		setCliente(cliente);
		setServi�o(servi�o);
	}

	public LocalDate getData() {
		return data;
	}

	// tavez seja necess�rio tratar uma data inv�lida
	public void setdata(int dia, int mes, int ano) {
		this.data = LocalDate.of(dia, mes, ano);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Servi�o getServi�o() {
		return servi�o;
	}

	public void setServi�o(Servi�o servi�o) {
		this.servi�o = servi�o;
	}
	
	
	
}
