package dao;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelUserHistory;

public class DAOUsuarioRepository {
	private Connection connection;

	public DAOUsuarioRepository() {

		connection = SingleConnectionBanco.getConnection();

	}




	public List<ModelUserHistory> getUserHistory () throws SQLException {

		List<ModelUserHistory> retorno = new ArrayList<>();

		String sql = "select m.nome, m.login, m.admin, h.data from historico_acessos h join model_login m on m.id = h.id_user";

		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet result = ps.executeQuery();

		while(result.next()){

			ModelUserHistory ret = new ModelUserHistory();

			ret.setNome(result.getString("nome"));
			ret.setLogin(result.getString("login"));
			ret.setAdmin(result.getInt("admin"));
			ret.setData(result.getTimestamp("data")) ;

			retorno.add(ret);

		}



		return retorno;
	}
	
	public ModelLogin gravarUsuario (ModelLogin modelLogin) throws Exception {
		

		


			if(modelLogin.isNovo()) {
				String sql = "INSERT INTO model_login(login, senha, nome, email, admin2) VALUES(?, ?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, modelLogin.getLogin());
				statement.setString(2, modelLogin.getSenha());
				statement.setString(3, modelLogin.getNome());
				statement.setString(4, modelLogin.getEmail());
				statement.setInt(5, modelLogin.getAdmin());


				statement.execute();

				connection.commit();

				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()){
					sql = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE login = ?";
					statement = connection.prepareStatement(sql);
					statement.setString(1, modelLogin.getFotoUser());
					statement.setString(2, modelLogin.getExtensaoFotoUser());
					statement.setString(3, modelLogin.getLogin());

					statement.execute();
					connection.commit();

				}

			} else {
				String sql1 = "UPDATE model_login SET login = ?, senha = ?, nome = ?, email = ?, admin2 = ? WHERE id = "+ modelLogin.getId() +"";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setString(1, modelLogin.getLogin());
				statement1.setString(2, modelLogin.getSenha());
				statement1.setString(3, modelLogin.getNome());
				statement1.setString(4, modelLogin.getEmail());
				statement1.setInt(5, modelLogin.getAdmin());

				statement1.executeUpdate();
				connection.commit();

				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()){
					sql1 = "UPDATE model_login SET fotouser = ?, extensaofotouser = ? WHERE id = ?";
					statement1 = connection.prepareStatement(sql1);
					statement1.setString(1, modelLogin.getFotoUser());
					statement1.setString(2, modelLogin.getExtensaoFotoUser());
					statement1.setLong(3, modelLogin.getId());

					statement1.execute();
					connection.commit();

				}

			}

			return this.consultarUsuarioLogin(modelLogin.getLogin());
		
	}



	
	
	public ModelLogin consultarUsuarioLogin (String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = ?";
		
		
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet result = statement.executeQuery();
		
		if (result.next()) {
			
			modelLogin.setId(result.getLong("id"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setFotoUser(result.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(result.getString("extensaofotouser"));
			modelLogin.setAdmin(result.getInt("admin2"));


		}
		
		return modelLogin;
		
	}
	
	public ModelLogin consultarUsuarioEmail (String email) throws SQLException{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where email = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			
			modelLogin.setId(result.getLong("id"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
		}
		
		return modelLogin;
		
	}

	public ModelLogin buscarUsuarioId(String id) throws SQLException {

		ModelLogin retorno = new ModelLogin();
		String sql = "SELECT * FROM model_login WHERE id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);

		ResultSet result = statement.executeQuery();

		while(result.next()){
			retorno.setId(result.getLong("id"));
			retorno.setNome(result.getString("nome"));
			retorno.setEmail(result.getString("email"));
			retorno.setLogin(result.getString("login"));
			retorno.setSenha(result.getString("senha"));
			retorno.setFotoUser(result.getString("fotouser"));
			retorno.setExtensaoFotoUser(result.getString("extensaofotouser"));
			retorno.setAdmin(result.getInt("admin2"));
		}

		return retorno;
	}

	public List<ModelLogin> listarUsuarios() throws SQLException{

		List<ModelLogin> retorno = new ArrayList<>();

		String sql = "SELECT * FROM model_login where admin2<>true;";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();

		while(result.next()){
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setFotoUser(result.getString("fotouser"));
			modelLogin.setFotoUser(result.getString("extensaofotouser"));
			modelLogin.setAdmin(result.getInt("admin2"));

			retorno.add(modelLogin);
		}

		return retorno;
	}
	
	public boolean validarLogin (String login) throws Exception {
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
		
		return false;
	}
	
	
	public void deletarUsuario(String idUser) throws SQLException {
		String sql = "DELETE FROM model_login WHERE id = ?";

		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUser));
		prepareSql.executeUpdate();

		connection.commit();
	}


   public List<ModelLogin> buscarUsuario(String nomeBusca) throws SQLException {

		List<ModelLogin> retorno = new ArrayList<>();

		String sql = "SELECT * FROM model_login WHERE upper(nome) like upper(?) and admin2<>true";
		PreparedStatement prepareSql = connection.prepareStatement(sql);

		prepareSql.setString(1, "%"+ nomeBusca +"%");

		ResultSet result = prepareSql.executeQuery();

		while(result.next()){
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setFotoUser(result.getString("fotouser"));
			modelLogin.setFotoUser(result.getString("extensaofotouser"));
			modelLogin.setAdmin(result.getInt("admin2"));

			retorno.add(modelLogin);
		}

		return retorno;
   }
	
	
	
	
	
	
}
