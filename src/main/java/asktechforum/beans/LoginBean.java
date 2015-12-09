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
	
	public String logar(){
		String retorno = "";
		Fachada fachada = Fachada.getInstance();
		Usuario user = null;
		
		user = fachada.fachadaConsultarUsuarioPorEmail(this.login);
		if(user == null){
			FacesContext.getCurrentInstance().addMessage("email", 
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
	
	
}
