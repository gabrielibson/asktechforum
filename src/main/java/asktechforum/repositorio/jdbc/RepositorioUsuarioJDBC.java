package asktechforum.repositorio.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import asktechforum.dataBase.ConnectionUtil;
import asktechforum.dominio.Usuario;
import asktechforum.interfaces.RepositorioUsuario;
import asktechforum.util.UsuarioUtil;

public class RepositorioUsuarioJDBC  implements RepositorioUsuario{
    UsuarioUtil usuarioUtil;
    private ConnectionUtil conexaoUtil;
	
	public RepositorioUsuarioJDBC(){
		this.usuarioUtil = new UsuarioUtil();
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}
	
	public void alterarUsuario(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("update usuario set nome=?,dt_nasc=?,admin=?,email=?,localizacao=?,senha=? where idUsuario=?");

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setBoolean(3, usuario.isAdmin());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getLocalizacao());
            preparedStatement.setString(6, usuario.getSenha());
            preparedStatement.setInt(7, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
    }
	
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("update usuario set admin=? where idUsuario=?");

            preparedStatement.setBoolean(1, usuario.isAdmin());
            preparedStatement.setInt(2, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
    }
	
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("insert into usuario(nome,dt_nasc,email,localizacao,senha,admin) values ( ?, ?, ?, ?, ?, ? )");
            
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getLocalizacao());
            preparedStatement.setString(5, usuario.getSenha());
            preparedStatement.setBoolean(6, usuario.isAdmin());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
    }
	
	public void deletarUsuario(String email) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("delete from usuario where email=?");
            
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
    }
	
	public void deletarUsuarioPorId(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
            preparedStatement = con
                    .prepareStatement("delete from usuario where idUsuario=?");
            
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            con.close();
        }
    }
	
	public Usuario consultarUsuarioPorEmail_Senha(String email,String senha) 
			throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con
					.prepareStatement("select * from usuario where email=? and senha=?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,senha);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            con.close();
        }
		
		return usuario;
	}
	
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con
					.prepareStatement("select * from usuario where idUsuario=?");
			
			preparedStatement.setInt(1, idUsuario);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            con.close();
        }
		
		return usuario;
	}
		
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = null;
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con
					.prepareStatement("select * from usuario where email=?");
			
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            con.close();
        }
		
		return usuario;
	}	
	
	public List<Usuario> consultarUsuarioPorNome(String nome) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection con = this.conexaoUtil. getConnection();
		
		try {
			preparedStatement = con
					.prepareStatement("select * from usuario where nome like ?");
			
			preparedStatement.setString(1, "%"+nome+"%");
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            con.close();
        }
		
		return usuarios;
	}	
	
	public List<Usuario> consultarTodosUsuarios() throws SQLException {
		Statement statement = null;
		ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection con = this.conexaoUtil. getConnection();
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("select * from usuario;");
            
            while(rs.next()) {
            	Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
            	usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	statement.close();
            rs.close();
            con.close();
        }
        
        return usuarios;
    }
	
}