package asktechforum.factory;

import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.interfaces.RepositorioResposta;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.interfaces.RepositorioVoto;

/**
 * Interface que uma fabrica deve
 * implementar para criar os daos solicitados.
 * @author diego.ferreira
 *
 */
public interface FabricaDAO {
	
	/**
	 * 	Cria um repositorio de Voto
	 * @return
	 */
	public RepositorioVoto criarDaoVoto();
	
	
	/**
	 * 	Cria um repositorio de Pergunta.
	 * @return
	 */
	public RepositorioPergunta criarDaoPergunta();
	
	
	/**
	 * Cria um repositorio de Resposta
	 * @return
	 */
	public RepositorioResposta criarDaoResposta();
	
	
	/**
	 * Cria um repositorio de Usuario.
	 * @return
	 */
	public RepositorioUsuario criarDaoUsuario();

}
