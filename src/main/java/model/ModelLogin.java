package model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String nome;
	private String login;
	private String senha;
	private String fotoUser;
	private String extensaoFotoUser;

	private int admin;

	public int getAdmin() {
		return this.admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getFotoUser() {
		return fotoUser;
	}

	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}

	public String getExtensaoFotoUser() {
		return extensaoFotoUser;
	}

	public void setExtensaoFotoUser(String extensaoFotoUser) {
		this.extensaoFotoUser = extensaoFotoUser;
	}
	
	public boolean isNovo (){

		if(this.id == null){
			return true;
		} else if(this.id != 0 && this.id > 0){
			return false;
		}

		return id == null;
	}

	public Long getId() {
		return id;
		
	}

	public void setId(Long id) {
		this.id = id;
		
	}

	public String getEmail() {
		return email;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public String getNome() {
		return nome;
		
	}

	public void setNome(String nome) {
		this.nome = nome;
		
	}

	public String getLogin() {
		return login;
		
	}

	public void setLogin(String login) {
		this.login = login;
		
	}

	public String getSenha() {
		return senha;
		
	}

	public void setSenha(String senha) {
		this.senha = senha;
		
	}

}
