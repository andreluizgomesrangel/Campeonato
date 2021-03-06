package br.com.mobilesaude.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.cadastro.dao.TimeDao;
import br.com.mobilesaude.source.Time;


@Path("/ws/servico/time")
public class TimeService {

	List<Time> times;
	
	@GET
	@Path("/teste")
	@Produces( MediaType.APPLICATION_XML)
	public Time teste( ){
		Time t = new Time();
		 return t;
	}
	
	@GET
	@Path("/listar")
	
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> getLista( ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		return lista;
	}
	
	/**
	 * http://localhost:8080/Campeonato/ws/servico/time/alterar?id=10&nome=alterado&pontos=500&jogos=100&vitorias=1000&empates=1111&derrotas=999&gp=10000&gc=98989&gs=1112&rendimento=1313&jogador=dedede
	 * @return
	 */
	@GET
	@Path("/alterar")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> alterar_queryParam(   @QueryParam("id") int id , 
									 		@QueryParam("nome") String nome ,
									 		@QueryParam("pontos") int pontos , 
									 		@QueryParam("jogos") int jogos ,
									 		@QueryParam("vitorias") int vitorias ,
									 		@QueryParam("empates") int empates ,
									 		@QueryParam("derrotas") int derrotas ,
									 		@QueryParam("gp") int gp ,
									 		@QueryParam("gc") int gc ,
									 		@QueryParam("gs") int gs ,
									 		@QueryParam("jogador") String jogador ){
		Time t = new Time(id, nome, pontos, jogos, vitorias, empates, derrotas, gp, gc, gs);
		t.setRendimento(rendimento(jogos,pontos));
		t.setJogador(jogador);
		TimeDao dao = new TimeDao();
		dao.altera(t);
		List<Time> lista = dao.getLista();
		return lista;
	}
	
	/**
	 * http://localhost:8080/Campeonato/ws/servico/time/inserir?id=10&nome=alterado&pontos=500&jogos=100&vitorias=1000&empates=1111&derrotas=999&gp=10000&gc=98989&gs=1112&rendimento=1313&jogador=dedede
	 * @return
	 */
	@GET
	@Path("/inserir")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> inserir_queryParam(   
									 		@QueryParam("nome") String nome ,
									 		@QueryParam("pontos") int pontos , 
									 		@QueryParam("jogos") int jogos ,
									 		@QueryParam("vitorias") int vitorias ,
									 		@QueryParam("empates") int empates ,
									 		@QueryParam("derrotas") int derrotas ,
									 		@QueryParam("gp") int gp ,
									 		@QueryParam("gc") int gc ,
									 		@QueryParam("gs") int gs ,
									 		@QueryParam("rendimento") double rendimento ,
									 		@QueryParam("jogador") String jogador ){
		
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		long id;
		if(lista.size()==0) id=1;
		else {
			id = lista.get( lista.size() - 1 ).getId()+1;
		}
		
		Time t = new Time(id, nome, pontos, jogos, vitorias, empates, derrotas, gp, gc, gs);
		t.setRendimento(rendimento(jogos,pontos));
		t.setJogador(jogador);
		
		dao.adiciona(t);
		
		lista = dao.getLista();
		return lista;
	}
	
	double rendimento(int j, int pontos){
		if(j==0) return 0.0;
		return (pontos*100.0)/(3.0*j);
	}
	
	@GET
	@Path("/fazerGol")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> fazerGol( @QueryParam("id") int id ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		
		Time t = lista.get(id-1);
		t.fazerGol();
		dao.altera(t);
		return lista;
	}
	
	@GET
	@Path("/levarGol")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> levarGol( @QueryParam("id") int id ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		
		Time t = lista.get(id-1);
		t.levarGol();
		dao.altera(t);
		return lista;
	}
	
	@GET
	@Path("/vencer")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> vencer( @QueryParam("id") int id ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		
		Time t = lista.get(id-1);
		t.vencer();
		dao.altera(t);
		return lista;
	}
	
	@GET
	@Path("/empatar")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> empatar( @QueryParam("id") int id ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		
		Time t = lista.get(id-1);
		t.empatar();
		dao.altera(t);
		return lista;
	}
	
	@GET
	@Path("/perder")
	@Produces( MediaType.APPLICATION_XML)
	public List<Time> perder( @QueryParam("id") int id ){
		TimeDao dao = new TimeDao();
		List<Time> lista = dao.getLista();
		
		Time t = lista.get(id-1);
		t.perder();
		dao.altera(t);
		return lista;
	}
}

