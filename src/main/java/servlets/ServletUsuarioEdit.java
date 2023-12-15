package servlets;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet("/ServletUsuarioEdit")
public class ServletUsuarioEdit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletUsuarioEdit() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

        } catch (Exception e){

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


            ModelLogin modelLogin = new ModelLogin();

            modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
            modelLogin.setNome(nome);
            modelLogin.setEmail(email);
            modelLogin.setLogin(login);
            modelLogin.setSenha(senha);


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
                request.getSession().setAttribute("userInfos", modelLogin);
            }

            RequestDispatcher redirecionar = request.getRequestDispatcher("principal/view-profile.jsp");
            redirecionar.forward(request, response);

        } catch (Exception e){

            e.printStackTrace();

            RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("msg", e.getMessage());
            redirecionar.forward(request, response);

        }

    }

}
