package br.com.mobilesaude.source;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.cadastro.dao.GolDao;

@XmlRootElement(name="time")
@XmlAccessorType(XmlAccessType.FIELD)
public class Time implements Comparable<Time>{
	
	@XmlElement(name="id")
	private long id;
	private String nome;
	private int pontos;
	private int jogos;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int gp;
	private int gc;
	private int gs;
	double rendimento;
	
	List<Partida> partidas = new ArrayList<Partida>();
	
	String jogador;
	List<Artilheiro> artilheiros = new ArrayList<Artilheiro>();
	List<Gol> gols = new ArrayList<Gol>();
	
	public Time(){
		this.jogos=0;
	}
	
	public Time(String jogador, String time){
		this.jogador=jogador;
		this.nome=time;
		this.jogos=0;
	}
	
	public Time(long id, String nome, int pontos, int jogos, int vitorias, int empates, int derrotas, int gp, int gc, int gs){
		this.id=id;
		this.nome=nome;
		this.pontos=pontos;
		this.jogos=jogos;
		this.vitorias=vitorias;
		this.empates=empates;
		this.derrotas=derrotas;
		this.gp=gp;
		this.gc=gc;
		this.gs=gs;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getJogos() {
		return jogos;
	}
	
	public void jogosInc(){
		this.jogos++;
	}
	
	public void setJogos(int jogos) {
		this.jogos = jogos;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public int getEmpates() {
		return empates;
	}
	public void setEmpates(int empates) {
		this.empates = empates;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getGp() {
		return gp;
	}
	public void setGp(int gp) {
		this.gp = gp;
	}
	public int getGc() {
		return gc;
	}
	public void setGc(int gc) {
		this.gc = gc;
	}
	public int getGs() {
		return gs;
	}
	public void setGs(int gs) {
		this.gs = gs;
	}
	
	public String toString(){
		return "id: "+id+"   nome: "+nome+""
				+ "   pts: "+pontos+"   jogos: "+jogos+""
				+ "   vitorias: "+vitorias+"   empates: "+empates+""
				+ "   derrotas: "+derrotas+"   gp: "+gp+"   gc: "+gc+""
				+ "   gs: "+gs+"   %: "+rendimento;
	}
	
	public double getRendimento() {
		return rendimento;
	}

	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	
	public void mostrar(){
		System.out.println(toString());
	}
	
	public void atualizarSaldoGols(){
		this.gs = this.gp-this.gc;
	}
	
	public void fazerGol(){
		this.gp++;
		atualizarSaldoGols();
	}
	
	public void levarGol(){
		this.gc++;
		atualizarSaldoGols();
	}
	
	public void atualizarRendimento(){
		double max = this.jogos*3;
		this.rendimento = (double)this.pontos/max;
		this.rendimento=this.rendimento*100.0;
	}
	/* 
	 * vencer, empatar e perder
	 */
	public void vencer(){
		
		this.vitorias++;
		this.pontos+=3;
		this.jogos++;
		atualizarRendimento();
		
	}
	
	public void empatar(){
		this.empates++;
		this.pontos++;
		this.jogos++;
		atualizarRendimento();
	}
	
	public void perder(){
		this.derrotas++;
		this.jogos++;
		atualizarRendimento();
	}

	@Override
	public int compareTo(Time o) {
		// TODO Auto-generated method stub
		if( this.pontos < o.pontos) return 1;
		if( this.pontos > o.pontos ) return -1;
		if( this.pontos == o.pontos ){
			if( this.jogos > o.jogos ) return 1;
			if( this.jogos < o.jogos ) return -1;
		}
		return 0;
	}
	
	//bucar artilhiero por nome
	public Artilheiro buscar( String nome ){
		for(Artilheiro a : artilheiros ){
			if( nome.equals(nome) ){
				return a;
			}
		}
		return null;
	}
	
	public void mostrarPartidasJogadas(){
		for(Partida p : partidas){
			p.mostrar();
		}
	}

	public List<Artilheiro> getArtilheiros() {
		return artilheiros;
	}

	public void setArtilheiros(List<Artilheiro> artilheiros) {
		this.artilheiros = artilheiros;
	}

	public List<Gol> getGols() {
		return gols;
	}

	public void setGols(List<Gol> gols) {
		this.gols = gols;
	}
}
