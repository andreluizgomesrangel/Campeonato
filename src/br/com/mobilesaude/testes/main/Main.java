package br.com.mobilesaude.testes.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import br.com.mobilesaude.cadastro.dao.TimeDao;
import br.com.mobilesaude.connection.cadastro.ConnectionFactory;
import br.com.mobilesaude.source.Campeonato;
import br.com.mobilesaude.source.Partida;
import br.com.mobilesaude.source.Tabela;
import br.com.mobilesaude.source.Time;

public class Main {

	//ip: 192.168.25.4
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Campeonato c = new Campeonato();
		c.iniciar(2);
		
		
		c.iniciarPartida();
		c.golB();c.golA();c.golA();c.golA();c.golB();c.golB();c.golA();c.golA();
		c.finalizarPartida();
		
		//timedao.altera(t1);
		
	}

}
