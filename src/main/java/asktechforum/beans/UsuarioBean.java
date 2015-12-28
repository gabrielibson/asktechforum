package asktechforum.beans;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Usuario usuarioSelecionado;
	private String tipoPesquisa;
	private String msgErroTipoPesquisa;
	private String msgErroUsuarioNaoSelecionado;
	private boolean resultadoVazio;
	private String nomePesquisa;
	private String emailPesquisa;
	
	public UsuarioBean() {
		this.limpar();
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
	
	public String alterarUsuario(){
		return "alterarUsuario";
	}
	
	public String excluirUsuario(){
		return "";
	}
	
	public String chamarCadastroUsuario(){
		limpar();
		return "cadastroUsuarioPage";
	}
	
	public String pesquisarUsuario(){
		this.limpar();
		
		if(this.tipoPesquisa != null && this.tipoPesquisa.trim() != "") {
			switch (this.tipoPesquisa) {
			case "nome":
				if(this.nomePesquisa != null && this.nomePesquisa.trim() != "") {
					this.usuarios.addAll(fachada.fachadaConsultarUsuarioPorNome(this.nomePesquisa));
				}else{
					this.msgErroTipoPesquisa = "Informe o nome do usuário.";
				}
				break;
			case "email":
				if(this.emailPesquisa != null && this.emailPesquisa.trim() != "") {
					Usuario usuario = fachada.fachadaConsultarUsuarioPorEmail(this.emailPesquisa);
					if(usuario != null) {
						if(usuario.getIdUsuario() != 0) {
							this.usuarios.add(fachada.fachadaConsultarUsuarioPorEmail(usuario.getEmail()));
						}
					}
				}else{
					this.msgErroTipoPesquisa = "Informe o email do usuário.";
				}
				break;
			case "todos":
				this.usuarios.addAll(fachada.fachadaConsultarTodosUsuarios());
				break;
			case "":
				break;
			default:
				break;
			}
		}else{
			this.msgErroTipoPesquisa = "* Selecione uma das opções acima para pesquisar.";
		}
		
		if(this.msgErroTipoPesquisa.equals("") && this.usuarios.isEmpty()){
			this.resultadoVazio = true;
		}

		return "pesquisarUsuariosPage";
	}
	
	public String exibirPerfilUsuario(){
		
		String retorno = "";
		if(this.usuarioSelecionado == null){
			this.msgErroUsuarioNaoSelecionado = "* Selecione um usuário para ver o perfil.";
			retorno = "pesquisarUsuariosPage";
		}else{
			retorno = "perfilUsuarioPage"; 
		}
		return retorno;
	}
	
	public void limpar(){
		this.msgErroTipoPesquisa = "";
		this.msgErroUsuarioNaoSelecionado = "";
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<>();
		this.cadastrado = false;
		this.resultadoVazio = false;
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

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getMsgErroTipoPesquisa() {
		return msgErroTipoPesquisa;
	}

	public void setMsgErroTipoPesquisa(String msgErroTipoPesquisa) {
		this.msgErroTipoPesquisa = msgErroTipoPesquisa;
	}

	public String getMsgErroUsuarioNaoSelecionado() {
		return msgErroUsuarioNaoSelecionado;
	}

	public void setMsgErroUsuarioNaoSelecionado(String msgErroUsuarioNaoSelecionado) {
		this.msgErroUsuarioNaoSelecionado = msgErroUsuarioNaoSelecionado;
	}

	public boolean isResultadoVazio() {
		return resultadoVazio;
	}

	public void setResultadoVazio(boolean resultadoVazio) {
		this.resultadoVazio = resultadoVazio;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public String getEmailPesquisa() {
		return emailPesquisa;
	}

	public void setEmailPesquisa(String emailPesquisa) {
		this.emailPesquisa = emailPesquisa;
	}
	
	
	//TO DO:
	// Metodos: Validar, Consultar, LimpaCampos

}
