package asktechforum.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.factory.FabricaDAO;
import asktechforum.factory.FactoryDataBase;
import asktechforum.interfaces.RepositorioPergunta;
import asktechforum.proxy.TagProxy;

public class CadastroPerguntaBC implements RepositorioPergunta {

	private RepositorioPergunta perguntaDAO;
	private ArrayList<Pergunta> lstPergunta;
	private ArrayList<ResultConsultarPergunta> lstQtdPergunta;
	private RepositorioPergunta tagProxy;

	public CadastroPerguntaBC() {
		FabricaDAO fabrica = FactoryDataBase.getInstancia().criarFabrica("JDBC");
		this.perguntaDAO = fabrica.criarDaoPergunta();
		this.tagProxy = TagProxy.getTagProxy();
	}

	@Override
	public String adcionarPergunta(Pergunta pergunta)  {
		String msg = "";
		try {
			String msgErro = this.verificaCampos(pergunta);
			if (msgErro.length()==0) {
				msg= perguntaDAO.adcionarPergunta(pergunta);
			}else{
				msg= msgErro;
			}
			          
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;

	}

	@Override
	public void deletarPergunta(int id) {
		try {
			if (id == 0) {

			} else {
				perguntaDAO.deletarPergunta(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Pergunta consultarPerguntaPorIdPergunta(int id) {

		Pergunta pergunta = new Pergunta();
		try {
			if (id == 0) {

			} else {
				pergunta = perguntaDAO.consultarPerguntaPorIdPergunta(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pergunta;
	}

	@Override
	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id)
			throws SQLException {
		this.lstPergunta = new ArrayList<Pergunta>();
		try {

			if (id == 0) {

			} else {
				this.lstPergunta = perguntaDAO.consultarPerguntaIdUsuario(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lstPergunta;
	}
	
	
	public ArrayList<String> consultaTodasAsTags() {
		ArrayList<String> tags = new ArrayList<String>();
		ArrayList<String> tagFiltradas = new ArrayList<String>();
		try {
			//Faz uso do padrao proxy virtual para controlar quando a consulta de 
			// tags será realizada para popular a lista de tags.
		   tags = this.tagProxy.consultaTodasAsTags();
		   
		   tagFiltradas.add(tags.get(0));
		   boolean ehIgual = false;
		   for(int i=1; i < tags.size(); i++){
			   for(int j=0; j < tagFiltradas.size(); j++){
				   if(tagFiltradas.get(j).equals(tags.get(i))){
					   ehIgual = true;
				   }
			   }	
			   if(!ehIgual){
				   tagFiltradas.add(tags.get(i));
			   }
			   ehIgual = false;
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagFiltradas;
		
	}
	
	@Override
	public ArrayList<Pergunta> consultarTodasPerguntas() {
		lstPergunta = new ArrayList<Pergunta>();
		try {

			this.lstPergunta = perguntaDAO.consultarTodasPerguntas();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstPergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag){
		lstQtdPergunta = new ArrayList<ResultConsultarPergunta>();
		try {
			if(tag.equals("all")){
				this.lstQtdPergunta = perguntaDAO.consultarPerguntaPorTodasTags();
			}else{
				this.lstQtdPergunta = perguntaDAO.consultarPerguntaPorTag(tag);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstQtdPergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags()
			throws SQLException {
		return null;
	}

	@Override
	public String alterarPergunta(Pergunta pergunta)
			 {
		String msg = "";
		try {
			String msgErro = this.verificaCampos(pergunta);
			if (msgErro.equals("")) {
				msg = perguntaDAO.alterarPergunta(pergunta);
			}else{
				msg = msgErro;
			}
			          
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return msg;
		
	}
	
	private String verificaCampos(Pergunta pergunta){
		String retorno = "";
		if (pergunta.getData() == null) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (pergunta.getDescricao() == null ||  pergunta.getDescricao().trim().equals("")) {
			retorno = "Preencha o campo 'Descrição' com dados válidos";
		} else if (pergunta.getHora() == null) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (pergunta.getTitulo() == null ||  pergunta.getTitulo().trim().equals("")) {
			retorno = "Preencha o campo 'Pergunta' com dados válidos";
		} else if (pergunta.getIdUsuario() == 0) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (pergunta.getTag() == null || pergunta.getTag().trim().equals("")) {
			retorno = "Preencha o campo 'Assuntos relacionados' com dados válidos";
		}	
		return retorno;
		
	}
}
