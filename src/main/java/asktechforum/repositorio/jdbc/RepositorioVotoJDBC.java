package asktechforum.repositorio.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import asktechforum.dataBase.ConnectionUtil;
import asktechforum.interfaces.RepositorioVoto;

public class RepositorioVotoJDBC implements RepositorioVoto{
    private ConnectionUtil conexaoUtil;

	public RepositorioVotoJDBC() {
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}
	
	public void adicionarVotoUsuario(int idUsuario, int idResposta) throws SQLException {
		String sql = "insert into voto(idUsuario, idResposta)values( ?, ? )";
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();
			con.close();
		}
	}
	
	public void deletarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("delete from voto where idUsuario=? and idResposta=?");

			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
	}
	
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Boolean flag = true;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con
					.prepareStatement("select * from voto where idUsuario=? and idResposta=?");

			preparedStatement.setInt(1, idUsuario);
			preparedStatement.setInt(2, idResposta);
			rs = preparedStatement.executeQuery();
			
			if(rs.next() == false) {
				flag = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            con.close();
        }
		
		return flag;
	}
	
}