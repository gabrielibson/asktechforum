package asktechforum.proxy;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.factory.FabricaDAO;
import asktechforum.factory.FactoryDataBase;
import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.repositorio.jdbc.RepositorioPerguntasJDBC;

/**
 * Classe que implementa o Proxy virtual para o acesso as tags do Forum
 * 
 * @author Barbara
 *
 */
public class TagProxy extends RepositorioPerguntasJDBC{

	private static TagProxy instance;
	private ArrayList<String> lista;
	private FabricaDAO fabrica;
	private RepositorioPergunta perguntaDAO;

	private TagProxy() {
		this.fabrica = FactoryDataBase.getInstancia().criarFabrica("JDBC");
		this.perguntaDAO = fabrica.criarDaoPergunta();
		this.lista = new ArrayList<String>();
	}

	public static TagProxy getTagProxy() {
		if (instance == null) {
			instance = new TagProxy();
		}
		return instance;
	}

	@Override
	public ArrayList<String> consultaTodasAsTags() throws SQLException{
		if(this.lista == null || this.lista.isEmpty()){
			this.lista = this.perguntaDAO.consultaTodasAsTags();
		}
		return this.lista;
	}
}
