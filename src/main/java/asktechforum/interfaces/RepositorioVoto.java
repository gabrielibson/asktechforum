package asktechforum.interfaces;

import java.sql.SQLException;

public interface RepositorioVoto {
	public void adicionarVotoUsuario(int idUsuario, int idResposta) throws SQLException;
	public void deletarUsuarioVoto(int idUsuario, int idResposta) throws SQLException;
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) throws SQLException;

}
