package asktechforum.factory;

import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.interfaces.RepositorioResposta;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.interfaces.RepositorioVoto;
import asktechforum.repositorio.jdbc.RepositorioPerguntasJDBC;
import asktechforum.repositorio.jdbc.RepositorioRespostaJDBC;
import asktechforum.repositorio.jdbc.RepositorioUsuarioJDBC;
import asktechforum.repositorio.jdbc.RepositorioVotoJDBC;

/**
 * Classe que implementa o padrao Abstract Factory
 * para criar objetos do tipo repositorio que sejam
 * da familia do JDBC.
 * 
 * @author diego.ferreira
 *
 */
public class FabricaJDBC implements FabricaDAO {

	@Override
	public RepositorioVoto criarDaoVoto() {
		 RepositorioVotoJDBC repositorio = new RepositorioVotoJDBC();
		 
		return repositorio;
	}

	@Override
	public RepositorioPergunta criarDaoPergunta() {
		RepositorioPerguntasJDBC repositorio = new RepositorioPerguntasJDBC();
		
		return repositorio;
	}

	@Override
	public RepositorioResposta criarDaoResposta() {
		RepositorioRespostaJDBC repositorio = new RepositorioRespostaJDBC();
		
		return repositorio;
	}

	@Override
	public RepositorioUsuario criarDaoUsuario() {
		RepositorioUsuarioJDBC repositorio = new RepositorioUsuarioJDBC();
		
		return repositorio;
	}

}
