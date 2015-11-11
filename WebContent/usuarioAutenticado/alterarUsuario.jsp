<%@ include file="/cabecalho.jsp" %>

<br/>
<br/>
<br/>

<form id="formAlteracaoPerfilUsuario1" action="<%=getServletContext().getContextPath()%>/ServletAlteracaoUsuario" method="post">
<%
	session.setAttribute("usuarioAdministrador", (Usuario)session.getAttribute("usuarioAlteracao"));
%>
	
	<input type="hidden" name="alteracaoUsuarioId" id="alteracaoUsuarioId" value="${usuarioAlteracao.idUsuario}" checked="checked" />
	<%
	Usuario perfilUsuario = new Usuario();
	Usuario perfilUsuarioLogado = new Usuario();
    perfilUsuario = (Usuario)session.getAttribute("usuarioPerfil");
	perfilUsuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
	Boolean cabecalho = (Boolean)session.getAttribute("cabecalho");
    Boolean flag = false;
	if(perfilUsuario != null && perfilUsuarioLogado != null) {
		flag = perfilUsuario.getEmail().equals(perfilUsuarioLogado.getEmail());
		if(flag == false) {
			cabecalho = false;
		}
	}
	if(perfilUsuario == null && flag == false) {
		cabecalho = true;
	}
	%>

	<% 
	if(flag!= null && flag == true || cabecalho == true) {
	%>
		<div id="site_content">
			<div class="content">
				<h1>Editar Perfil</h1>
				<div class="content_item">
					<div style="width: 700px;" class="form_settings_cadastro">
						<p>
							<span>Nome* </span>
							<br/>
							<input class="contact" value="${usuarioAlteracao.nome}" type="text" name="nome" id="nome" />
						</p>
						<p>
							<span>Data de Nascimento </span>
							<br/>
							<input class="contact" type="text" value="${usuarioAlteracao.dataString}" name="dataNascimento" id="dataNascimento" placeholder="dd/mm/aaaa" />
						</p>
						<p>
							<span>E-mail* </span>
							<br/>
							<input class="contact" value="${usuarioAlteracao.email}" type="email" name="email" id="email" placeholder="nome@exemplo.com" />
						</p>
						<p>
							<span>Localização </span>
							<br/>
							<input class="contact" value="${usuarioAlteracao.localizacao}" type="text" name="localizacao" id="localizacao" placeholder="Cidade/Estado" />
						</p>
						<p>
							<span>Senha* </span>
							<br/>
							<input class="contact" value="${usuarioAlteracao.senha}" type="password" name="senha" id="senha" placeholder="Senha" />
							<span style="color:rgb(10,122,146); font-size:12px;">O campo 'Senha' deve conter no máximo 8 caracteres</span> 
						</p>
					    <p>
					    	<span>Confirmar Senha* </span>
							<br/>
					    	<input class="contact" value="${usuarioAlteracao.confSenha}" type="password" name="confsenha" id="confsenha" placeholder="Repetir Senha" />
							<span style="color:rgb(10,122,146); font-size:12px;">Os campos 'Senha' e 'Confirmar Senha'
								devem ser iguais</span> 
					    </p>
						<c:if test="${usuarioAlteracao.admin == true}">
							<p>
								<input style="width:20px;" value="${usuarioAlteracao.admin}" type="checkbox" checked="checked" name="admin" id="admin" />
								<label for="admin" >Administrador</label>
							</p>
						</c:if>
						<br/>
						<p style="color:red; font-size:12px;">Os campos com * são obrigatórios, preencha-os com dados válidos</p> 
						
						<c:if test="${emailExistente == true}">
							<p style="color:red; font-size:12px;">Este Email já está cadastrado no AskTechForum.<br/>Tente outro ;)</p> 
						</c:if>
					
						<p>
							<input class="submit" value="Salvar" type="submit" /> 
							<input class="submit" value="Voltar" type="button" onclick="voltar()" />
						</p>	 
						<br />
						<br />
						<br />
					</div>	
				</div>
			</div>
		</div>
<%	
	session.setAttribute("usuarioAlteracao", null);
	session.setAttribute("usuarioPerfil", null);
	} 
%>
	
</form>
	
<form id="formAlteracaoPerfilUsuario2" action="<%=getServletContext().getContextPath()%>/ServletAlteracaoUsuario" method="post">
	
	<% 
	if(flag == false && cabecalho == false) {
	%>
	<div id="site_content">
		<div class="content">
			<h1>Definir Usuário Administrador</h1>
			<div class="content_item">
				<p>
					<h2>Para definir o usuário ${usuarioAdministrador.nome} como um Administrador do AskTechForum, selecione a opção abaixo e clique em Salvar:</h2>
				</p>
				<div style="width: 300px;" class="form_settings_cadastro">
					
					<c:if test="${usuarioAdministrador.admin == true}">
						<p>
							<input style="width:20px;" type="checkbox" checked="checked" id="admin" name="admin" id="admin" />
							<label for="admin" >Administrador</label>
						</p>
					</c:if>
					
					<c:if test="${usuarioAdministrador.admin == false}">
						<p>
							<input style="width:20px;" type="checkbox" id="admin" name="admin" id="admin" />
							<label for="admin" >Administrador</label>
						</p>
					</c:if>
					
					<p>
						<input type="hidden" name="alteracaoAdminId" id="alteracaoAdminId" value="${usuarioAdministrador.idUsuario}" checked="checked" />
					</p>
					 
					<p>
						<input class="submit" value="Salvar" type="submit" /> 
						<input class="submit" value="Voltar" type="button" onclick="voltar()" />
					</p>
					
					<br />
					<br />
					<br />
				</div>	
			</div>
		</div>
		
	</div>
<%	
	session.setAttribute("usuarioAlteracao", null);
	session.setAttribute("usuarioPerfil", null);
	}
%>
	
</form>
<br/>
<br/>
<br/>

<script>
	function voltar() {
		history.back();
	}
</script>

<jsp:include page="/rodape.jsp"></jsp:include>