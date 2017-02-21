package br.com.mobilesaude.source;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "artilheiro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Artilheiro {

	long id;
	List<Partida> partidas;
	int gols;
	long idTime;
	Time time;
	String nome;
	
	public Artilheiro(){
		partidas = new ArrayList<Partida>();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Partida> getPartidas() {
		return partidas;
	}
	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}
	public int getGols() {
		return gols;
	}
	public void setGols(int gols) {
		this.gols = gols;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getIdTime() {
		return idTime;
	}

	public void setIdTime(long idTime) {
		this.idTime = idTime;
	}
	
	
}
