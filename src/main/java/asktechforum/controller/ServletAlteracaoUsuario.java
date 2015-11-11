package asktechforum.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Usuario;
//import asktechforum.negocio.UsuarioBC;
import asktechforum.fachada.Fachada;

/**
 * Implementacao do Servlet de Alterar Perfil de Usuario.
 */
public class ServletAlteracaoUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String ALTERAR = "./usuarioAutenticado/alterarUsuario.jsp";
    private static String ERROALTERACAO = "./usuarioAutenticado/alterarUsuario.jsp";
    private static String ERROALTERAREMAIL = "./usuarioAutenticado/alterarUsuario.jsp";
    private static String SUCESSOALTERACAO = "perfilUsuario.jsp";
    private static String ERROALTERACAOEXCLUSAO = "./usuarioAutenticado/alteracaoExclusaoUsuarioErro.jsp";
    
	//private UsuarioBC usuarioBC;
       
    /**
     * Construtor do Servlet de Alterar Perfil de Usuario.
     */
    public ServletAlteracaoUsuario() {
        super();
        //this.usuarioBC = new UsuarioBC();
    }

    /**
	 * Implementacao do metodo doGet() Servlet de Alterar Perfil de Usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Alterar Perfil de Usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();
		int quantAdmin;
		boolean flag = true;
		Fachada fachada = Fachada.getInstance();
		
		String pesquisaUsuarioEmail = request.getParameter("pesquisaUsuarioEmail");
		String alteracaoUsuarioId = request.getParameter("alteracaoUsuarioId");
		String alteracaoAdminId = request.getParameter("alteracaoAdminId");
		
		int idUsuario = 0;
		RequestDispatcher view;
		
    	session.setAttribute("erroAlteracaoExclusao", true);
		
		if(pesquisaUsuarioEmail != null) {
			//usuario = this.usuarioBC.consultarUsuarioPorEmail(pesquisaUsuarioEmail);
			usuario = fachada.fachadaConsultarUsuarioPorEmail(pesquisaUsuarioEmail);
			if(usuario.getDataNascimento() != null) {
				usuario.setDataString(fachada.fachadaFormatarDataSQL(usuario.getDataNascimento().toString()));
			}
			usuario.setSenha("");
			usuario.setConfSenha("");
			view = request.getRequestDispatcher(ALTERAR);
			session.setAttribute("usuarioAlteracao", usuario);
			session.setAttribute("usuarioPerfil", usuario);
			view.forward(request, response);
		
		}else if(alteracaoUsuarioId != null) {
			idUsuario = Integer.parseInt(alteracaoUsuarioId);
			
			//usuario = this.usuarioBC.consultarUsuarioPorId(idUsuario);
			usuario = fachada.fachadaConsultarUsuarioPorId(idUsuario);
			//quantAdmin = this.usuarioBC.consultarQuantidadeAdmin(usuario);
			quantAdmin = fachada.fachadaConsultarQuantidadeAdmin(usuario);
			if(!fachada.fachadaVerificarEmail(request.getParameter("email"), usuario)) {
				usuario.setNome(request.getParameter("nome"));
				usuario.setDataString(request.getParameter("dataNascimento"));
				usuario.setEmail(request.getParameter("email"));
				usuario.setLocalizacao(request.getParameter("localizacao"));
				usuario.setSenha(request.getParameter("senha"));
				usuario.setConfSenha(request.getParameter("confsenha"));
				
				if(request.getParameter("admin") != null) {
					if(request.getParameter("admin").trim().equals("true")) {
						usuario.setAdmin(true);

						flag = fachada.fachadaAlterarUsuario(usuario);
						
						if(flag) {
							view = request.getRequestDispatcher(SUCESSOALTERACAO);
							session.setAttribute("usuarioLogado", usuario);
					        view.forward(request, response);
						}else {
							view = request.getRequestDispatcher(ERROALTERACAO);
							
							usuario.setSenha("");
							usuario.setConfSenha("");
							request.setAttribute("usuarioAlteracao", usuario);
						    view.forward(request, response);
						}
					}
				}else {
					if(quantAdmin > 1) {
						usuario.setAdmin(false);

						flag = fachada.fachadaAlterarUsuario(usuario);
						
						if(flag) {
							view = request.getRequestDispatcher(SUCESSOALTERACAO);
							session.setAttribute("usuarioLogado", usuario);
					        view.forward(request, response);
						}else {
							view = request.getRequestDispatcher(ERROALTERACAO);
							
							usuario.setSenha("");
							usuario.setConfSenha("");
							request.setAttribute("usuarioAlteracao", usuario);
						    view.forward(request, response);
						}
					}else {
						view = request.getRequestDispatcher(ERROALTERACAOEXCLUSAO);
				        view.forward(request, response);
			        }
				}
			}else {
				view = request.getRequestDispatcher(ERROALTERAREMAIL);
				request.setAttribute("emailExistente", true);
				usuario.setNome(request.getParameter("nome"));
				usuario.setDataString(request.getParameter("dataNascimento"));
				usuario.setLocalizacao(request.getParameter("localizacao"));
				usuario.setSenha("");
				usuario.setConfSenha("");
				request.setAttribute("usuarioAlteracao", usuario);
				view.forward(request, response);
			}
	        
		}else if(alteracaoAdminId != null) {
			idUsuario = Integer.parseInt(alteracaoAdminId);
			
			usuario = fachada.fachadaConsultarUsuarioPorId(idUsuario);
			
			if(request.getParameter("admin") != null) {
				usuario.setAdmin(true);
			}else {
				usuario.setAdmin(false);
			}

			flag = fachada.fachadaAlterarUsuarioAdmin(usuario);
			
			if(flag) {
				view = request.getRequestDispatcher(SUCESSOALTERACAO);
				session.setAttribute("usuarioPerfil", usuario);
		        view.forward(request, response);
			}else {
				view = request.getRequestDispatcher(ERROALTERACAO);
				
				usuario.setSenha("");
				usuario.setConfSenha("");
				session.setAttribute("usuarioAlteracao", usuario);
			    view.forward(request, response);
			}
			
		}
	}

}
