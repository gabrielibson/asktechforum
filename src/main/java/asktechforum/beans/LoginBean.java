package asktechforum.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Usuario;
import asktechforum.fachada.Fachada;


@ManagedBean(name="loginMB")
@SessionScoped
public class LoginBean {
	String login;
	String senha;
	Usuario usuarioLogado = null;
	boolean emailEnviado;
	
	public LoginBean(){
		this.emailEnviado = false;
	}
	
	public String logar(){
		String retorno = "";
		Fachada fachada = Fachada.getInstance();
		Usuario user = null;
		
		user = fachada.fachadaConsultarUsuarioPorEmail(this.login);
		if(user == null){
			FacesContext.getCurrentInstance().addMessage("login", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "msg_summay", "Email não existe, favor cadastre-se"));
		}else {
			user = null;
			user = fachada.fachadaConsultarUsuarioPorEmail_Senha(this.login, this.senha);
			if(user == null){
				FacesContext.getCurrentInstance().addMessage("senha", 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "msg_summay", "Senha incorreta"));
			}
			else{
				this.usuarioLogado = user;
				retorno = "passou";
			}
		}		
		
		return retorno;
	}
	
	public String sair(){
		this.usuarioLogado = null;
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
		return "indexPage";
	}
	
	public String enviarEmailSenha(){
		Fachada fachada = Fachada.getInstance();
		//UsuarioBC consultaUsuario = new UsuarioBC();
		Usuario usuario = fachada.fachadaConsultarUsuarioPorEmail(this.login);
		String retorno = "";
		if (usuario == null) {
			this.emailEnviado = false;
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "msg_summay", 
							"O e-mail informado não está cadastrado no Ask TechForum."));
			retorno = "emailNaoCadastrado";

		} else {
			fachada.sendEmailEsqueciSenha(usuario);
			this.emailEnviado = true;
			retorno = "senhaEnviada";
		}
		return retorno;
	}

	public String esqueciSenha(){
		this.limpar();
		return "esqueciMinhaSenha";
	}
	
	public void limpar(){
		this.login = "";
		this.emailEnviado = false;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public boolean isEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}
	
	
}
