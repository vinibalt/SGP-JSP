package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;

	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();

	}

	public void gravaAcesso (ModelLogin modelLogin) throws SQLException {
		String sql = "insert into historico_acessos (id_user, data) values (?, ?)";

		Date date = new Date();
		java.sql.Timestamp dateSql = new java.sql.Timestamp(date.getTime());

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setLong(1, modelLogin.getId());
		ps.setTimestamp(2, dateSql);

		ps.execute();

		connection.commit();
	}

	public boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		String sql = "select * from model_login where login = ? and senha = ?";

		try {

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());

			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return false;
	}

}
