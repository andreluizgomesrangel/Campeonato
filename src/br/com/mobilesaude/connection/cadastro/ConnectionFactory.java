package br.com.mobilesaude.connection.cadastro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {
        try {
            return DriverManager.getConnection(
            		"jdbc:mysql://localhost/cadastro", "root", "123456"); //jdbc:mysql://ip/nome_do_banco
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
}

