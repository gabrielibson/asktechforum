package asktechforum.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

//import asktechforum.dominio.Pergunta;
import asktechforum.dominio.Resposta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.dominio.Usuario;
import asktechforum.fachada.Fachada;
import asktechforum.util.Util;

@ManagedBean(name="respostaBean")
@SessionScoped
public class RespostaBean {
	private int idPergunta;
	private Resposta resposta;
	private ResultConsultarPergunta pergunta;
	private Fachada fachada;
	private ArrayList<Resposta> listRespostas;
	private HtmlDataTable dataTableListRespostas;
	private Boolean sucessoCadastro;
	private String autor;
	private String msgErro;
	

	public RespostaBean(){
		this.fachada = Fachada.getInstance();
		this.listRespostas = new ArrayList<Resposta>();
		this.sucessoCadastro = false;
		this.msgErro = "";
	}
	
	public String consultarRepostasPergunta(){
		this.listRespostas = fachada.fachadaConsultarRespostaPorPergunta(pergunta.getIdPergunta());
		return "consultarRespostas";
	}
	
	public String responderPergunta(){
		this.limpar();
		return "responderPerguntaPage";
	}
	
	public String cadastrarResposta(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
				getExternalContext().getSession(true);
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		this.resposta.setStrData(Util.getDataSistema());
		this.resposta.setStrHora(Util.getHoraSistema());
		this.resposta.setIdPergunta(this.pergunta.getIdPergunta());
		this.resposta.setIdUsuario(usuarioLogado.getIdUsuario());
		
		String retorno = this.fachada.fachadaAdicionarResposta(resposta);
		
		if (retorno != null
				&& !retorno.equals("cadastroSucesso")) {
			this.msgErro = retorno;
		} else {
			this.sucessoCadastro = true;
		}
		
		return "responderPerguntaPage";
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
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String curtirResposta(){
		this.fachada.fachadaAdicionarVotoResposta(this.resposta.getIdResposta());
		this.fachada.fachadaAdicionarVotoUsuario(this.resposta.getIdUsuario(), this.resposta.getIdResposta());
		return this.consultarRepostasPergunta();
	}
	
	public String descurtirResposta(){
		fachada.fachadaRemoverVotoResposta(this.resposta.getIdResposta());
		fachada.fachadaDeletarUsuarioVoto(this.resposta.getIdUsuario(), this.resposta.getIdResposta());
		return this.consultarRepostasPergunta();
	}
	
	public void limpar(){
		this.resposta = new Resposta();
		this.sucessoCadastro = false;
		this.msgErro = "";
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

	public ResultConsultarPergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(ResultConsultarPergunta pergunta) {
		this.pergunta = pergunta;
	}

	public ArrayList<Resposta> getListRespostas() {
		return listRespostas;
	}
	
	public Boolean getSucessoCadastro() {
		return sucessoCadastro;
	}

	public void setSucessoCadastro(Boolean sucessoCadastro) {
		this.sucessoCadastro = sucessoCadastro;
	}

	public String getMsgErro() {
		return msgErro;
	}

	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}
	
	public void setListRespostas(ArrayList<Resposta> listRespostas) {
		this.listRespostas = listRespostas;
	}

	public boolean isCurtiu() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
				getExternalContext().getSession(true);
		Usuario usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
		Resposta resposta = (Resposta) this.dataTableListRespostas.getRowData();
		Boolean curtiu = this.fachada.fachadaConsultarUsuarioVoto(usuarioLogado.getIdUsuario(), resposta.getIdResposta());
		if(null != curtiu && curtiu){
			return false;
		}
		return true;
	}

	public HtmlDataTable getDataTableListRespostas() {
		return dataTableListRespostas;
	}

	public void setDataTableListRespostas(HtmlDataTable dataTableListRespostas) {
		this.dataTableListRespostas = dataTableListRespostas;
	}
	
	
}
