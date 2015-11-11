package asktechforum.negocio;

import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dominio.Resposta;
import asktechforum.dominio.Usuario;
import asktechforum.factory.FabricaDAO;
import asktechforum.factory.FactoryDataBase;
import asktechforum.interfaces.RepositorioResposta;


public class CadastroRespostaBC{

	private RepositorioResposta cadastro;
	private ArrayList<Resposta> lstResposta;


	public CadastroRespostaBC() {
		FabricaDAO fabrica = FactoryDataBase.getInstancia().criarFabrica("JDBC");
		this.cadastro = fabrica.criarDaoResposta();
		
	}

	public String adicionarResposta(Resposta resposta) {
		String msg = "";
		try {
			String msgErro = this.verificaCampos(resposta);
			 if(msgErro.equals("")){
				msg = cadastro.adicionarResposta(resposta);
			}else{
				msg =  msgErro;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	/** Método que envia email para todos os usuários que responderam uma pergunta e para o autor dessa pergunta.
	 * Isso acontece quando uma nova resposta é cadastrada.
	 * 
	 * 
	 * idUsuario é o id do usuário que acabou de responder. Fazer com isso o controle de não enviar ao última pessoa q respondeu a pergunta.
	 * @param idPergunta
	 */
	public void notificarContribuintesPerg(int idPergunta, int idUsuario){

		try {
			Usuario autorPergunta = cadastro.consultarAutorPergunta(idPergunta);
			ArrayList<Usuario> usuarios = cadastro.consultarContribuintesPergunta(idPergunta);

			Email email = new Email();

			if(idUsuario != autorPergunta.getIdUsuario()){
				email.sendEmailAutor(autorPergunta.getNome(), autorPergunta.getEmail(), autorPergunta.getPergunta().getTitulo());
			}

			for(int i = 0; i < usuarios.size(); i++){
				if(idUsuario != usuarios.get(i).getIdUsuario()){
					email.sendEmailAutor(usuarios.get(i).getNome(), usuarios.get(i).getEmail(), usuarios.get(i).getPergunta().getTitulo());
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deletarResposta(int id) {
		try {
			if (id == 0) {

			} else {
				cadastro.deletarResposta(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Resposta consultarRespostaPorIdResposta(int id)  {
		Resposta resposta = new Resposta();
		try {
			if (id == 0) {

			} else {
				resposta = cadastro.consultarRespostaPorIdResposta(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta;
	}

	public ArrayList<Resposta> consultarRespostaPorIdUsuario(int id)
			throws SQLException {
		lstResposta = new ArrayList<Resposta>();
		try {
			if (id == 0) {

			} else {
				lstResposta = cadastro.consultarRespostaPorIdUsuario(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstResposta;
	}

	public ArrayList<Resposta> consultarTodasRespostas() throws SQLException {
		lstResposta = new ArrayList<Resposta>();
		try {
			lstResposta = cadastro.consultarTodasRespostas();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstResposta;
	}

	public ArrayList<Resposta> consultarRespostaPorPergunta(int id) {
		lstResposta = new ArrayList<Resposta>();
		try {
			if (id == 0) {

			} else {
				lstResposta = cadastro.consultarRespostaPorPergunta(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstResposta;
	}

	public void adicionarVotoResposta(int id){
		try {
			cadastro.adcionarVotoResposta(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerVotoResposta(int id){
		try {
			cadastro.removerVotoResposta(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String alterarResposta(Resposta resposta){
		String msg = "";
		try {
			String msgErro = this.verificaCampos(resposta);
			 if(msgErro.equals("")){
				msg = cadastro.alterarResposta(resposta);
			}else{
				msg =  msgErro;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	private String verificaCampos(Resposta resposta){
		String retorno = "";
		if (resposta.getData() == null) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (resposta.getDescricao() == null || resposta.getDescricao().trim().equals("")) {
			retorno = "Preencha o campo 'Descrição' com dados válidos";
		} else if (resposta.getHora() == null) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (resposta.getIdPergunta() == 0) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		} else if (resposta.getIdUsuario() == 0) {
			retorno = "Erro no sistema. Tente novamente em instantes.";
		}
		return retorno;
	}

}
