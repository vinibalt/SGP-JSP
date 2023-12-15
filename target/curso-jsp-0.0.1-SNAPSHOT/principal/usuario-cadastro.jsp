<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



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

										<!-- task, page, download counter  start -->
										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h5>Cadastro de Usuário</h5>
													<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
												</div>
												<div class="card-block">
													<h6>${ msg }</h6>
													<form enctype="multipart/form-data" id="cadastro-form" class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post">
														<input type="hidden" id="acao" value="" name="acao">
														<div class="form-group form-default">
															<input type="text" name="id" id="id" class="form-control"  readonly="readonly" value="${ modelLogin.id }">
															<span class="form-bar"></span>
															<label class="float-label">ID:</label>
														</div>
														<div class="form-group form-default input-group mb-3">
															<div class="input-group-prepend">
																<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=downloadfoto&id=${ modelLogin.id }">
																	<img alt="Imagem usuário" id="foto64" src="${ modelLogin.fotoUser }" width="70px"/>
																</a>
															</div>
															<input name="fotouser" onchange="vizualizarImg('foto64', 'foto-user')" type="file" accept="image/*" class="form-control-file" id="foto-user"/>
														</div>
														<div class="form-group form-default">
															<input type="text" name="nome" class="form-control" id="nome" autocomplete="off" value="${ modelLogin == null ? '' : modelLogin.nome }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Nome</label>
														</div>
														<div class="form-group form-default">
															<input type="email" name="email" class="form-control" id="email" autocomplete="off" value="${ modelLogin == null ? '' : modelLogin.email }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Email (exa@gmail.com)</label>
														</div>
														<div class="form-group form-default">
															<input type="text" name="login" class="form-control" id="login" autocomplete="off" value="${ modelLogin == null ? '' : modelLogin.login }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Login</label>
														</div>
														<div class="form-group form-default">
															<label class="">O usuário terá função de administrador?</label>
															<div class="form-check">
																<label class="form-check-label" for="exampleRadios1">
																	Sim
																</label>
																<input class="form-check-input" type="radio" name="admin" id="exampleRadios1" value="1" <c:if test="${modelLogin.admin == 1}">checked</c:if> >
															</div>
															<div class="form-check">
																<label class="form-check-label" for="exampleRadios2">
																	Não
																</label>
																<input class="form-check-input" type="radio" name="admin" id="exampleRadios2" value="0" <c:if test="${modelLogin.admin == 0}">checked</c:if> >
															</div>

														</div>
														<div class="form-group form-default">
															<input type="password" name="senha" class="form-control" id="senha" autocomplete="off" value="${ modelLogin == null ? '' : modelLogin.senha }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Senha</label>
														</div>
														<button class="btn btn-primary btn-round waves-effect waves-light">Salvar</button>
														<button onClick="limparForm()" class="btn btn-success btn-round waves-effect waves-light">Novo</button>
														<button onClick="criarDeleteAjax()" type="button" class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
														<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
															Buscar Usuário
														</button>
														<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=listartodosusuarios" type="button" class="btn btn-primary">
															Listar Usuários
														</a>
														<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=gerarrelatorio" type="button" class="btn btn-primary">
															Relatório de Usuários
														</a>
														<a href="<%= request.getContextPath() %>/ServletUsuarioController?acao=gerarhistoricoacessos" type="button" class="btn btn-primary">
															Relatório de Histórico de Acessos
														</a>
													</form>

													<table class="table m-t-5">
														<thead>
														<tr>
															<th scope="col">ID</th>
															<th scope="col">Nome</th>
															<th scope="col">Email</th>
															<th scope="col">Ver</th>
														</tr>
														</thead>
														<tbody id="tbody2">
														<c:if test="${usuarios != null}">
														<c:forEach items='${usuarios}' var="u">
															<tr>
																<td><c:out value="${u.id}"></c:out></td>
																<td><c:out value="${u.nome}"></c:out></td>
																<td><c:out value="${u.email}"></c:out></td>
																<td><a class="btn btn-success" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${u.id}" >Ver</a></td>
															</tr>
														</c:forEach>
														</c:if>
														</tbody>
													</table>
													<c:if test="${usuarios == null}">
															<div style="width: 100%; padding: 8px 16px 8px 16px; text-align: center;">
																Sem resultados. Tente o botão Listar Usuários!
															</div>
													</c:if>
												</div>
											</div>
										</div>


										<!--  project and team member end -->

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


	<!-- Required Jquery -->
	<jsp:include page="javascript.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input id="nomeBusca" type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button onClick="buscarUsuario()" class="btn btn-outline-secondary" type="button">Buscar</button>
						</div>
					</div>

					<div style="height: 300px; overflow-y: scroll;">
					<table class="table">
						<thead>
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Nome</th>
							<th scope="col">Email</th>
							<th scope="col">Ver</th>
						</tr>
						</thead>
						<tbody id="tbody">

						</tbody>
					</table>
					</div>
				</div>
				<div class="modal-footer">
					<button onClick="limparCamposBusca()" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<script>

	function verEditar(id) {

		var urlAction = document.getElementById('cadastro-form').action;


		window.location.href = urlAction + '?acao=buscareditar&id='+id;

	}


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

 function deletar() {
	 if(confirm("Deseja realmente deletar o usuario?")){
		 document.getElementById("cadastro-form").method = "get";
		 document.getElementById("acao").value = "deletar";
		 document.getElementById("cadastro-form").submit();
	 }

 }

 function limparForm(){
	 var inputs = document.getElementById('cadastro-form').getElementsByTagName('input');

	 for (var i = 0; i < inputs.length; i++) {
		 inputs[i].value = '';
	 }
 }

 function listarTodosUsuarios(){

	 urlAction = document.getElementById("cadastro-form").action;

	 $.ajax({
		 method: "get",
		 url: urlAction,
		 data: "acao=listartodosusuariosajax",
		 dataType: "json",
		 success: function (response){

			 var dadosRetornados = response;


			 if(dadosRetornados.length != 0){
				 tbody = document.getElementById("tbody2");
				 tbody.innerHTML = "";

				 dadosRetornados.forEach((e) => {
					 tbody.innerHTML += "<tr><th>"+ e.id +"</th><td>"+ e.nome +"</td><td>"+ e.email +"</td><td><button onClick='limparCamposBusca(); verEditar(" + e.id + ");' type='button' class='btn btn-primary' data-dismiss='modal' >Ver</button></td></tr>";
				 })

		 }
	  }
	 }).fail(function (xhr, status, errorThrown)
	 {
		 alert("Erro ao buscar usuário" + xhr.responseText);
	 })

 }

 function trazerDados(id){

	 urlAction = document.getElementById("cadastro-form").action;

	 $.ajax({
		 method: "get",
		 url: urlAction,
		 data: "id=" + id + "&acao=preencherajax",
		 dataType: "json",
		 success: function (response){

			 usuario = response;

			 idUser = document.getElementById("id");
			 fotoUser = document.getElementById("foto64")
			 nome = document.getElementById("nome");
			 email = document.getElementById("email");
			 login = document.getElementById("login");
			 senha = document.getElementById("senha");

			 console.log(usuario);

			 idUser.value = usuario.id;
			 fotoUser.src = usuario.fotoUser;
			 nome.value = usuario.nome;
			 email.value = usuario.email;
			 login.value = usuario.login;
			 senha.value = usuario.senha;

			 console.log(fotoUser.src)
		 }
	 }).fail(function (xhr, status, errorThrown)
	 {
		 alert("Erro ao buscar usuário" + xhr.responseText);
	 })

 }

 function limparCamposBusca(){
	 tbody = document.getElementById("tbody");
	 nomeBusca = document.getElementById("nomeBusca")
	 tbody.innerHTML = "";
	 nomeBusca.value = "";

 }

 function buscarUsuario(){
	 nomeBusca = document.getElementById("nomeBusca").value;

	 urlAction = document.getElementById("cadastro-form").action;

	 if(nomeBusca != null && nomeBusca != ''){
		 $.ajax({
			 method: "get",
			 url: urlAction,
			 dataType: 'json',
			 data: "nomeBusca=" + nomeBusca + "&acao=buscaruserajax",
			 success: function (response){

				 dadosRetornados = response;


				 if(dadosRetornados.length != 0){
					 tbody = document.getElementById("tbody");
					 tbody.innerHTML = "";

					 dadosRetornados.forEach((e) => {
						 tbody.innerHTML += "<tr><th>"+ e.id +"</th><td>"+ e.nome +"</td><td>"+ e.email +"</td><td><button onClick='limparCamposBusca(); verEditar(" + e.id + ");' type='button' class='btn btn-primary' data-dismiss='modal' >Ver</button></td></tr>";
					 })


					 // $("#tbody").empty();
					 //
					 // if (response.length !== 0) {
						//  // Itera sobre os dados retornados e adiciona as linhas à tabela
						//  $.each(response, function (index, e) {
						// 	 var row = $("<tr>")
						// 			 .append($("<th>").text(e.id))
						// 			 .append($("<td>").text(e.nome))
						// 			 .append($("<td>").text(e.email))
						// 			 .append($("<td>").text("Ver"));
					 //
						// 	 $("#tbody").append(row);
						//  });
					 // }

				 }
			 }
		 }).fail(function (xhr, status, errorThrown)
		 {
			 alert("Erro ao buscar usuário" + xhr.responseText);
		 })
	 }

 }

 function criarDeleteAjax(){
	 if(confirm("Deseja realmente exluir o usuario?")){
		 urlAction = document.getElementById("cadastro-form").action;
		 idUser = document.getElementById("id").value;

		 $.ajax({
			method: "get",
			 url: urlAction,
			 data: "id=" + idUser + "&acao=deletarajax",
			 success: function (response){
				alert(response);
			 }
		 }).fail(function (xhr, status, errorThrown)
		 {
			 alert("Erro ao deletar usuário por id" + xhr.responseText);
		 })
	 }
 }

</script>
</html>
