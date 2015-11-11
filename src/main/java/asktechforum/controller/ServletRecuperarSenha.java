package asktechforum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asktechforum.dominio.Usuario;
import asktechforum.fachada.Fachada;

/**
 * Implementacao do Servlet de Recuperar Senha.
 */
public class ServletRecuperarSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * Construtor do Servlet de Recuperar Senha.
     */
    public ServletRecuperarSenha() {
        super();
    }

	/**
	 * Implementacao do metodo doGet() de Recuperar Senha.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Implementacao do metodo doPost() de Recuperar Senha.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailPesquisado = request.getParameter("email");
		Fachada fachada = Fachada.getInstance();
		//UsuarioBC consultaUsuario = new UsuarioBC();
		Usuario usuario = fachada.fachadaConsultarUsuarioPorEmail(emailPesquisado);
		
		try{
			if(usuario.getEmail() == null){
				request.getRequestDispatcher("respostaNegativaEsqueceuSenha.jsp").forward(request, response);
				
			}else{
				fachada.sendEmailEsqueciSenha(usuario);
				request.getRequestDispatcher("respostaPositivaEsqueceuSenha.jsp").forward(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
