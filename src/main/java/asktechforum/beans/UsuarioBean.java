package asktechforum.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import asktechforum.dominio.Usuario;
import asktechforum.fachada.Fachada;

@ManagedBean(name="userBean")
@SessionScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String confSenha;
	private String senhaNova;
	private String senhaAtual;
	private Fachada fachada;
	private boolean cadastrado;
	private String dataNascimentoStr;
	
	public UsuarioBean() {
		this.usuario = new Usuario();
		this.fachada = Fachada.getInstance();
	}
	
	public String cadastrarUsuario(){
		this.fachada = Fachada.getInstance();
		this.cadastrado = false;
		int quantAdmin = this.fachada.fachadaConsultarQuantidadeAdmin(this.usuario);
		boolean flag = true;

		if(!this.fachada.fachadaVerificarEmail(this.usuario.getEmail(), this.usuario)) {	
			if(quantAdmin == 0) {
				this.usuario.setAdmin(true);
			}else {
				this.usuario.setAdmin(false);
			}
			
			flag = fachada.fachadaAdicionarUsuario(this.usuario);
			
			if(flag){
				this.cadastrado = true;
				this.usuario = new Usuario();
				return "sucessoCadastro";
			}else{
				return "erroCadastro";
			}
		}else{
			FacesContext.getCurrentInstance().addMessage("", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "msg_summay", "Este Email já está cadastrado no AskTechForum."));
			return "erroCadastro";
		}
	}
	
	public String chamarCadastroUsuario(){
		limpar();
		return "cadastroUsuarioPage";
	}
	
	public void limpar(){
		this.usuario = new Usuario();
		this.cadastrado = false;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public String getConfSenha() {
		return confSenha;
	}
	public void setConfSenha(String confSenha) {
		this.confSenha = confSenha;
	}
	public String getSenhaNova() {
		return senhaNova;
	}
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public boolean isCadastrado() {
		return cadastrado;
	}

	public void setCadastrado(boolean cadastrado) {
		this.cadastrado = cadastrado;
	}

	public String getDataNascimentoStr() {
		return dataNascimentoStr;
	}

	public void setDataNascimentoStr(String dataNascimentoStr) {
		this.dataNascimentoStr = dataNascimentoStr;
	}
	
	
	//TO DO:
	// Metodos: Validar, Consultar, LimpaCampos

}
