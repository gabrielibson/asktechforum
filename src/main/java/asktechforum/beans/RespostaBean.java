package asktechforum.beans;

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

	public RespostaBean(){
		this.fachada = Fachada.getInstance();
	}
	
	public String consultarRepostasPergunta(){
		this.pergunta = this.fachada.fachadaConsultarPerguntaPorIdPergunta(this.idPergunta);
		return "";
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
	
	
}
