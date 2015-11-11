<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<div id="site_content">
	<div class="content">
		<h1>Perfil de ${usuarioPerfil.nome}</h1>
		<div class="content_item">
			<div class="form_settings_perfil">
				<form id="formPerfilUsuario" action="ServletAlteracaoUsuario" method="post">
					<%
					session.setAttribute("usuarioExcluido", (Usuario)session.getAttribute("usuarioPerfil"));
					session.setAttribute("proprioPerfil", (Usuario)session.getAttribute("usuarioPerfil"));
					%>
					<p>
						<span>Nome: </span>
						<c:if test="${not empty usuarioPerfil.nome}">
							<label>${usuarioPerfil.nome}</label>
						</c:if>
						<c:if test="${empty usuarioPerfil.nome}">
							<label>Não encontrado</label>
						</c:if>
					</p>
					<p>
						<span>Data de Nascimento: </span>
						<c:if test="${empty usuarioPerfil.dataNascimento}">
							<label>Preferiu não informar =)</label>
						</c:if>
						<c:if test="${not empty usuarioPerfil.dataNascimento}">
							<label><fmt:formatDate pattern="dd/MM/yyyy" value="${usuarioPerfil.dataNascimento}"/></label>
						</c:if>
					</p>
					<p>
						<span>E-mail: </span>
						<c:if test="${not empty usuarioPerfil.email}">
							<label>${usuarioPerfil.email}</label>
						</c:if>
						<c:if test="${empty usuarioPerfil.email}">
							<label>Não encontrado</label>
						</c:if>
						<input type="hidden" name="pesquisaUsuarioEmail" id="pesquisaUsuarioEmail" value="${usuarioPerfil.email}" checked="checked" />
					</p>
					<p>
						<span>Localização: </span>
						<c:if test="${empty usuarioPerfil.localizacao}">
							<label>Preferiu não informar =)</label>
						</c:if>
						<c:if test="${not empty usuarioPerfil.localizacao}">
							<label>${usuarioPerfil.localizacao}</label>
						</c:if>
					</p>
					<p>
						<span>Administrador </span>
						<c:if test="${not empty usuarioPerfil.admin}">
							<c:if test="${usuarioPerfil.admin == false}"><label>Não</label></c:if>
							<c:if test="${usuarioPerfil.admin == true}"><label>Sim</label></c:if>
						</c:if>
						<c:if test="${empty usuarioPerfil.admin}">
							<label>Não encontrado</label>
						</c:if>
					</p>
					<%
          				session.setAttribute("usuarioPerfil", null);
					%>
				</form>
				
				<form id="formExclusaoPerfilUsuario" action="ServletExclusaoUsuario" method = "post">
					<input type="hidden" name="exclusaoUsuarioEmail" id="exclusaoUsuarioEmail" value="${usuarioExcluido.email}" checked="checked" />
				</form>
				
				<form action="pesquisarUsuario.jsp">
					<p>
						<%
						Usuario proprioPerfil = new Usuario(); 
						Usuario perfilUsuarioLogado = new Usuario(); 
						proprioPerfil = (Usuario)session.getAttribute("proprioPerfil"); 
						perfilUsuarioLogado = (Usuario)session.getAttribute("usuarioLogado"); 
          				Boolean saudacaoPerfil = (Boolean)session.getAttribute("saudacao"); 
          				Boolean flag = false;
          				
          				
          				if(saudacaoPerfil != null && saudacaoPerfil == true) {
							if(proprioPerfil != null && perfilUsuarioLogado != null) {
								flag = proprioPerfil.getEmail().equals(perfilUsuarioLogado.getEmail());
							}else if(proprioPerfil == null) {
								flag = true;
							}

							if(flag!= null && flag == true && perfilUsuarioLogado.isAdmin() == false) {
							%>
								<input value="Editar Perfil" type="button" class="submit" onclick="alterarUsuario()" />
								<input value="Excluir Cadastro" type="button" class="submit" onclick="excluirUsuario()" />
							<%
							}  
							if(perfilUsuarioLogado.isAdmin() == true) {
							%>
								<input value="Editar Perfil" type="button" class="submit" onclick="alterarUsuario()" />
								<input value="Excluir Cadastro" type="button" class="submit" onclick="excluirUsuario()" />
							<%
					  		}
					  	}
          				session.setAttribute("usuarioPerfil", null);
					  	%>
					  	
						<button class="submit" >Voltar</button>
					</p> 
				</form>
			</div>
		</div>
	</div>
</div>
<br />
<br />
<br />

<script>
	function alterarUsuario() {
		var formulario;
		formulario = document.getElementById("formPerfilUsuario");
		formulario.submit();
	}
</script>

<script>
	function excluirUsuario() {
		alert("=( Que pena! Você está deixando o Ask TechForum. Toda nossa comunidade está de luto por 3 dias. Nós esperamos que você retorne logo.");
		var formulario;
		formulario = document.getElementById("formExclusaoPerfilUsuario");
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>