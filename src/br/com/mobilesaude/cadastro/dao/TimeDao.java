package br.com.mobilesaude.cadastro.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mobilesaude.connection.cadastro.ConnectionFactory;
import br.com.mobilesaude.source.Time;

//classe qeu acessa o banco de dados de times
public class TimeDao {

	// a conexão com o banco de dados
	   private Connection connection;
	 
	   public TimeDao() {
	     this.connection = new ConnectionFactory().getConnection();
	   }
	
	   public void adiciona(Time time) {
		     String sql = "insert into time " +
		             "(id,nome,pontos,jogos,vitorias,empates,derrotas,gp,gc,gs,rendimento,jogador)" +
		             " values (?,?,?,?,?,?,?,?,?,?,?,?)";
		     try {
		         // prepared statement para inserção
		         PreparedStatement stmt = connection.prepareStatement(sql);
		 
		         // seta os valores
		         stmt.setLong(1,time.getId());
		         stmt.setString(2,time.getNome());
		         stmt.setInt(3,time.getPontos());
		         stmt.setInt(4,time.getJogos());
		         stmt.setInt(5,time.getVitorias());
		         stmt.setInt(6,time.getEmpates());
		         stmt.setInt(7,time.getDerrotas());
		         stmt.setInt(8,time.getGp());
		         stmt.setInt(9,time.getGc());
		         stmt.setInt(10,time.getGs());
		         stmt.setDouble(11,time.getRendimento());
		         stmt.setString(12,time.getJogador());
		         
		         // executa
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public List<Time> getLista() {
		   
		     try {
		         List<Time> times = new ArrayList<Time>();
		         PreparedStatement stmt = this.connection.
		        		 
		         prepareStatement("select * from time");
		         ResultSet rs = stmt.executeQuery();
		 
		         while (rs.next()) {
		             // criando o objeto Contato
		             Time time = new Time();
		             time.setId(rs.getLong("id"));
		             time.setNome(rs.getString("nome"));
		             time.setPontos(rs.getInt("pontos"));
		             time.setJogos(rs.getInt("jogos"));
		             time.setVitorias(rs.getInt("vitorias"));
		             time.setEmpates(rs.getInt("empates"));
		             time.setDerrotas(rs.getInt("derrotas"));
		             time.setGp(rs.getInt("gp"));
		             time.setGc(rs.getInt("gc"));
		             time.setGs(rs.getInt("gs"));
		             time.setRendimento(rs.getDouble("rendimento"));
		             time.setJogador(rs.getString("jogador"));
		             
		             // montando a data através do Calendar
		             /*Calendar data = Calendar.getInstance();
		             data.setTime(rs.getDate("dataNascimento"));
		             contato.setDataNascimento(data);*/
		 
		             // adicionando o objeto à lista
		             times.add(time);
		         }
		         rs.close();
		         stmt.close();
		         return times;
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
	   public void altera(Time time) {
		     String sql = "update time set nome=?, pontos=?, jogos=?, vitorias=?, empates=?, derrotas=?, gp=?, gc=?, gs=?, rendimento=?, " +
		             "jogador=? where id=?";
		     try {
		         PreparedStatement stmt = connection.prepareStatement(sql);
		         stmt.setString(1, time.getNome());
		         stmt.setInt(2, time.getPontos());
		         stmt.setInt(3, time.getJogos());
		         stmt.setInt(4, time.getVitorias());
		         stmt.setInt(5, time.getEmpates());
		         stmt.setInt(6, time.getDerrotas());
		         stmt.setInt(7, time.getGp());
		         stmt.setInt(8, time.getGc());
		         stmt.setInt(9, time.getGs());
		         stmt.setDouble(10, time.getRendimento());
		         stmt.setString(11, time.getJogador());
		         stmt.setLong(12, time.getId());
		         stmt.execute();
		         stmt.close();
		     } catch (SQLException e) {
		         throw new RuntimeException(e);
		     }
		 }
	   
}
