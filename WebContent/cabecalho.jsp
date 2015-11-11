<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import= "asktechforum.dominio.Usuario" %>
<%@ page import= "asktechforum.dominio.Resposta" %>
<%@ page import= "asktechforum.negocio.VotoBC" %>
<%@ page import= "java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>
  <title>Ask Tech Forum </title>
  <meta name="description" content="website description" />
  <meta name="keywords" content="website keywords, website keywords" />
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" type="text/css" href="<%=getServletContext().getContextPath()%>/css/style.css" />
  <!-- modernizr enables HTML5 elements and feature detects -->
  <script type="text/javascript" src="js/modernizr-1.5.min.js"></script>
</head>

<%
if((Boolean)session.getAttribute("stop") == null) {
	session.setAttribute("stop", true);
}
%>

<body>
  <div id="main">
  	<div id="header">
      <div id="logo">
        <div id="logo_text">
			<!-- class="logo_colour", allows you to change the colour of the text -->
			<h1><a href="<%=getServletContext().getContextPath()%>/index.jsp">Ask Tech<span class="logo_colour">Forum</span></a></h1>
          
          	<%
          	Usuario usuarioLogado = new Usuario();
          	usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
          	if(usuarioLogado != null) {
	          	usuarioLogado.setSenha("");
          	}
          	Boolean saudacao = (Boolean)session.getAttribute("saudacao");
          	session.setAttribute("cabecalho", true);
          	%>
			
			<c:if test="${saudacao != true}">
	          	<h2><a href="login.jsp">Entre</a>&nbsp; <a href="cadastroUsuario.jsp">Cadastre-se</a></h2>
			</c:if>
			
			<c:if test="${saudacao == true}">
	         		<h2><span style="color: white">Olá</span>
	         			<a onclick="verificarUsuarioPerfil()" style="color: white" href="<%=getServletContext().getContextPath()%>/perfilUsuario.jsp">
	         				${usuarioLogado.nome}!
	         			</a>&nbsp;&nbsp;
	         			<%
	         			if(usuarioLogado != null) {
		         			if(usuarioLogado.isAdmin() == true) {
		         			%>
		         				<a href="<%=getServletContext().getContextPath()%>/cadastroUsuario.jsp">Cadastrar Usuário</a>&nbsp;&nbsp;
		         			<%
		         			}
	         			}
	         			%>
	         			<a href="<%=getServletContext().getContextPath()%>/ServletAutenticacaoUsuario?logout=true">Sair</a>
	         		</h2>
			</c:if>
        </div>
        
        <!-- Parte de pesquisa de pergunta. Será implementado na versão beta -->
<!--         <form method="post" action="#" id="search"> -->
<!--           <input class="search" type="text" name="search_field" value="search....." onclick="javascript: document.forms['search'].search_field.value=''" /> -->
<!--           <input name="search" type="image" style="float: right;border: 0; margin: 20px 0 0 0;" src="images/search.png" alt="search" title="search" /> -->
<!--         </form> -->

      </div>
       
        <ul class="sf-menu" id="nav">
       		<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=all">Perguntas</a></li>
        	<li><a href="<%=getServletContext().getContextPath()%>/pesquisarUsuario.jsp">Usuários</a></li>
         	<li><a href="#">Tags</a>
            <ul>
           		<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=java">Java</a></li>
             	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=android">Android</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=redes">Redes</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=javascript">JavaScript</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=sql">SQL</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=html">HTML</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=css">CSS</a></li>
	           	<li><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=allTags">Todas</a></li>
            </ul>
         	</li>
        	<li><a href="<%=getServletContext().getContextPath()%>/sobre.jsp">Sobre</a></li>
            <li><a href="<%=getServletContext().getContextPath()%>/usuarioAutenticado/CadastroPergunta.jsp">Pergunte</a></li>
        </ul>
      </div>
      
<script>
	function goBack() {
		window.history.go(-1);
	}
</script>

<script>
	function verificarUsuarioPerfil() {
		<%
			Usuario usuarioPerfil = (Usuario)session.getAttribute("usuarioPerfil");
			if(usuarioPerfil == null) {
				session.setAttribute("usuarioAlteracao", usuarioLogado);
				session.setAttribute("usuarioPerfil", usuarioLogado);
			}
		%>
	}
</script>
