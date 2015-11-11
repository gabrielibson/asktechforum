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
 * Implementacao do Servlet de Perfil de Usuario.
 */
public class ServletPerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String PERFIL = "perfilUsuario.jsp";
       
	//private UsuarioBC usuarioBC;
    
    /**
     * Construtor do Servlet de Perfil de Usuario.
     */
    public ServletPerfilUsuario() {
        super();
        //this.usuarioBC = new UsuarioBC();
    }

    /**
	 * Implementacao do metodo doGet() Servlet de Perfil de Usuario.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request, response);
	}

	/**
	 * Implementacao do metodo doPost() Servlet de Perfil de Usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fachada fachada = Fachada.getInstance();
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();
		String usuarioRadio = request.getParameter("usuarioRadio");
		
		if(usuarioRadio != null) {
			usuario = fachada.fachadaConsultarUsuarioPorEmail(usuarioRadio);
			RequestDispatcher view = request.getRequestDispatcher(PERFIL);
			session.setAttribute("usuarioPerfil", usuario);

	        view.forward(request, response);
		}
	}

}
