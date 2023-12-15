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

										<div class="col-md-12">
											<div class="card">
												<div class="card-header">
													<h5>Consultar Usuário</h5>
													<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
												</div>
												<div class="card-block">
													<h6>${ msg }</h6>

													<form class="form-material"
														action="<%=request.getContextPath()%>/ServletConsultaUsuario"
														method="post">
														<input class="pb-5 pt-5" type="checkbox" name="metodo-busca" value="Login">
														<label for="login">Login </label>
														
														<input class="ms-3 pb-5 pt-5" type="checkbox" name="metodo-busca" value="Login1">
														<label for="login">E-mail </label>
														
														<input class="ms-3 pb-5 pt-5" type="checkbox" name="metodo-busca" value="Login2">
														<label for="login">Nome </label>
														<div class="form-group mt-5 form-default">
															<input placeholder="Digite o login" type="text"
																name="login-busca" class="form-control" id="login-busca">
															<span class="form-bar"></span> <label class="float-label">Busca
																pelo Login:</label>
														</div>
														<button
															class="btn btn-info btn-round waves-effect waves-light">Buscar Usuário</button>
													</form>
													<br><br>

													<form class="form-material"
														action="<%=request.getContextPath()%>/ServletUsuarioController"
														method="post">
														<div class="form-group form-default">
															<input placeholder="ID" type="text" name="id" class="form-control" id="id"
																value="${ modelLogin == null ? '' : modelLogin.id}"
																disabled> <span class="form-bar"></span> <label
																class="float-label">ID</label>
														</div>
														<div class="form-group form-default">
															<input type="text" name="nome" class="form-control"
																id="nome" autocomplete="off"
																value="${ modelLogin == null ? '' : modelLogin.nome }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Nome</label>
														</div>
														<div class="form-group form-default">
															<input type="email" name="email" class="form-control"
																id="email" autocomplete="off"
																value="${ modelLogin == null ? '' : modelLogin.email }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Email (exa@gmail.com)</label>
														</div>
														<div class="form-group form-default">
															<input type="text" name="login" class="form-control"
																id="login" autocomplete="off"
																value="${ modelLogin == null ? '' : modelLogin.login }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Login</label>
														</div>
														<div class="form-group form-default">
															<input type="password" name="senha" class="form-control"
																id="senha" autocomplete="off"
																value="${ modelLogin == null ? '' : modelLogin.senha }"
																required=""> <span class="form-bar"></span> <label
																class="float-label">Senha</label>
														</div>
														<button
															class="btn btn-primary btn-round waves-effect waves-light">Salvar</button>
														<button
															class="btn btn-success btn-round waves-effect waves-light">Novo</button>
														<button
															class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
													</form>
												</div>
											</div>
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


	<!-- Required Jquery -->
	<jsp:include page="javascript.jsp"></jsp:include>
</body>

</html>
