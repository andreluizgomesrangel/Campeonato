package br.com.mobilesaude.cadastro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mobilesaude.connection.cadastro.ConnectionFactory;
import br.com.mobilesaude.source.Partida;
import br.com.mobilesaude.source.Time;

public class PartidaDao {

	// a conexão com o banco de dados
	   private Connection connection;
	 
	   public PartidaDao() {
	     this.connection = new ConnectionFactory().getConnection();
	   }
	   
	   public void adiciona(Partida partida) {
		     String sql = "insert into partida " +
		             "(id,idTimeA,idTimeB,placarA,placarB,acabou)" +
		             " values (?,?,?,?,?,?)";
		     try {
		         // prepared statement para inserção
		         PreparedStatement stmt = connection.prepareStatement(sql);
		 
		         // seta os valores
		         stmt.setLong(1,partida.getId());
		         stmt.setLong(2,partida.getTimeA().getId());
		         stmt.setLong(3,partida.getTimeB().getId());
		         stmt.setInt(4,partida.getPlacarA());
		         stmt.setInt(5,partida.getPlacarB());
		         stmt.setBoolean(6,partida.isAcabou());
		         
		         // executa
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public List<Partida> getLista(List<Time> times) {
		   
		     try {
		         List<Partida> partidas = new ArrayList<Partida>();
		         PreparedStatement stmt = this.connection.
		        		 
		         prepareStatement("select * from partida");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		             Partida partida = new Partida();
		             partida.setId(rs.getLong("id"));
		             
		             partida.setPlacarA(rs.getInt("placarA"));
		             partida.setPlacarB(rs.getInt("placarB"));
		             
		             Time timeA, timeB;
		             long idA, idB;
		             idA = rs.getLong("idTimeA");
		             idB = rs.getLong("idTimeB");
		             long hash = idA+16*idB;
		             partida.setHash(hash);
		             
		             timeA = buscaTime(idA, times);
		             timeB = buscaTime(idB, times);
		             
		             if(timeA==null || timeB==null){
		            	 System.out.println("times nao encontrados!");
		             }
		             else{
		            	 partida.setTimeA(timeA);
		            	 partida.setTimeB(timeB);
		             }
		             
		             partida.setAcabou(rs.getBoolean("acabou"));
		             
		             // montando a data através do Calendar
		             /*Calendar data = Calendar.getInstance();
		             data.setTime(rs.getDate("dataNascimento"));
		             contato.setDataNascimento(data);*/
		 
		             // adicionando o objeto à lista
		             partidas.add(partida);
		         }
		         rs.close();
		         stmt.close();
		         return partidas;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public void altera(Partida partida) {
		     String sql = "update partida set idTimeA=?, idTimeB=?, placarA=?, placarB=?, " +
		             "acabou=? where id=?";
		     try {
		         PreparedStatement stmt = connection.prepareStatement(sql);
		         
		         stmt.setLong(1, partida.getTimeA().getId());
		         stmt.setLong(2, partida.getTimeB().getId());
		         stmt.setInt(3, partida.getPlacarA());
		         stmt.setInt(4, partida.getPlacarB());
		         stmt.setBoolean(5, partida.isAcabou());
		         stmt.setLong(6, partida.getId());
		         
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public void truncate() {
		     String sql = "TRUNCATE TABLE cadastro.partida";
		             
		     try {
		         PreparedStatement stmt = connection.prepareStatement(sql);
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public Time buscaTime(long id, List<Time> times){
			for(Time t : times){
				if(t.getId()==id){
					return t;
				}
			}
			return null;
		}
}
