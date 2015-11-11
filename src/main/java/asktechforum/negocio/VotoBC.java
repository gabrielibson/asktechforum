package asktechforum.negocio;

import java.sql.SQLException;

import asktechforum.factory.FabricaDAO;
import asktechforum.factory.FactoryDataBase;
import asktechforum.interfaces.RepositorioVoto;

public class VotoBC {
	private RepositorioVoto votoDAO;
	
	public VotoBC() {
		FabricaDAO fabrica = FactoryDataBase.getInstancia().criarFabrica("JDBC");
		this.votoDAO = fabrica.criarDaoVoto();
	}
	
	public void adicionarVotoUsuario(int idUsuario, int idResposta) {
		try {
			this.votoDAO.adicionarVotoUsuario(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarUsuarioVoto(int idUsuario, int idResposta) {
		try {
			this.votoDAO.deletarUsuarioVoto(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean consultarUsuarioVoto(int idUsuario, int idResposta) {
		Boolean flag = true;
		try {
			flag = this.votoDAO.consultarUsuarioVoto(idUsuario, idResposta);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}