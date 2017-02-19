package br.com.mobilesaude.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.cadastro.dao.PartidaDao;
import br.com.mobilesaude.cadastro.dao.TimeDao;
import br.com.mobilesaude.source.Campeonato;
import br.com.mobilesaude.source.Partida;
import br.com.mobilesaude.source.Time;


@SuppressWarnings({ "unused" })
@Path("/ws/servico/campeonato")
public class CampeonatoService {

	@GET
	@Path("/teste")
	@Produces( MediaType.APPLICATION_XML)
	public Campeonato teste( ){
		Campeonato c = new Campeonato();
		 return c;
	}
	
	@GET
	@Path("/return")
	@Produces( MediaType.APPLICATION_XML)
	public Campeonato getCampeonato( ){
		TimeDao tdao = new TimeDao();
		PartidaDao pdao = new PartidaDao();
		
		Campeonato c = new Campeonato();
		List<Time> times = tdao.getLista();
		c.setTimes(times);
		
		if(times.size()!=0){
			c.setPartidas(pdao.getLista(c.getTimes()));
		}
		
		 return c;
	}
	
	
	@GET
	@Path("/iniciar")
	@Produces( MediaType.APPLICATION_XML)
	public Campeonato iniciar(  @QueryParam("rodadas") int rodadas  ){
		
		Campeonato c = new Campeonato();
		c.iniciar(rodadas);
	return c;
	}
}
