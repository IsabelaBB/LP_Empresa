import java.time.LocalDate;
import java.util.ArrayList;

public class Prestador extends Cliente{
	
	private ArrayList<Agenda> agenda;
	
	public Prestador(String nome, String cpf) {
		super(nome, cpf);
		agenda = new ArrayList<>();
	}

	// se tiver adicionado uma exceção ao criar data para data inválida, talvez precis adicionar um try catch aqui
	public boolean addAgenda(LocalDate data, Cliente cliente, Serviço servico) {
		if(consultaAgenda(data, servico.getDuracao()) == null) {
			this.agenda.add(new Agenda(data.getDayOfMonth(), data.getMonthValue(), data.getYear(), cliente, servico));
			return true;
		} else
			return false;
	}
	
	public boolean removeAgenda(Agenda agenda) {
		if(consultaAgenda(agenda.getData(), agenda.getServiço().getDuracao()) != null) {
			this.agenda.remove(agenda);
			return true;
		}else
			return false;
	}
	
	// não dá para adicionar uma exceção quando a data não estiver na agenda
	// porque pode ser justamente o que está sendo procurado, como é o caso quando uma nova data é adicionada à agenda
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
