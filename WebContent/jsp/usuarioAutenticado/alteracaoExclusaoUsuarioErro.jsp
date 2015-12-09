<%@ include file="/jsp/cabecalho.jsp"%>

<br />
<br />
<br />
<div id="site_content">
	<div class="content">
		<h1>Ops!</h1>
		<div class="content_item">
			<div class="form_settings_perfil">
				<form>
					<h2>Atualmente, o Ask TechForum possui somente você como
						Administrador. Então, defina outro usuário para ser um
						Administrador do nosso sistema. Somente assim, você poderá deixar
						de ser Administrador, ou até mesmo excluir seu cadastro.</h2>
					<p>
						<input class="submit" value="Voltar" type="button"
							onclick="voltar()" />
					</p>
				</form>
			</div>
		</div>
	</div>
</div>
<%
	session.setAttribute("erroAlteracaoExclusao", false);
%>
<br />
<br />
<br />

<script>
	function voltar() {
		history.back();
	}
</script>

<jsp:include page="/jsp/rodape.jsp"></jsp:include>