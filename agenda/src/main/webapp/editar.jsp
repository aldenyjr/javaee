<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="PT-BR">
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.ico">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="container">
		<h1>Editar Novo Contato</h1>
		<form name="frmContato" class="frmContato" action="update" method="get">
			<table>
				<tr>
					<td>
						<label>Código</label> 
						<input type="text" name="idcon" class="caixa3" readonly value="<%out.print(request.getAttribute("idcon"));%>">
						<p class="obrigatorio">
					</td>
				</tr>
				<tr>
					<td><label>Nome</label> <input type="text" name="nome"
						class="caixa1" oninput="criaMascara()"
						value="<%out.print(request.getAttribute("nome"));%>">
						<p class="obrigatorio1"></p></td>
				</tr>
				<tr>
					<td><label>Fone</label><input type="tel" name="fone"
						class="caixa2" maxlength="11" value="<%out.print(request.getAttribute("fone"));%>"  oninput="criaMascara('fone')" >
						<p class="obrigatorio2"></p></td>
				</tr>
				<tr>
					<td><label>E-mail</label><input type="email" name="email"
						class="caixa1"
						value="<%out.print(request.getAttribute("email"));%>">
						<p class="obrigatorio3"></p></td>
				</tr>
			</table>
			<input class="botao1" type="button" value="Salvar"
				onclick="validar()">
		</form>
		
	</div>
		<div class="btn-container">

		<div class="btn-return">
			<p>Voltar</p>
			<a href="main"><svg viewBox="0 0 256 256"
					xmlns="http://www.w3.org/2000/svg">
		<rect fill="none" height="256" width="256" />
		<path fill="rgb(102, 187, 255)"
						d="M216,40H40A16,16,0,0,0,24,56V200a16,16,0,0,0,16,16H216a16,16,0,0,0,16-16V56A16,16,0,0,0,216,40Zm-32,96a8,8,0,0,1-8,8H99.3l10.4,10.3a8.1,8.1,0,0,1,0,11.4,8.2,8.2,0,0,1-11.4,0l-24-24a8.1,8.1,0,0,1,0-11.4l24-24a8.1,8.1,0,0,1,11.4,11.4L99.3,128H168V104a8,8,0,0,1,16,0Z" /></svg>

			</a>
		</div>
	</div>

	<script src="scripts/validador.js"></script>
</body>
</html>