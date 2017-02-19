package br.com.mobilesaude.source;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.mobilesaude.cadastro.dao.PartidaDao;
import br.com.mobilesaude.cadastro.dao.TimeDao;


/*
 * ENTRAR COM TIMES
 * 	- A cada time pego, inserir no BD
 * 
 * INICIAR CAMPEONATO
 * 	- Gerar lista de partidas e inserir no BD
 *
 * 
 */
@XmlRootElement(name="campeonato")
@XmlAccessorType(XmlAccessType.FIELD)
public class Campeonato {

	List<Time> times;
	long idTime;
	
	Tabela tabela;
	
	boolean iniciou = false;
	boolean fim = false;
	
	List<Partida> partidas;
	Partida partidaAtual;
	long idPartida;
	

	public Campeonato(){
		this.idTime=1;
		this.times=new ArrayList<Time>();
		this.tabela = new Tabela(times);
		partidas = new ArrayList<Partida>();
		idPartida = 0;
	}
	
	/*
	 * pegar times
	 * formar partidas com um dado numero de rodadas
	 * 
	 */
	public void iniciar(int rodadas){
		DBcarregar();
		if(partidas.size()>1){
			iniciou=true;
		}
		if(iniciou==false){
			pegarTimes();
			rodada(rodadas);
			iniciou=true;
		}
	}

	public void pegarTimes(){
		/*novoTime("barce", "marcos");
		novoTime("mcity", "andre");
		novoTime("bayer", "jhnoy");
		novoTime("realm", "fernando");*/
		this.times = DBgetTimes();
	}
	
	public void DBcarregar(){
		this.times = DBgetTimes();
		this.partidas = DBgetPartidas();
		tabela.setTimes(times);
	}
	
	public List<Time> DBgetTimes(){
		TimeDao dao = new TimeDao();
		List<Time> t = dao.getLista();
		return t;
	}
	
	public List<Partida> DBgetPartidas(){
		PartidaDao dao = new PartidaDao();
		List<Partida> p = dao.getLista(times);
		return p;
	}
	
	/*
	 * cria um time
	 * insere na lista de times
	 * insere no bd
	 */
	public void novoTime(String time, String jogador){
		Time t = new Time(jogador,time);
		t.setId(idTime);
		this.times.add(t);
		this.idTime++;
		TimeDao dao = new TimeDao();
		dao.adiciona(t);
	}
	
	public void mostrarTimes(){
		for(Time t : times){
			t.mostrar();
		}
	}
	
	public void mostrarTabela(){
		this.tabela.mostrar();
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}
	
	public Time buscaTime(long id, List<Time> times){
		for(Time t : times){
			if(t.getId()==id){
				return t;
			}
		}
		return null;
	}
	
	long calculaHash(long a, long b){
		return a+16*b;
	}
	
	//no caso de um campeonato de pontos corridos so de ida
	Partida buscaPartida(long id1, long id2, List<Partida> partidas){
		
		long hash = calculaHash(id1,id2);
		
		for(Partida p : partidas){
			long hasha, hashb;
			hasha = calculaHash(p.getTimeA().getId(), p.getTimeB().getId());
			hashb = calculaHash(p.getTimeB().getId(), p.getTimeA().getId());
			if( hash==hasha ||  hash==hashb){
				//partida existe
				return p;
			}
		}
		//partida  n existe 
		return null;
		/*for(int i=0;i<partidas.size();i++){
			Partida p = partidas.get(i);
			if( p.getTimeA().getId() == id1 && p.getTimeB().getId() == id2){
				return p;
			}
			if( p.getTimeA().getId() == id2 && p.getTimeB().getId() == id1){
				return p;
			}
		}
		return null;*/
	}
	
	
	public void rodada(int rodadas){
		
		idPartida = 1;
		int qtdJogos = times.size()-1;
		PartidaDao dao = new PartidaDao();
		times.sort(null);
		
		
		while( times.get(0).getJogos() < qtdJogos ){
			int i = 1;
			Time t1, t2;
			t1 = times.get(0);
			t2 = times.get(i);
			
			Partida busca = buscaPartida( t1.getId() , t2.getId() , partidas );
			System.out.println(times.size());
			while( busca!=null && i < times.size() ){
				i++;
				t2 = times.get(i);
				busca = buscaPartida( t1.getId() , t2.getId() , partidas );
				
			}
			if(busca==null){
				
				long hash = calculaHash(t1.getId(), t2.getId());
				Partida nova = new Partida();
				nova.setHash(hash);
				nova.setId(idPartida);
				t1.jogosInc();
				t2.jogosInc();
				nova.setTimeA(t1);
				nova.setTimeB(t2);
				dao.adiciona(nova);
				partidas.add(nova);
				nova.mostrarConfronto();
				idPartida++;
				times.sort(null);
			}
			
			
		}
		
		if(rodadas==2){
			List<Partida> volta = new ArrayList<Partida>();
			for(Partida p : partidas){
				Time t1 = p.getTimeA();
				Time t2 = p.getTimeB();
				t1.jogosInc();
				t2.jogosInc();
				Partida p2 = new Partida(t2, t1);
				//p2.mostrarConfronto();;
				volta.add(p2);
			}
			for(Partida p : volta){
				p.setId(idPartida);
				idPartida++;
				partidas.add(p);
				dao.adiciona(p);
			}
			
		}
		
		for(Time t : times ){
			t.setJogos(0);
		}
	}
	/*
	 * gera a lista de partidas
	 * insere essa lista no bd
	 * 
	 */
	public void roda(int rodadas){
		//this.partidas = gerarPartidas(parametro);
		//mostrarPartidas();
		
		PartidaDao dao = new PartidaDao();
		idPartida++;
		
		//qtd jogos por time
		int qtdJogos;
		qtdJogos=times.size()-1;
		
		while( times.get(0).getJogos() < qtdJogos ){
			times.sort(null);
			
			Partida busca = null;		
			busca = buscaPartida(times.get(0).getId(), times.get(1).getId(), partidas);	
			int i2=2;
			
			while(busca!=null && i2<times.size()-1){
				busca.mostrar();
				busca = buscaPartida(times.get(0).getId(), times.get(i2).getId(), partidas);
				i2++;
			}
			times.get(0).jogosInc();
			times.get(i2).jogosInc();
			Partida p = new Partida(times.get(0), times.get(i2));
			p.setId(idPartida);
			idPartida++;
			long hash = p.getTimeA().getId() + 16*p.getTimeB().getId();
			p.setHash(hash);
			partidas.add(p);
			//p.mostrar();
			dao.adiciona(p);
			
			times.sort(null);
		}
		
		int qtdRodadas=1;
		if(rodadas==2){
			List<Partida> volta = new ArrayList<Partida>();
			for(Partida p : partidas){
				Time t1 = p.getTimeA();
				Time t2 = p.getTimeB();
				t1.jogosInc();
				t2.jogosInc();
				Partida p2 = new Partida(t2, t1);
				//p2.mostrarConfronto();;
				volta.add(p2);
			}
			for(Partida p : volta){
				p.setId(idPartida);
				idPartida++;
				partidas.add(p);
				dao.adiciona(p);
			}
			
		}
		
		for(Time t : times ){
			t.setJogos(0);
		}
		idPartida=0;		
	}
	
