<%@ page language="java" contentType="text/html; charset=utf-8 "
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Agenda de contatos</title>
<link rel="icon" href="imagens/telefone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Agenda de contatos</h1>
	<a = href="novo.html" class="Botao1">Novo Contato</a>
	<a = href="report" class="Botao2">Relatorio</a>
	
	<table id = "tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>E-mail</th>
				<th>Op��es</th>

			</tr>
		</thead>
		<tbody>

			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getTelefone()%></td>
				<td><%=lista.get(i).getEmail()%></td>
				<td><a href = "select?idcon=<%=lista.get(i).getId() %>" class = "Botao1">Editar
				<a href="javascript: confirmar(<%=lista.get(i).getId()%>)"
					class="Botao2">Excluir</a></td>

			</tr>

			<%
			}
			%>
		</tbody>

	</table>
	<script  src = "scripts/confirmador.js"></script>
</body>
</html>
