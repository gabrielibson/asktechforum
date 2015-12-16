package asktechforum.repositorio.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import asktechforum.dataBase.ConnectionUtil;
import asktechforum.dominio.Pergunta;
import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.interfaces.RepositorioPergunta;

public class RepositorioPerguntasJDBC implements RepositorioPergunta {

	private ConnectionUtil conexaoUtil;

	public RepositorioPerguntasJDBC() {
		this.conexaoUtil = ConnectionUtil.getInstancia();
	}
	
	public String adcionarPergunta(Pergunta pergunta) throws SQLException {
		String retorno = "cadastroSucesso";

		String sql = "insert into pergunta(titulo, data, hora, descricao, idUsuario, tag)values(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		 Connection con = this.conexaoUtil.getConnection();
		try {
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, pergunta.getTitulo());
			stmt.setDate(++index, pergunta.getData());
			stmt.setTime(++index, pergunta.getHora());
			stmt.setString(++index, pergunta.getDescricao());
			stmt.setInt(++index, pergunta.getIdUsuario());
			stmt.setString(++index, pergunta.getTag());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		return retorno;
	}

	public void deletarPergunta(int id) throws SQLException {

		String sql = "delete from pergunta where idPergunta = " + id;
		PreparedStatement stmt = null;
		Connection con = conexaoUtil.getConnection();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}

	}

	public Pergunta consultarPerguntaPorIdPergunta(int id) throws SQLException {

		Pergunta pergunta = new Pergunta();

		String sql = "select * from pergunta where idPergunta = " + id;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 Connection con = this.conexaoUtil.getConnection();
		 
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				pergunta.setDescricao(rs.getString("descricao"));
				pergunta.setIdPergunta(rs.getInt("idPergunta"));
				pergunta.setTitulo(rs.getString("titulo"));
				pergunta.setIdUsuario(rs.getInt("idUsuario"));
				pergunta.setData(rs.getDate("data"));
				pergunta.setHora(rs.getTime("hora"));
				pergunta.setTag(rs.getString("tag"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarPerguntaIdUsuario(int id)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from pergunta where idUsuario = " + id
				+ " order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		 Connection con = this.conexaoUtil.getConnection();
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	public ArrayList<Pergunta> consultarTodasPerguntas() throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from pergunta order by data, hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = this.conexaoUtil.getConnection();
		
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			pergunta = montarLista(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}
	
	
	public ArrayList<String> consultaTodasAsTags() throws SQLException {
		ArrayList<String> tags = new ArrayList<String>();
		
		String sql = "SELECT DISTINCT tag FROM pergunta order by tag";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = this.conexaoUtil.getConnection();
		
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();
			tags = this.separaTags(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return tags;
		
	}
	
	public ArrayList<String> separaTags(ResultSet rs){
		ArrayList<String> listTags = new ArrayList<String>();
		String [] resultTag ;
		
		try {
			while (rs.next()) {
				resultTag = rs.getString("tag").split(" ");
				
				for(int i=0; i<resultTag.length; i++ ){
					listTags.add(resultTag[i].toUpperCase());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return listTags;
	}
	
	public ArrayList<Pergunta> consultarPerguntaPorData(Date data)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();

		String sql = "select * from pergunta where idUsuario = " + data
				+ " order by hora";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = this.conexaoUtil.getConnection();
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Pergunta p = new Pergunta();
				p.setDescricao(rs.getString("descricao"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setIdUsuario(rs.getInt("idUsuario"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				p.setTag(rs.getString("tag"));
				pergunta.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTag(String tag)
			throws SQLException {
		ArrayList<ResultConsultarPergunta> pergunta = new ArrayList<ResultConsultarPergunta>();

		String sql = " select p.descricao, count(r.idResposta) total, u.nome, p.idPergunta, p.titulo, p.data, p.hora , p.idUsuario" +
				"  from usuario u left join pergunta p on u.idUsuario = p.idUsuario " +
				"		left join resposta r on p.idPergunta = r.idPergunta " +
				"		where p.tag like '%"+ tag +"%'  group by u.nome, p.idPergunta ; ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = this.conexaoUtil.getConnection();
		
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			ResultConsultarPergunta p;
			
			while(rs.next()){
				p = new ResultConsultarPergunta();
				p.setIdUsuario(rs.getInt("idUsuario"));
				p.setAutor(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdResposta(rs.getInt("total"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				pergunta.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}

	
	
	@Override
	public ArrayList<ResultConsultarPergunta> consultarPerguntaPorTodasTags()
			throws SQLException {
		ArrayList<ResultConsultarPergunta> pergunta = new ArrayList<ResultConsultarPergunta>();
		
		String sql = "SELECT p.descricao, COUNT( r.idResposta ) total, u.nome, p.idPergunta, p.titulo, p.tag, p.data, p.hora, p.idUsuario" +
		" FROM pergunta p LEFT JOIN usuario u ON u.idUsuario = p.idUsuario 	LEFT JOIN resposta r ON p.idPergunta = r.idPergunta " +
		" GROUP BY u.nome, p.idPergunta	ORDER BY p.data DESC , p.hora DESC LIMIT 0 , 15 ";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = this.conexaoUtil.getConnection();
		try {
			stmt = con.prepareStatement(sql);

			rs = stmt.executeQuery();

			ResultConsultarPergunta p;
			
			while(rs.next()){
				p = new ResultConsultarPergunta();
				p.setIdUsuario(rs.getInt("idUsuario"));
				p.setAutor(rs.getString("nome"));
				p.setDescricao(rs.getString("descricao"));
				p.setQtdResposta(rs.getInt("total"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setTag(rs.getString("tag"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				pergunta.add(p);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}

		return pergunta;
	}
	private ArrayList<Pergunta> montarLista(ResultSet rs)
			throws SQLException {
		ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
		Connection con = this.conexaoUtil.getConnection();
		
		try {
			while (rs.next()) {
				Pergunta p = new Pergunta();
				p.setDescricao(rs.getString("descricao"));
				p.setIdPergunta(rs.getInt("idPergunta"));
				p.setTitulo(rs.getString("titulo"));
				p.setIdUsuario(rs.getInt("idUsuario"));
				p.setData(rs.getDate("data"));
				p.setHora(rs.getTime("hora"));
				p.setTag(rs.getString("tag"));
				pergunta.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			con.close();
		}

		return pergunta;
	}

	@Override
	public String alterarPergunta(Pergunta pergunta) throws SQLException {
		String retorno = "alteracaoSucesso";

		String sql = "update pergunta set titulo=?, data=?, hora=?, descricao=?, idUsuario=?, tag=?  where idPergunta = ?";
		PreparedStatement stmt = null;
		 Connection con = this.conexaoUtil.getConnection();
		try {
			stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setString(++index, pergunta.getTitulo());
			stmt.setDate(++index, pergunta.getData());
			stmt.setTime(++index, pergunta.getHora());
			stmt.setString(++index, pergunta.getDescricao());
			stmt.setInt(++index, pergunta.getIdUsuario());
			stmt.setString(++index, pergunta.getTag());
			stmt.setInt(++index, pergunta.getIdPergunta());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			con.close();
		}
		
		return retorno;
	}

}
