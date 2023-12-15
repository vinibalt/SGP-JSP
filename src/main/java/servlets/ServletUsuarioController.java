package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelUserHistory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;


import dao.DAOUsuarioRepository;
import model.ModelLogin;

/**
 * Servlet implementation class ServletUsuarioController
 */
@MultipartConfig
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				request.setAttribute("msg", "Usuario excluido com sucesso!!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")){

				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				response.getWriter().write("Usuario excluido com sucesso!!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscaruserajax")){

				String nomeUser = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.buscarUsuario(nomeUser);
				ObjectMapper objectMapper = new ObjectMapper();
				String json = objectMapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("preencherajax")){
				String id = request.getParameter("id");
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(daoUsuarioRepository.buscarUsuarioId(id));
				request.setAttribute("modelLogin", daoUsuarioRepository.buscarUsuarioId(id));
				request.setAttribute("msg", "Usuário em edição");
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listartodosusuariosajax")){
				ObjectMapper obj = new ObjectMapper();
				String json = obj.writeValueAsString(daoUsuarioRepository.listarUsuarios());
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listartodosusuarios")){
				List<ModelLogin> usuarios = daoUsuarioRepository.listarUsuarios();
				request.setAttribute("usuarios", usuarios);
				request.getRequestDispatcher("principal/usuario-cadastro.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscareditar")){
				String id = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.buscarUsuarioId(id);

				List<ModelLogin> usuarios = daoUsuarioRepository.listarUsuarios();
				request.setAttribute("usuarios", usuarios);

				request.setAttribute("msg", "Usuario em edição");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/usuario-cadastro.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadfoto")){

				String idUser = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.buscarUsuarioId(idUser);
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()){
					response.setHeader("Content-Disposition", "attachment;filename=foto-" + modelLogin.getNome() +"." + modelLogin.getExtensaoFotoUser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("gerarrelatorio")){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment;filename=relatorio.pdf");

				Document doc = new Document(PageSize.A4);

				try {

					PdfWriter.getInstance(doc, response.getOutputStream());


					doc.open();

					List<ModelLogin> users = daoUsuarioRepository.listarUsuarios();

					Paragraph p = new Paragraph("Relatório de Usuários");
					p.setAlignment(1);
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);

					PdfPTable table = new PdfPTable(3);

					PdfPCell cell1 = new PdfPCell(new Paragraph("Nome"));
					PdfPCell cell2 = new PdfPCell(new Paragraph("Email"));
					PdfPCell cell3 = new PdfPCell(new Paragraph("Login"));



					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);

					for (ModelLogin user : users) {



						cell1 = new PdfPCell(new Paragraph(user.getNome()));
						cell2 = new PdfPCell(new Paragraph(user.getEmail()));
						cell3 = new PdfPCell(new Paragraph(user.getLogin()));

						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);

					}

					doc.add(table);
					doc.close();

					request.setAttribute("listaDados", users);

					request.getRequestDispatcher("principal/usuario-cadastro.jsp").forward(request, response);



				} catch (DocumentException e) {
					e.printStackTrace();
				}

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("gerarhistoricoacessos")){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment;filename=relatorio.pdf");

				Document doc = new Document(PageSize.A4);

				try {

					PdfWriter.getInstance(doc, response.getOutputStream());


					doc.open();

					List<ModelUserHistory> users = daoUsuarioRepository.getUserHistory();

					Paragraph p = new Paragraph("Relatório de Acessos de Usuários");
					p.setAlignment(1);
					doc.add(p);
					p = new Paragraph(" ");
					doc.add(p);

					PdfPTable table = new PdfPTable(4);

//					PdfPCell cell1 = new PdfPCell(new Paragraph("Nome"));
//					PdfPCell cell2 = new PdfPCell(new Paragraph("Email"));
//					PdfPCell cell3 = new PdfPCell(new Paragraph("Login"));

					PdfPCell cell1 = new PdfPCell(new Paragraph("Nome"));
					PdfPCell cell2 = new PdfPCell(new Paragraph("Login"));
					PdfPCell cell3 = new PdfPCell(new Paragraph("Tipo Usuário"));
					PdfPCell cell4 = new PdfPCell(new Paragraph("Data"));

					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);

					for (ModelUserHistory user : users) {



						cell1 = new PdfPCell(new Paragraph(user.getNome()));
						cell2 = new PdfPCell(new Paragraph(user.getLogin()));
						cell3 = new PdfPCell(new Paragraph(user.getAdmin() == 1 ? "Admin" : "Comum"));
						cell4 = new PdfPCell(new Paragraph(String.valueOf(user.getData())));

						table.addCell(cell1);
						table.addCell(cell2);
						table.addCell(cell3);
						table.addCell(cell4);

					}

					doc.add(table);
					doc.close();

					request.setAttribute("listaDados", users);

					request.getRequestDispatcher("principal/usuario-cadastro.jsp").forward(request, response);



				} catch (DocumentException e) {
					e.printStackTrace();
				}

			} else {
				List<ModelLogin> usuarios = daoUsuarioRepository.listarUsuarios();
				request.setAttribute("usuarios", usuarios);
				request.getRequestDispatcher("principal/usuario-cadastro.jsp").forward(request, response);
			}



		} catch (Exception e){
			e.printStackTrace();

			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String msg = "Operação realizada com sucesso!";

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String admin = request.getParameter("admin");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setAdmin(admin == null ? 0 : Integer.parseInt(admin));


				Part part = request.getPart("fotouser"); // pega foto da tela
			if (part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream()); /*Converte imagem para byte*/
				String img64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
				modelLogin.setFotoUser(img64);
				modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
			}


			if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Já existe usuário com este login! Por favor tente outro.";
			} else {
				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso!";
				}else {
					msg= "Atualizado com sucesso!";
				}
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}

			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario-cadastro.jsp");
			List<ModelLogin> usuarios = daoUsuarioRepository.listarUsuarios();
			request.setAttribute("usuarios", usuarios);
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			redirecionar.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
