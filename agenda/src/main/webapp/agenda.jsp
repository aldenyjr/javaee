<%@ page language="java"
	contentType="text/html; charset=ISO-8859-1UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@ SuppressWarnings ("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
%>


<!DOCTYPE html>
<html lang="PT-BR">
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.ico">
<link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="modal-container">
      <div class="modal">
        <h2>Exluir Contato?</h2>
        <hr />
        <span>Voce realmente desejar excluir o contato: <span class="nomecontato"></span>?</span
        >
        <hr />
        <div class="btns">
          <a class="botao1 btnOK">OK</a>
          <a class="botao2" onclick="closeModal()">Cancelar</a>
        </div>
      </div>
    </div>
	<div class="container">
		<a href="index.html"><img src="imagens/agenda.png" /></a>
		<h1>Agenda de Contatos</h1>
		<div>
		<a href="novo.html" class="botao1">Novo Contato</a>
		<a href="report" target="_blank" class="botao2">Relatório</a>
		</div>
	</div>
	<div>
		<table id="tabela">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Telefone</th>
					<th>E-mail</th>
					<th>Opções</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<tr>
					<td><%=lista.get(i).getIdcon()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>"
						class="botao1">Editar</a>
						<a href="javascript: openModal(<%=lista.get(i).getIdcon() %>, '<%=lista.get(i).getNome() %>')" class="botao2">Excluir</a>				
						</td>
				</tr>
				<%
				}
				%>


			</tbody>
		</table>
	</div>

	<div class="btn-container">

		<div class="btn-return">
			<p>Voltar</p>
			<a href="index.html"><svg viewBox="0 0 256 256"
					xmlns="http://www.w3.org/2000/svg">
		<rect fill="none" height="256" width="256" />
		<path fill="rgb(102, 187, 255)"
						d="M216,40H40A16,16,0,0,0,24,56V200a16,16,0,0,0,16,16H216a16,16,0,0,0,16-16V56A16,16,0,0,0,216,40Zm-32,96a8,8,0,0,1-8,8H99.3l10.4,10.3a8.1,8.1,0,0,1,0,11.4,8.2,8.2,0,0,1-11.4,0l-24-24a8.1,8.1,0,0,1,0-11.4l24-24a8.1,8.1,0,0,1,11.4,11.4L99.3,128H168V104a8,8,0,0,1,16,0Z" /></svg>

			</a>
		</div>
	</div>
<script type="text/javascript" src="scripts/confirmador.js"></script>
<script type="text/javascript" src="scripts/popup.js"></script>

</body>
</html>