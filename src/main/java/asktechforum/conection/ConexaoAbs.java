package asktechforum.conection;

import java.sql.Connection;

public abstract class ConexaoAbs {
	
	/**
	 * Metodo sem retorno responsavel
	 * por executar a conexao com o banco.
	 */
	public abstract void conectar();

	/**
	 *  Metodo que retorna uma conexao valida
	 *  com o banco.
	 *  
	 * @return Conexao com o banco.
	 */
	public abstract Connection getConexao();
}
