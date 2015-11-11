package asktechforum.dataBase;

import java.sql.Connection;
import java.sql.SQLException;

import asktechforum.conection.ConexaoAbs;
import asktechforum.conection.ConexaoFactory;
import asktechforum.config.PropriedadesBancoLoader;

/**
 * Classe que implementa o padrao singleton
 *	servindo conexoes a quem precisar.
 * Tambem implementa o padrao proxy remoto para abstrair a forma como 
 * a conexao ao baanco eh feita, seja a conexao remota ou local.
 *	
 */
public class ConnectionUtil {
	private static ConnectionUtil instancia;
	private ConexaoAbs connection = null;
	private ConexaoFactory fabricaConexao;
	
	private ConnectionUtil(){
		fabricaConexao = new ConexaoFactory();
		this.iniciarConexao();
	}
	
	public void iniciarConexao(){
		this.connection = fabricaConexao.criarConexao(PropriedadesBancoLoader.CONEXAO_LOCAL);		
	}
	
	public Connection getConnection() {
		if (this.connection == null) {
			this.iniciarConexao();
		}

		try {
			// checando se a conexao ainda eh valida.
			if (!this.connection.getConexao().isValid(500)) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			this.iniciarConexao();
		}

		return this.connection.getConexao();
	}
	
	public static ConnectionUtil getInstancia() {
		if (ConnectionUtil.instancia == null) {
			instancia = new ConnectionUtil();
		}
		return instancia;
	}
	
}