package asktechforum.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.fachada.Fachada;

@ManagedBean(name="respostaBean")
public class RespostaBean {
	private int idPergunta;
	private Resposta resposta;
	private Pergunta pergunta;
	private Fachada fachada;
	private ArrayList<Resposta> listRespostas;

	public RespostaBean(){
		this.fachada = Fachada.getInstance();
		this.listRespostas = new ArrayList<Resposta>();
	}
	
	public String consultarRepostasPergunta(){
		this.pergunta = this.fachada.fachadaConsultarPerguntaPorIdPergunta(this.idPergunta);
		this.listRespostas = fachada.fachadaConsultarRespostaPorPergunta(this.idPergunta);
		return "consultarRespostas";
	}
	
	public String responderPergunta(){
		return "";
	}
	
	public String alterarPergunta(){
		return "";
	}
	
	public String excluirPergunta(){
		return "";
	}
	
	public String alterarResposta(){
		return "";
	}
	
	public String excluirResposta(){
		return "";
	}
	
	public String curtirResposta(){
		this.fachada.fachadaAdicionarVotoResposta(this.resposta.getIdResposta());
		this.fachada.fachadaAdicionarVotoUsuario(this.resposta.getIdUsuario(), this.resposta.getIdResposta());
		return "curtirDescurtirResposta";
	}
	
	public String descurtirResposta(){
		fachada.fachadaRemoverVotoResposta(this.resposta.getIdResposta());
		fachada.fachadaDeletarUsuarioVoto(this.resposta.getIdUsuario(), this.resposta.getIdResposta());
		return "curtirDescurtirResposta";
	}
	
	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public int getIdPergunta() {
		return idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public ArrayList<Resposta> getListRespostas() {
		return listRespostas;
	}

	public void setListRespostas(ArrayList<Resposta> listRespostas) {
		this.listRespostas = listRespostas;
	}

	public boolean isCurtiu() {
		return this.fachada.fachadaConsultarUsuarioVoto(this.resposta.getIdUsuario(), this.resposta.getIdResposta());
	}
	
	
}
