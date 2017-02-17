package br.com.mobilesaude.service;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.cadastro.dao.PartidaDao;
import br.com.mobilesaude.cadastro.dao.TimeDao;
import br.com.mobilesaude.source.Partida;
import br.com.mobilesaude.source.Time;


@SuppressWarnings({ "unused" })
@Path("/ws/servico/partida")
public class PartidaService {

	@GET
	@Path("/teste")
	@Produces( MediaType.APPLICATION_XML)
	public Partida teste( ){
		 PartidaDao dao = new PartidaDao();
		 TimeDao dao2 = new TimeDao();
		 List<Time> times = dao2.getLista();
		 List<Partida> lista = dao.getLista(times);
		return lista.get(0);
	}
	
	@GET
	@Path("/listar")
	@Produces( MediaType.APPLICATION_XML)
	public List<Partida> getLista( ){
		 PartidaDao dao = new PartidaDao();
		 TimeDao dao2 = new TimeDao();
		 List<Time> times = dao2.getLista();
		 List<Partida> lista = dao.getLista(times);
		return lista;
	}
	
	/**
	 * http://localhost:8080/Campeonato/ws/servico/partida/alterar?id=1&idTimeA=100&idTimeB=200&placarA=9&placarB=8&acabou=true
	 * @return
	 */
	@GET
	@Path("/alterar")
	@Produces( MediaType.APPLICATION_XML)
	public List<Partida> alterar_queryParam( @QueryParam("id")      int id , 
									 		 @QueryParam("idTimeA") int idTimeA ,
									 		 @QueryParam("idTimeB") int idTimeB ,
									 		 @QueryParam("placarA") int placarA ,
									 		 @QueryParam("placarB") int placarB ,
									 		 @QueryParam("acabou")  boolean acabou){
		TimeDao dao2 = new TimeDao();
		List<Time> times = dao2.getLista();
		Time tA = new Time();
		Time tB = new Time();
		
		for(Time t : times){
			if(t.getId()==idTimeA){
				tA = t;
			}
		}
		for(Time t : times){
			if(t.getId()==idTimeB){
				tB = t;
			}
		}
		
		Partida p = new Partida();
		p.setId(id);
		p.setAcabou(acabou);
		p.setPlacarA(placarA);
		p.setPlacarB(placarB);
		p.setTimeA(tA);
		p.setTimeB(tB);
	
		PartidaDao dao = new PartidaDao();
		dao.altera(p);
		
		List<Partida> lista = dao.getLista(times);
		return lista;
	}
	
	
	/**
	 * http://localhost:8080/Campeonato/ws/servico/partida/insere?id=1&idTimeA=100&idTimeB=200&placarA=9&placarB=8&acabou=true
	 * @return
	 */
	@GET
	@Path("/insere")
	@Produces( MediaType.APPLICATION_XML)
	public List<Partida> insere_queryParam( 
	 		 								 @QueryParam("idTimeA") int idTimeA ,
									 		 @QueryParam("idTimeB") int idTimeB ,
									 		 @QueryParam("placarA") int placarA ,
									 		 @QueryParam("placarB") int placarB ,
									 		 @QueryParam("acabou")  boolean acabou ){
		TimeDao dao2 = new TimeDao();
		List<Time> times = dao2.getLista();
		Time tA = new Time();
		Time tB = new Time();
		
		for(Time t : times){
			if(t.getId()==idTimeA){
				tA = t;
			}
		}
		for(Time t : times){
			if(t.getId()==idTimeB){
				tB = t;
			}
		}
		
		PartidaDao dao = new PartidaDao();
		List<Partida> lista = dao.getLista(times);
		long id = lista.get( lista.size() - 1 ).getId() + 1;
		
		Partida p = new Partida();
		p.setId(id);
		p.setAcabou(acabou);
		p.setPlacarA(placarA);
		p.setPlacarB(placarB);
		p.setTimeA(tA);
		p.setTimeB(tB);
	
		dao.adiciona(p);
		
		lista = dao.getLista(times);
		return lista;
	}
}
