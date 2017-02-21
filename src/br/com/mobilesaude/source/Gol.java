package br.com.mobilesaude.source;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.cadastro.dao.PartidaDao;

@XmlRootElement(name="gol")
@XmlAccessorType(XmlAccessType.FIELD)
public class Gol {
	
	long id;
	long idPartida;
	Partida partida;
	String nomeArtilheiro;
	Artilheiro artilheiro;
	long idTime;
	Time time;
	
	public Gol(){
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(long idPartida) {
		
		this.idPartida = idPartida;
	}
	public Partida getPartida() {
		return partida;
	}
	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	public Artilheiro getArtilheiro() {
		return artilheiro;
	}
	public void setArtilheiro(Artilheiro artilheiro) {
		this.artilheiro = artilheiro;
	}
	public long getIdTime() {
		return idTime;
	}
	public void setIdTime(long idTime) {
		this.idTime = idTime;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}

	public String getNomeArtilheiro() {
		return nomeArtilheiro;
	}

	public void setNomeArtilheiro(String nomeArtilheiro) {
		this.nomeArtilheiro = nomeArtilheiro;
	}
	
	
	
}
