<%@ include file="cabecalho.jsp"%>

<form method="post" action="../ServletAutenticacaoUsuario" id="formLogin">

	<div id="site_content">
		<div class="content">
			<h1>Login</h1>
			<div class="content_item">
				<div class="form_settings_login">
					<p>
						<span>E-mail  </span><input class="contact" type="email"
							name="email" value="" placeholder="nome@exemplo.com"/>
					</p>
					<p>	<span>Senha</span>
						<input class="contact" type="password" name="senha" placeholder="senha"/>
						<a href="esqueciMinhaSenha.jsp" style="font-size: 10px">Esqueci minha senha</a>
					</p>
					
					<%String erro = (String)session.getAttribute("erro"); %>
					<c:if test="${erro == null}">
						<% session.setAttribute("erro", ""); %>
					</c:if>
					<p style="font-size: 12px; color: red;" id= msg><%=session.getAttribute("erro")%></p>
					<p style="padding-top: 15px"><span>&nbsp;</span><input class="submit" type="submit" name="btn_entrar" value="Entrar"/>
					<a href="cadastroUsuario.jsp"><input  class="submit" name="btn_cadastrar" type="button" value="Cadastrar"></a></p>
					<% session.setAttribute("erro", ""); %>
	
					</div>
			</div>
		</div>
	</div>
	
</form>

<jsp:include page="rodape.jsp"></jsp:include>