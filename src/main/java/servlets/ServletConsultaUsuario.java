package servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServletConsultaUsuario
 */
@WebServlet("/ServletConsultaUsuario")
public class ServletConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletConsultaUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ModelLogin modelUsuario = new ModelLogin();
		ModelLogin usuario = new ModelLogin();

		String login = request.getParameter("login-busca");

		DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

		try {
			usuario = daoUsuarioRepository.consultarUsuarioLogin(login);
			
			if (usuario != null && !usuario.equals("null") ) {
				
				System.out.println(usuario.getNome());
				
				modelUsuario.setId(usuario.getId());
				modelUsuario.setNome(usuario.getNome());
				modelUsuario.setLogin(usuario.getLogin());
				modelUsuario.setEmail(usuario.getEmail());
				modelUsuario.setSenha(usuario.getSenha());
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/consultar-usuario.jsp");
				request.setAttribute("modelLogin", modelUsuario);
				redirecionar.forward(request, response);
			}
			
		} catch (SQLException e) {

			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/consultar-usuario.jsp");
			request.setAttribute("msg", "Usuário não encontrado!");
			redirecionar.forward(request, response);
			
		}

		


		

			
		

	}
}
