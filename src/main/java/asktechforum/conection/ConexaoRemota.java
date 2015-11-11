package asktechforum.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import asktechforum.config.PropriedadesBancoLoader;

public class ConexaoRemota extends ConexaoAbs {
	private Connection conexao;
	
	public ConexaoRemota() {
		super();
		this.conectar();
	}

	@Override
	public void conectar() {
		try {
			String driver = PropriedadesBancoLoader.BANCO_DRIVER;
			String url = PropriedadesBancoLoader.BANCO_URL_REMOTA;
			String user = PropriedadesBancoLoader.BANCO_USER_NAME;
			String password = PropriedadesBancoLoader.BANCO_PASSWD;
			Class.forName(driver);
			this.conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Connection getConexao() {
		return this.conexao;
	}
	
}
