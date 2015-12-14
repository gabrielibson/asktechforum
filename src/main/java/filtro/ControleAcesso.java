package filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.Usuario;

@WebFilter("/jsf/admin/*")
public class ControleAcesso implements Filter {

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)req).getSession();
		Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
		if(usuario==null){
			((HttpServletResponse)res).sendRedirect("../acessoNegado.jsf");
		}else{
			chain.doFilter(req, res);
		}
	} 
	public void destroy() {

	}

}