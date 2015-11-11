package asktechforum.conection;

import asktechforum.config.PropriedadesBancoLoader;

/**
 * 	Classe que implementa o padrao factory method
 * criando conexoes com a base de dados de acordo com o tipo de parametro fornecido (conexao local / remota).
 * 
 * @author Pamela
 *
 */
public class ConexaoFactory implements FabricaDeConexoes{
	private ConexaoAbs conexao;
	
	/**
	 * Metodo responsavel por criar um objeto conexao de acordo com o tipo fornecido por
	 * parametro.
	 * @param tipoConexao - Tipo de conexao a ser criado
	 * @return uma conexao do Tipo ConexaoAbs(classe pai das conexoes remota e local)
	 */
	public ConexaoAbs criarConexao(int tipoConexao){
		conexao = null;
		
		if(tipoConexao == PropriedadesBancoLoader.CONEXAO_LOCAL){
			conexao = new ConexaoLocal();
		}else if(tipoConexao == PropriedadesBancoLoader.CONEXAO_REMOTA){
			conexao = new ConexaoRemota();
		}else{
			System.out.println("Não foi possível criar conexão. Tipo de conexão inválido.");
		}
		
		return conexao;
	}
}