	//diz qual eh a partida atual
	void setPartidaAtual(){
		idPartida=0;
		for(int i=0; i<partidas.size();i++){
			if(partidas.get(i).isAcabou()==false){
				idPartida = (i+1);
				break;
			}
		}
		//idPartida++;
		int i = (int) (idPartida-1);
		partidaAtual = partidas.get(i);
	}
	
	//mostra a partida autal "inicia a partida"
	public void iniciarPartida(){
		if(partidas.size()==0){
			fim = true;
		}
		else if( partidas.get( partidas.size()-1 ).acabou==true ){
			fim=true;
		}
		if(fim==false){
			setPartidaAtual();
			partidaAtual.mostrarConfronto();
		}
	}

	public void golA(){
		if(fim==false)
		if(partidaAtual.acabou==false){
			partidaAtual.golA();
			partidaAtual.mostrarPlacar();
		}
	}
	
	public void golB(){
		if(fim==false)
		if(partidaAtual.acabou==false){
		partidaAtual.golB();
		partidaAtual.mostrarPlacar();
		}
	}
	
	/* finaliza a partida atual ja setando a proxima partida
	 * atualiza partida no bd
	 * 
	 * 
	 */
	public void finalizarPartida(){
		PartidaDao dao = new PartidaDao();
		if(fim==false)
		if(partidaAtual.acabou==false){
			partidaAtual.finalizarPartida();
			dao.altera(partidaAtual);
			partidaAtual.getTimeA().getPartidas().add(partidaAtual);
			partidaAtual.getTimeB().getPartidas().add(partidaAtual);
			mostrarTabela();
			if(idPartida==partidas.size()){
				fim=true;
				System.out.println("FIM DE CAMPEONATO");
			}
		}
	}
	
	//retorna a proxima partida
	public Partida verifProxPartida(){
		
		if( idPartida == partidas.size() ){
			return null;
		}
		return partidas.get( (int)idPartida );
		
	}
	
	public Partida getPartidaAtual() {
		return partidaAtual;
	}
	
	void mostrarPartida(Partida p){
		System.out.println("Partida "+p.getId()+"o "+p.getHash()+" "+p.getTimeA().getNome()+" "+p.getTimeA().getId()+" x "+p.getTimeB().getNome()+" "+p.getTimeB().getId() );
	}
	
	public void mostrarPartidas(){
		for(int i=0; i<partidas.size();i++){
			mostrarPartida(partidas.get(i));
			
		}
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public boolean isIniciou() {
		return iniciou;
	}

	public void setIniciou(boolean iniciou) {
		this.iniciou = iniciou;
	}

	public boolean isFim() {
		return fim;
	}

	public void setFim(boolean fim) {
		this.fim = fim;
	}
	
	/*public void mostrarTabela(){
		tabela.mostrar();
	}*/
	
}
