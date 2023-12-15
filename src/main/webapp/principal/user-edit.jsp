<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
<!-- Pre-loader start -->
<jsp:include page="theme-loader.jsp"></jsp:include>
<!-- Pre-loader end -->
<div id="pcoded" class="pcoded">
  <div class="pcoded-overlay-box"></div>
  <div class="pcoded-container navbar-wrapper">

    <jsp:include page="navbar.jsp"></jsp:include>

    <div class="pcoded-main-container">
      <div class="pcoded-wrapper">
        <jsp:include page="pcoded-navbar.jsp"></jsp:include>
        <div class="pcoded-content">
          <!-- Page-header start -->
          <jsp:include page="page-header.jsp"></jsp:include>
          <!-- Page-header end -->
          <div class="pcoded-inner-content">
            <!-- Main-body start -->
            <div class="main-body">
              <div class="page-wrapper">
                <!-- Page-body start -->
                <div class="page-body">
                  <div class="row">
                    <!-- task, page, download counter  start -->
                    <div class="col-md-12">
                      <div class="card">
                        <div class="card-header">
                          <h5>Altere suas informações</h5>
                          <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                        </div>
                        <div class="card-block">
                    <form enctype="multipart/form-data" id="cadastro-form" class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioEdit" method="post">
                      <input type="hidden" id="acao" value="" name="acao">
                      <div class="form-group form-default">
                        <input type="hidden" name="id" id="id" class="form-control"  readonly="readonly" value="${ userInfos.id }">
                        <span class="form-bar"></span>
                        <label class="float-label">ID:</label>
                      </div>
                      <div class="form-group form-default input-group mb-3">
                        <div class="input-group-prepend">
                          <a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadfoto&id=${ userInfos.id }">
                            <img alt="Imagem usuário" id="foto64" src="${ userInfos.fotoUser }" width="70px"/>
                          </a>
                        </div>
                        <input name="fotouser" onchange="vizualizarImg('foto64', 'foto-user')" type="file" accept="image/*" class="form-control-file" id="foto-user"/>
                      </div>
                      <div class="form-group form-default">
                        <input type="text" name="nome" class="form-control" id="nome" autocomplete="off" value="${ userInfos == null ? '' : userInfos.nome }"
                               required=""> <span class="form-bar"></span> <label
                              class="float-label">Nome</label>
                      </div>
                      <div class="form-group form-default">
                        <input type="email" name="email" class="form-control" id="email" autocomplete="off" value="${ userInfos == null ? '' : userInfos.email }"
                               required=""> <span class="form-bar"></span> <label
                              class="float-label">Email (exa@gmail.com)</label>
                      </div>
                      <div class="form-group form-default">
                        <input type="text" name="login" class="form-control" id="login" autocomplete="off" value="${ userInfos == null ? '' : userInfos.login }"
                               required=""> <span class="form-bar"></span> <label
                              class="float-label">Login</label>
                      </div>
                      <div class="form-group form-default">
                        <input type="password" name="senha" class="form-control" id="senha" autocomplete="off" value="${ userInfos == null ? '' : userInfos.senha }"
                               required=""> <span class="form-bar"></span> <label
                              class="float-label">Senha</label>
                      </div>
                      <button class="btn btn-primary btn-round waves-effect waves-light">Salvar</button>
                      <a href="<%= request.getContextPath() %>/principal/view-profile.jsp" class="btn btn-success btn-round waves-effect waves-light">Cancela</a>
                    </form>
                        </div>
                      </div>
                    </div>
                    <!--  project and team member end -->
                  </div>
                </div>
                <!-- Page-body end -->
              </div>
              <div id="styleSelector"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script>
  function vizualizarImg(foto64, file){
    var preview = document.getElementById(foto64);
    var filePhoto = document.getElementById(file).files[0];

    var reader = new FileReader();

    reader.onloadend = function(){
      preview.src = reader.result; //carrega a foto na tela
    }

    if(file){
      reader.readAsDataURL(filePhoto); // preview da img
    } else {
      preview.src = "";
    }
  }
</script>
<!-- Required Jquery -->
<jsp:include page="javascript.jsp"></jsp:include>
</body>

</html>
