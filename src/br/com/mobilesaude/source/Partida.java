package br.com.mobilesaude.source;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.cadastro.dao.TimeDao;

@XmlRootElement(name="partida")
@XmlAccessorType(XmlAccessType.FIELD)
public class Partida {

	private Time timeA;
	private Time timeB;
	
	private int placarA;
	private int placarB;
	
	long id;
	private long hash;
	boolean acabou;
	
	List<Artilheiro> artilheiros;
	
	public Partida(){
		artilheiros = new ArrayList<Artilheiro>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isAcabou() {
		return acabou;
	}

	public void setAcabou(boolean acabou) {
		this.acabou = acabou;
	}

	
	
	public Partida(Time timeA, Time timeB){
		this.timeA=timeA;
		this.timeB=timeB;
		acabou=false;
	}
	
	public void setIdtimeA(long id){
		timeA.setId(id);
	}
	public void setIdtimeB(long id){
		timeB.setId(id);
	}
	
	public Time getTimeA() {
		return timeA;
	}
	public void setTimeA(Time timeA) {
		this.timeA = timeA;
	}
	public Time getTimeB() {
		return timeB;
	}
	public void setTimeB(Time timeB) {
		this.timeB = timeB;
	}
	public int getPlacarA() {
		return placarA;
	}
	public void setPlacarA(int placarA) {
		this.placarA = placarA;
	}
	public int getPlacarB() {
		return placarB;
	}
	public void setPlacarB(int placarB) {
		this.placarB = placarB;
	}
	
	public void golA(){
		this.timeA.fazerGol();
		this.placarA++;
		this.timeB.levarGol();
		//mostrar();
	}
	
	public void golB(){
		this.timeB.fazerGol();
		this.placarB++;
		this.timeA.levarGol();
		//mostrar();
		
	}
	
	public void vencerA(){
		this.timeA.vencer();
		this.timeB.perder();
	}
	
	public void vencerB(){
		this.timeB.vencer();
		this.timeA.perder();
	}
	
	public void empatar(){
		this.timeA.empatar();
		this.timeB.empatar();
	}
	
	/*
	 * finaliza partida e atualiza os times
	 * atualiza times no bd
	 */
	public void finalizarPartida(){
		TimeDao dao = new TimeDao();
		if(this.acabou==false){
			if(this.placarA>this.placarB){
				vencerA();
			}
			if(this.placarB>this.placarA){
				vencerB();
			}
			if(this.placarB==this.placarA){
				empatar();
			}
			this.acabou=true;
		}
		dao.altera(timeA);
		dao.altera(timeB);
		//mostrar();
	}
	
	public void mostrar(){
		System.out.println("hash: "+hash);
		System.out.println(timeA.getNome()+"  x  "+timeB.getNome());
		System.out.println("  "+placarA    +"    x    "+placarB);
	}
	
	public void mostrarConfronto(){
		System.out.println(timeA.getNome()+"  x  "+timeB.getNome());
	}
	
	public void mostrarPlacar(){
		System.out.println("  "+placarA    +"    x    "+placarB);
	}

	public long getHash() {
		return hash;
	}

	public void setHash(long hash) {
		this.hash = hash;
	}
	
}
