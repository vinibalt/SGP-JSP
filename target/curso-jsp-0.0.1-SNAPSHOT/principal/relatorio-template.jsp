<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ModelLogin" %>

<html>
<head>
  <title>Template PDF</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }

    th {
      background-color: #f2f2f2;
    }
  </style>
</head>
<body>
<h1>Relatório Dinâmico</h1>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Login</th>
  </tr>
  </thead>
  <tbody>
  <c:out value="${listaDados}" />
  <c:forEach var="dados" items="${listaDados}">
    <tr>
      <td>${dados.id}</td>
      <td>${dados.nome}</td>
      <td>${dados.login}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
