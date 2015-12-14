package asktechforum.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.dominio.Usuario;
import asktechforum.fachada.Fachada;
import asktechforum.util.Util;

@ManagedBean(name="perguntaBean")
@SessionScoped
public class PerguntaBean {
	private Fachada fachada = Fachada.getInstance();
	private Pergunta pergunta;
	private boolean perguntaCadastrada;
	private List<ResultConsultarPergunta> listPerguntas;
	private String tag;

	public PerguntaBean(){
		this.listPerguntas = new ArrayList<ResultConsultarPergunta>();
		this.pergunta = new Pergunta();
		this.listarTodasPerguntas();
	}
	
	public String inserirPergunta(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		this.pergunta.setStrData(Util.getDataSistema());
		this.pergunta.setStrHora(Util.getHoraSistema());
		this.pergunta.setIdUsuario(usuario.getIdUsuario());
		String retornoCadastroPergunta = this.fachada.fachadaAdcionarPergunta(pergunta);

		if (retornoCadastroPergunta != null
				&& !retornoCadastroPergunta.equals("cadastroSucesso")) {
			this.perguntaCadastrada = false;
		} else {
			this.perguntaCadastrada = true;
		}

		return "cadastroPergunta";
	}
	
	public String consultarPerguntaPorTag(){
		return "";
	}
	
	public String listarTodasPerguntas(){
		this.listPerguntas = fachada.fachadaConsultarPerguntaPorTag("all");
		return "/jsf/consultaPerguntasPorTag";
	}
	
	public String cadastrarPergunta(){
		this.limpar();
		return "cadastroPergunta";
	}
	
	public void limpar(){
		this.perguntaCadastrada = false;
		this.pergunta = new Pergunta();
	}
	
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public boolean isPerguntaCadastrada() {
		return perguntaCadastrada;
	}

	public void setPerguntaCadastrada(boolean perguntaCadastrada) {
		this.perguntaCadastrada = perguntaCadastrada;
	}

	public List<ResultConsultarPergunta> getListPerguntas() {
		return listPerguntas;
	}

	public void setListPerguntas(List<ResultConsultarPergunta> listPerguntas) {
		this.listPerguntas = listPerguntas;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
