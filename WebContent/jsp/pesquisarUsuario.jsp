<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<div id="site_content">
	<div class="content">
		<h1>Pesquisar Usuários</h1>
		<div class="content_item">
			<form id="formPesquisaUsuario" action="ServletPesquisaUsuario" method="post">
			
				<table border="0">
					<td>
						<input type="radio" name="pesquisaRadio" id="nome" value="nomeRadio" />
						<br />
						<br />
						<input type="radio" name="pesquisaRadio" id="email" value="emailRadio" />
						<br />
						<br />
						<input type="radio" name="pesquisaRadio" id="listarTodos" value="listartodosRadio" checked="checked" />
					</td>
					<td>
						<label for="nome"> Nome: </label><input class="input" value="${usuario.nome}" type="text" name="nome" id="nome" />
						<br />
						<br /> 
						<label for="email"> E-mail: </label><input class="input" value="${usuario.email}" type="text" name="email" id="email" />
						<br/>
						<br />
						<label for="listarTodos">Listar Todos</label>
					</td>
				</table>	
				<p style="color:red; font-size:12px;">* Selecione uma das opções acima para pesquisar.</p> 
				<input value="Pesquisar" type="submit" class="submit"/> 
				<br/><br/>
			</form>				
			
			<form id="formPerfilUsuario" action="ServletPerfilUsuario"
				method="post">
	
				<c:if test="${not empty usuarios}">
					<table id="tabelaListaUsuarios" border="0">
						<thead>
							<tr class="tabelaPesquisa">
								<td></td>
								<td>Nome</td>
								<td>Data de Nascimento</td>
								<td>Email</td>
								<td>Localização</td>
								<td>Administrador</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usuarios}" var="usuarios">
								<c:if test="${usuarios.nome != 'Usuário Excluído'}">
									<tr>
										<td><input type="radio" name="usuarioRadio" id="usuario" value="${usuarios.email}" /></td>
										<td><c:out value="${usuarios.nome}" /></td>
										<td><fmt:formatDate pattern="dd/MM/yyyy" value="${usuarios.dataNascimento}"/></td>
										<td><c:out value="${usuarios.email}" /></td>
										<td><c:out value="${usuarios.localizacao}" /></td>
										<c:if test="${usuarios.admin == true}">
											<td><label>Sim</label></td>
										</c:if>
										<c:if test="${usuarios.admin == false}">
											<td><label>Não</label></td>
										</c:if>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
					<p style="color:red; font-size:12px;">* Selecione um dos usuários acima para exibir.</p> 
					<input value="Exibir Perfil" type="button" class="submit" onclick="validarExibirUsuario()"/> 
				</c:if>
			</form>		
		</div>
	</div>
</div>
<br />
<br />
<br />

<script>
	function validarExibirUsuario() {
		var radio;
		var formulario = document.getElementById("formPerfilUsuario");
		var radioButton = document.getElementsByName("usuarioRadio");
		
		for(var i = 0; i < radioButton.length; i++) {
			radio = radioButton.item(i);
			if(radio.checked == true) {
				formulario.submit();
			}
		}
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>