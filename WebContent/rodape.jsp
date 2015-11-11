  <footer>
      <p><a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=all">Perguntas</a> | <a href="<%=getServletContext().getContextPath()%>/pesquisarUsuario.jsp">Usuários</a> | <a href="<%=getServletContext().getContextPath()%>/sobre.jsp">Sobre</a> | <a href="<%=getServletContext().getContextPath()%>/usuarioAutenticado/CadastroPergunta.jsp">Pergunte</a></p>
      <p>Copyright &copy; Ask Tech Foum | 2014</p>
    </footer>
  </div>
  <!-- javascript at the bottom for fast page loading -->
  <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript" src="js/jquery.easing-sooper.js"></script>
  <script type="text/javascript" src="js/jquery.sooperfish.js"></script>
</body>
<%
session.setAttribute("stop", true);
%>
</html>
