package asktechforum.beans;

import javax.faces.bean.ManagedBean;

import asktechforum.dominio.Resposta;

@ManagedBean
public class RespostaBean {
	private int idPergunta;
	private Resposta resposta;

	public RespostaBean(){
		
	}
	
	public String consultarRepostasPergunta(){
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
	
	
}
