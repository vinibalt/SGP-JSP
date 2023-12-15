package connection;

import java.sql.Connection;

import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:mariadb://localhost:3306/banco_jspw";
	private static String user = "maria";
	private static String senha = "maria123";
	
	private static Connection connection = null;
	
	
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	//quando tiver uma instancia da classe, vai conectar
	public SingleConnectionBanco() {
		conectar();
	}
	
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("org.mariadb.jdbc.Driver"); // Carrega o driver de conexão do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); // para não efetuar alterações no banco sem o nosso comando
				
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
