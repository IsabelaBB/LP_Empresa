import java.time.LocalDate;
import java.util.ArrayList;

public class Prestador extends Cliente{
	
	private ArrayList<Agenda> agenda;
	
	public Prestador(String nome, String cpf) {
		super(nome, cpf);
		agenda = new ArrayList<>();
	}

	// se tiver adicionado uma exce��o ao criar data para data inv�lida, talvez precis adicionar um try catch aqui
	public boolean addAgenda(LocalDate data, Cliente cliente, Servi�o servico) {
		if(consultaAgenda(data, servico.getDuracao()) == null) {
			this.agenda.add(new Agenda(data.getDayOfMonth(), data.getMonthValue(), data.getYear(), cliente, servico));
			return true;
		} else
			return false;
	}
	
	public boolean removeAgenda(Agenda agenda) {
		if(consultaAgenda(agenda.getData(), agenda.getServi�o().getDuracao()) != null) {
			this.agenda.remove(agenda);
			return true;
		}else
			return false;
	}
	
	// n�o d� para adicionar uma exce��o quando a data n�o estiver na agenda
	// porque pode ser justamente o que est� sendo procurado, como � o caso quando uma nova data � adicionada � agenda
	public Agenda consultaAgenda(LocalDate dataInicio, int duracao){
		while(duracao > 0) {
			for(int i = 0; i < agenda.size(); i++) {
				if(dataInicio.equals(agenda.get(i).getData())) return agenda.get(i);
			}
			dataInicio.plusDays(1);
			duracao--;
		}
		return null;
	}
}
