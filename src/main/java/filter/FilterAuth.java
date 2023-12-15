package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import connection.SingleConnectionBanco;
import model.ModelLogin;


@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAuth implements Filter {

	private static Connection connection;

	public FilterAuth() {

	}

	// encerra os processos quando o servidor é parado
	// mata os processos de conexão com banco
	public void destroy() {
		try {

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	// intercepta as requisiões e as respostas no sistema
	// tudo o que fizer no sistema passara por ele
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");
			ModelLogin user = (ModelLogin) session.getAttribute("userInfos");

			String urlParaAutenticar = req.getServletPath();/* Url que está sendo acessada */

			/* Validar se está logado senão redireciona para a tela de login */
			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {/* Não está logado */

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return; /* Para a execução e redireciona para o login */

			} else if( user != null && user.getAdmin() == 0 && urlParaAutenticar.equalsIgnoreCase("/principal/usuario-cadastro.jsp")){
				RequestDispatcher redireciona = request.getRequestDispatcher("/principal/principal.jsp");
				redireciona.forward(request, response);
			}  else {
				chain.doFilter(request, response);
			}


			
			connection.commit(); // deu tudo certo, entao commita as alterações no bd

		} catch (Exception e) {
			e.printStackTrace();
			
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
