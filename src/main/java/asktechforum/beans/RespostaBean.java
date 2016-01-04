package asktechforum.beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Pergunta;
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
	private Boolean sucessoAlteracao;
	private boolean sucessoExclusaoResposta;
	private boolean sucessoExclusaoPergunta;
	private String autor;
	private String msgErro;
	private PerguntaBean perguntaBean;
	

	public RespostaBean(){
		this.fachada = Fachada.getInstance();
		this.listRespostas = new ArrayList<Resposta>();
		this.sucessoCadastro = false;
		this.sucessoAlteracao = false;
		this.sucessoExclusaoResposta = false;
		this.sucessoExclusaoPergunta = false;
		this.msgErro = "";
	}
	
	public String consultarRepostasPergunta(){
		this.listRespostas = fachada.fachadaConsultarRespostaPorPergunta(pergunta.getIdPergunta());
		for (Resposta resposta : listRespostas) {
			this.tratarData(resposta);
		}
		return "consultarRespostas";
	}

	private void tratarData(Resposta resposta){
		if(resposta.getData()!= null) {
			resposta.setStrData(fachada.fachadaFormatarDataSQL(resposta.getStrData().toString()));
		}
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
			this.fachada.fachadaNotificarContribuintesPerg(resposta.getIdPergunta(), resposta.getIdUsuario());
			this.sucessoCadastro = true;
		}
		
		return "responderPerguntaPage";
	}
	
	
	public String alterarResposta(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
				getExternalContext().getSession(true);
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
		this.resposta.setStrData(Util.getDataSistema());
		this.resposta.setStrHora(Util.getHoraSistema());
		this.resposta.setIdPergunta(this.pergunta.getIdPergunta());
		this.resposta.setIdUsuario(usuarioLogado.getIdUsuario());
		
		String retorno = this.fachada.fachadaAlterarResposta(resposta);
		
		if (retorno != null
				&& !retorno.equals("alteracaoSucesso")) {
			this.msgErro = retorno;
		} else {
			this.sucessoAlteracao = true;
		}
		
		return "alterarResposta";
	}
	
	public String alterarPergunta(){
		return "alterarPergunta";
	}
	
	public String excluirPergunta(){
		this.fachada.fachadaDeletarPergunta(this.pergunta.getIdPergunta());
		this.sucessoExclusaoPergunta = true;		
		this.perguntaBean = new PerguntaBean();
		perguntaBean.listarTodasPerguntas();
		return "consultarRespostas";
	}
	
	public String alterarRespostaPage(){
		return "alterarResposta";
	}
	
	public String voltarConsultaRespostaPage(){
		this.sucessoExclusaoResposta = false;
		return this.consultarRepostasPergunta();
	}
	
	public String consultarPerguntasPage(){
		return "consultarPerguntas";
	}
	
	public String consultarRespostaPage(){
		return "consultarRespostas";
	}
	
	public String excluirResposta(){
		this.fachada.fachadaDeletarResposta(this.resposta.getIdResposta());	
		this.sucessoExclusaoResposta = true;
		return "consultarRespostas";
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
		this.sucessoAlteracao =  false;
		this.sucessoExclusaoResposta = false;
		this.sucessoExclusaoPergunta = false;
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

	public Boolean isSucessoAlteracao() {
		return sucessoAlteracao;
	}

	public void setSucessoAlteracao(Boolean sucessoAlteracao) {
		this.sucessoAlteracao = sucessoAlteracao;
	}

	public boolean isSucessoExclusaoResposta() {
		return sucessoExclusaoResposta;
	}

	public void setSucessoExclusaoResposta(boolean sucessoExlusaoResposta) {
		this.sucessoExclusaoResposta = sucessoExlusaoResposta;
	}

	public boolean isSucessoExclusaoPergunta() {
		return sucessoExclusaoPergunta;
	}

	public void setSucessoExclusaoPergunta(boolean sucessoExclusaoPergunta) {
		this.sucessoExclusaoPergunta = sucessoExclusaoPergunta;
	}

	public PerguntaBean getPerguntaBean() {
		return perguntaBean;
	}

	public void setPerguntaBean(PerguntaBean perguntaBean) {
		this.perguntaBean = perguntaBean;
	}
	
	
}
