package asktechforum.beans;

import javax.faces.bean.ManagedBean;

import asktechforum.dominio.Pergunta;
import asktechforum.fachada.Fachada;

@ManagedBean(name="perguntaBean")
public class PerguntaBean {
	Fachada fachada = Fachada.getInstance();
	Pergunta pergunta;

	public PerguntaBean(){
		this.pergunta = new Pergunta();
	}
	
	public String inserirPergunta(){
		String retornoCadastroPergunta = fachada
				.fachadaAdcionarPergunta(pergunta);

		if (retornoCadastroPergunta != null
				&& !retornoCadastroPergunta.equals("cadastroSucesso")) {
/*			session.setAttribute("erroCadastroPergunta",
					retornoCadastroPergunta);
			request.setAttribute("pergunta", pergunta);
			request.getRequestDispatcher(
					"usuarioAutenticado/CadastroPergunta.jsp").forward(
					request, response);
*/		} else {
		/*	RequestDispatcher view = request
					.getRequestDispatcher(SUCESSOCADASTRO);
			request.setAttribute("pergunta", pergunta);
			view.forward(request, response);*/
		}

		return "";
	}
	
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	
}
