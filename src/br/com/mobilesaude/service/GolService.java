package br.com.mobilesaude.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.mobilesaude.cadastro.dao.GolDao;
import br.com.mobilesaude.source.Gol;

@Path("/ws/servico/gol")
public class GolService {

	
	@GET
	@Path("/teste")
	@Produces( MediaType.APPLICATION_XML)
	public Gol teste( ){
		Gol g = new Gol();
		 return g;
	}
	
	@GET
	@Path("/listar")
	@Produces( MediaType.APPLICATION_XML)
	public List<Gol> getLista( ){
		List<Gol> gols = new ArrayList<Gol>();
		GolDao dao = new GolDao();
		gols = dao.getLista();
		 return gols;
	}
	
	@GET
	@Path("/inserir")
	@Produces( MediaType.APPLICATION_XML)
	public List<Gol> inserir( @QueryParam("artilheiro") String nome,
							  @QueryParam("idTime") long idTime,
							  @QueryParam("idPartida") long idPartida ){
		List<Gol> gols = new ArrayList<Gol>();
		GolDao dao = new GolDao();
		
		Gol gol = new Gol();
		gol.setNomeArtilheiro(nome);
		gol.setIdTime(idTime);
		gol.setIdPartida(idPartida);
		
		dao.adiciona(gol);
		
		gols = dao.getLista();
		 return gols;
	}
}
