<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="icon" href="imagem/telefone.png">

<link rel="stylesheet" href="style.css">
<title>Editor de contato</title>
</head>
<body>
	<h1>Editar contato</h1>
	<form name="frmContato" action="update">

		<table>
			<tr>
			<--Atributo recebidos da classe Controller -->
				<td><input type="text" id="Caixa3" name="id" placeholder="id"
					readonly="readonly" value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" class="Caixa1" name="Nome"
					value="<%out.print(request.getAttribute("Nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" class="Caixa2" name="Telefone"
					value="<%out.print(request.getAttribute("Telefone"));%>"></td>
			</tr>
			<tr>
				<td><input type="email" class="Caixa1" name="Email"
					value="<%out.print(request.getAttribute("Email"));%>"></td>
			</tr>

		</table>

		<input type="button" class="Botao1" value="Editar"
			onclick="validar()">

	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>