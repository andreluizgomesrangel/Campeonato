package br.com.mobilesaude.cadastro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mobilesaude.connection.cadastro.ConnectionFactory;
import br.com.mobilesaude.source.Gol;
import br.com.mobilesaude.source.Time;

public class GolDao {

	private Connection connection;
	 
	   public GolDao() {
	     this.connection = new ConnectionFactory().getConnection();
	   }
	
	   public void adiciona(Gol gol) {
		   List<Gol> gols = getLista();
		   long id;
		   if(gols.size()==0){
			   id = 1;
		   }
		   else{
			   id = gols.get( gols.size() - 1 ).getId() + 1;
		   }
		   gol.setId(id);
		   
		     String sql = "insert into gol " +
		             "(id,nomeArtilheiro,idPartida,idTime)" +
		             " values (?,?,?,?)";
		     try {
		         // prepared statement para inserção
		         PreparedStatement stmt = connection.prepareStatement(sql);
		 
		         // seta os valores
		         stmt.setLong(1,gol.getId());
		         stmt.setNString(2,gol.getNomeArtilheiro());
		         stmt.setLong(3,gol.getIdPartida());
		         stmt.setLong(4,gol.getIdTime());
		         
		         // executa
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public List<Gol> getLista() {
		   
		     try {
		         List<Gol> gols = new ArrayList<Gol>();
		         PreparedStatement stmt = this.connection.
		        		 
		         prepareStatement("select * from gol");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		             Gol gol = new Gol();
		             gol.setId(rs.getLong("id"));
		             gol.setNomeArtilheiro(rs.getString("nomeArtilheiro"));
		             gol.setIdTime(rs.getLong("id"));
		             gol.setIdPartida(rs.getLong("id"));
		             
		             // montando a data através do Calendar
		             /*Calendar data = Calendar.getInstance();
		             data.setTime(rs.getDate("dataNascimento"));
		             contato.setDataNascimento(data);*/
		 
		             // adicionando o objeto à lista
		             gols.add(gol);
		         }
		         rs.close();
		         stmt.close();
		         return gols;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
}
