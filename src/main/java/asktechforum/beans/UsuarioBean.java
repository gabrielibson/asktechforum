package asktechforum.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	private boolean alterado;
	private boolean excluido;
	private boolean clicouExcluir;
	private Usuario usuarioSelecionado;
	private Usuario usuarioLogado;
	private String tipoPesquisa;
	private String msgErroTipoPesquisa;
	private String msgErroUsuarioNaoSelecionado;
	private boolean exibeErroExclusao;
	private boolean resultadoVazio;
	private String nomePesquisa;
	private String emailPesquisa;
	private HttpSession session;

	public UsuarioBean() {
		this.limpar();
		this.fachada = Fachada.getInstance();
		this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		this.setUsuarioLogado((Usuario) session.getAttribute("usuarioLogado"));
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
				FacesMessage message = new FacesMessage("N�o foi poss�vel cadastrar usu�rio.Por favor, verifique os dados preenchidos e tente novamente. ");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("messages", message);
				
				return "erroCadastro";
			}
		}else{
			FacesMessage message = new FacesMessage("Este email j� est� cadastrado no AskTechForum. ");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("messages", message);
			
			return "erroCadastro";
		}
	}

	public String alterarUsuario(){
		this.fachada = Fachada.getInstance();
		this.setAlterado(false);
		//int quantAdmin = this.fachada.fachadaConsultarQuantidadeAdmin(this.usuario);
		boolean flag = true;
		this.usuarioLogado = (Usuario) this.session.getAttribute("usuarioLogado");
		
		if(!fachada.fachadaVerificarEmail(usuario.getEmail(), this.usuarioSelecionado)) {
			flag = fachada.fachadaAlterarUsuario(this.usuario);

			if(flag){
				this.alterado = true;
				this.setUsuarioSelecionado(this.usuario);
				this.usuario = new Usuario();
				return "sucessoCadastro";
			}else{
				
				FacesMessage message = new FacesMessage("N�o foi poss�vel realizar altera��o.Por favor, verifique os dados preenchidos e tente novamente. ");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage("messages", message);
				
				return "alterarUsuarioPage";
			}
		}else{
			
			FacesMessage message = new FacesMessage("Este Email j� est� cadastrado no AskTechForum.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("messages", message);
			
			return "alterarUsuarioPage";
		}

	}

	public String excluirUsuario(){
		this.usuarioLogado = (Usuario) this.session.getAttribute("usuarioLogado");

		if(this.fachada.fachadaConsultarQuantidadeAdmin(usuarioSelecionado) > 1){
		//	this.fachada.fachadaDeletarUsuarioPorId(this.usuarioSelecionado.getIdUsuario());
			this.fachada.fachadaDeletarUsuario(this.usuarioSelecionado.getEmail());
			
			if(this.usuarioSelecionado != null && usuarioLogado != null) {
				if(this.usuarioSelecionado.getIdUsuario() == usuarioLogado.getIdUsuario()) {
					session.invalidate();
				}
			}
			this.excluido = true;
		}else{
			this.exibeErroExclusao = true; 
		}
				
		return "perfilUsuarioPage";
	}
	
	public String chamarAlterarUsuario(){
		this.setAlterado(false);
		this.usuario = new Usuario();
		this.usuario.setAdmin(usuarioSelecionado.isAdmin());
		this.usuario.setConfSenha("");
		this.usuario.setSenha("");
		this.usuario.setDataNascimento(usuarioSelecionado.getDataNascimento());
		this.usuario.setEmail(usuarioSelecionado.getEmail());
		this.usuario.setNome(usuarioSelecionado.getNome());
		this.usuario.setDataString(usuarioSelecionado.getDataString());
		this.usuario.setLocalizacao(usuarioSelecionado.getLocalizacao());
		this.usuario.setPergunta(usuarioSelecionado.getPergunta());
		this.usuario.setIdUsuario(usuarioSelecionado.getIdUsuario());
		return "alterarUsuarioPage";
	}
	
	public String chamarCadastroUsuario(){
		limpar();
		return "cadastroUsuarioPage";
	}
	
	public String chamarPesquisarUsuario(){
		this.limpar();
		return "pesquisarUsuariosPage";
	}
	
	public String chamarExcluirUsuario(){
		this.limpar();
		this.setClicouExcluir(true);
		return "perfilUsuarioPage";
	}
	
	public String cancelarExcluir(){
		this.setClicouExcluir(false);
		return "perfilUsuarioPage";
	}

	public String pesquisarUsuario(){
		this.limpar();

		if(this.tipoPesquisa != null && this.tipoPesquisa.trim() != "") {
			switch (this.tipoPesquisa) {
			case "nome":
				if(this.nomePesquisa != null && this.nomePesquisa.trim() != "") {
					this.usuarios.addAll(fachada.fachadaConsultarUsuarioPorNome(this.nomePesquisa));
					//Fazer tratamento data de nascimento
					for (Usuario usuario : usuarios) {
						this.tratarDataNascimento(usuario);
					}
				}else{
					this.msgErroTipoPesquisa = "Informe o nome do usu�rio.";
				}
				break;
			case "email":
				if(this.emailPesquisa != null && this.emailPesquisa.trim() != "") {
					Usuario usuario = fachada.fachadaConsultarUsuarioPorEmail(this.emailPesquisa);
					if(usuario != null) {
						if(usuario.getIdUsuario() != 0) {
							//Fazer tratamento data de nascimento
							tratarDataNascimento(usuario);
							this.usuarios.add(usuario);		
							
						}
					}
				}else{
					this.msgErroTipoPesquisa = "Informe o email do usu�rio.";
				}
				break;
			case "todos":
				this.usuarios.addAll(fachada.fachadaConsultarTodosUsuarios());
				//Fazer tratamento data de nascimento
				for (Usuario usuario : usuarios) {
					this.tratarDataNascimento(usuario);
				}
				break;
			case "":
				break;
			default:
				break;
			}
		}else{
			this.msgErroTipoPesquisa = "* Selecione uma das op��es acima para pesquisar.";
		}

		if(this.msgErroTipoPesquisa.equals("") && this.usuarios.isEmpty()){
			this.resultadoVazio = true;
		}
		
		this.nomePesquisa = "";
		this.emailPesquisa = "";
		this.tipoPesquisa="";
		
		return "pesquisarUsuariosPage";
	}

	private void tratarDataNascimento(Usuario usuario){
		if(usuario.getDataNascimento() != null) {
			usuario.setDataString(fachada.fachadaFormatarDataSQL(usuario.getDataNascimento().toString()));
		}
	}
	
	public String exibirPerfilUsuario(){
		this.exibeErroExclusao = false;
		this.excluido = false;
		String retorno = "";
		if(this.usuarioSelecionado == null){
			this.msgErroUsuarioNaoSelecionado = "* Selecione um usu�rio para ver o perfil.";
			retorno = "pesquisarUsuariosPage";
		}else{
			//Fazer tratamento data de nascimento
			tratarDataNascimento(usuarioSelecionado);
			retorno = "perfilUsuarioPage"; 
		}
		return retorno;
	}
	
	public String exibirPerfilUsuarioLogado(){
		this.exibeErroExclusao = false;
		this.excluido = false;
		this.usuarioLogado = (Usuario) this.session.getAttribute("usuarioLogado");
		this.usuarioSelecionado = this.fachada.fachadaConsultarUsuarioPorId(this.usuarioLogado.getIdUsuario());
		this.tratarDataNascimento(usuarioSelecionado);
		return "perfilUsuarioPage";
	}

	public void limpar(){
		this.msgErroTipoPesquisa = "";
		this.msgErroUsuarioNaoSelecionado = "";
		this.exibeErroExclusao = false;
		this.usuario = new Usuario();
		this.usuarios = new ArrayList<>();
		this.cadastrado = false;
		this.alterado = false;
		this.excluido = false;
		this.clicouExcluir = false;
		this.resultadoVazio = false;
		this.setExcluido(false);
				
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

	public boolean isAlterado() {
		return alterado;
	}

	public void setAlterado(boolean alterado) {
		this.alterado = alterado;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
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

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isClicouExcluir() {
		return clicouExcluir;
	}

	public void setClicouExcluir(boolean clicouExcluir) {
		this.clicouExcluir = clicouExcluir;
	}

	public boolean isExibeErroExclusao() {
		return exibeErroExclusao;
	}

	public void setExibeErroExclusao(boolean exibeErroExclusao) {
		this.exibeErroExclusao = exibeErroExclusao;
	}

	
	//TO DO:
	// Metodos: Validar, Consultar, LimpaCampos

}
