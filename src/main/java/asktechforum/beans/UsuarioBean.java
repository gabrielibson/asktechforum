package asktechforum.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import asktechforum.dominio.Usuario;

@ManagedBean(name="userBean")
@ViewScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String confSenha;
	private String senhaNova;
	private String senhaAtual;
	
	//TO DO:
	// Metodos: Validar, Consultar, LimpaCampos

}
