package asktechforum.interfaces;

import java.sql.SQLException;
import java.util.List;

import asktechforum.dominio.Usuario;

public interface RepositorioUsuario {
	public void alterarUsuario(Usuario usuario) throws SQLException;
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException;
	public void adicionarUsuario(Usuario usuario) throws SQLException;
	public void deletarUsuario(String email) throws SQLException ;
	public void deletarUsuarioPorId(int idUsuario) throws SQLException ;
	public Usuario consultarUsuarioPorEmail_Senha(String email,String senha) 
			throws SQLException ;
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException;
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException;
	public List<Usuario> consultarUsuarioPorNome(String nome) throws SQLException;
	public List<Usuario> consultarTodosUsuarios() throws SQLException;


}
