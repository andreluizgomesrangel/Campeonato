package br.com.mobilesaude.source;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="tabela")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tabela {
	List<Time> times;
	
	public Tabela(){
		
	}
	
	public Tabela(List<Time> times){
		this.times = times;
	}
	
	public void mostrarTime(Time t){
		System.out.println("    "+t.getNome()+"   "+"   "+t.getPontos()+"      "+"     "+t.getJogos()+"      "+"     "+t.getVitorias()+"      "+"       "+t.getEmpates()+"      "+"      "+t.getDerrotas()+"      "+"    "+t.getGp()+"     "+"   "+t.getGc()+"   "+"    "+t.getGs()+"    "+"   "+t.getRendimento());
	}
	
	public void mostrar(){
		System.out.println("  -------------------------------------------------------------------------------------------------------------------");
		System.out.println("POSICAO   TIME      PONTOS      JOGOS      VITORIAS      EMPATES      DERROTAS      GP      GC      GS       %");

		times.sort(null);
		for(int i=0;i<times.size();i++){
			rendimento(times.get(i));
			System.out.print("   "+(i+1)+"o ");
			mostrarTime(times.get(i));
		}
		System.out.println("  -------------------------------------------------------------------------------------------------------------------");

		
		
	}

	public void rendimento(Time t){
		if(t.getJogos()==0){
			t.setRendimento(0);
		}
		else{
			t.setRendimento( ((double)(100.0*t.getPontos()))/((double)3.0*t.getJogos()) );
		}
		
	}
	
	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}
	
	
}
