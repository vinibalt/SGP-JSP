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
                                    <div class="row">
                                        <!-- task, page, download counter  start -->
                                        <div class="col-md-12">
                                            <div class="card">
                                                <div class="card-header">
                                                    <h5>Informações do perfil</h5>
                                                    <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                </div>
                                                <div class="card-block">
                                            <div class="row gutters-sm ">
                                                <div class="col-md-6">
                                                <div class="card width-100">
                                                    <div class="card-body">
                                                        <div class="d-flex flex-column align-items-center text-center">

                                                            <c:if test="${ userInfos.fotoUser != null }">
                                                                <img src="${ userInfos.fotoUser }" alt="Admin" class="rounded-circle" width="150">
                                                            </c:if>

                                                            <c:if test="${ userInfos.fotoUser == null }">
                                                                <img src="<%= request.getContextPath() %>/assets/images/perfil-sem-foto.png" alt="Admin"  class="rounded-circle" width="150">
                                                            </c:if>
                                                            <div class="mt-3">
                                                                <h4><c:out value="${ userInfos.nome }"/></h4>
                                                                <p class="text-secondary mb-1">Full Stack Developer</p>
                                                                <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                                <div class="col-md-6">
                                                    <div class="card w-100">
                                                        <div class="card-body">
                                                            <div class="row">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">Nome</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <c:out value="${ userInfos.nome }"/>
                                                                </div>
                                                            </div>
                                                            <hr>
                                                            <div class="row">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">Email</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <c:out value="${ userInfos.email }"/>
                                                                </div>
                                                            </div>
                                                            <hr>
                                                            <div class="row">
                                                                <div class="col-sm-3">
                                                                    <h6 class="mb-0">Login</h6>
                                                                </div>
                                                                <div class="col-sm-9 text-secondary">
                                                                    <c:out value="${ userInfos.login }"/>
                                                                </div>
                                                            </div>
                                                            <hr>
                                                            <div class="row">
                                                                <div class="col-sm-12">
                                                                    <a class="btn btn-info " href="<%= request.getContextPath() %>/principal/user-edit.jsp">Edit</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
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


<!-- Required Jquery -->
<jsp:include page="javascript.jsp"></jsp:include>
</body>

</html>
