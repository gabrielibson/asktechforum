<%@ include file="/jsp/cabecalho.jsp"%>

<br />
<br />
<br />
<div id="site_content">
	<div class="content">
		<h1>Bye!</h1>
		<div class="content_item">
			<div class="form_settings_perfil">
				<form action="<%=getServletContext().getContextPath()%>/index.jsp">
					<h2>Usuário excluído com sucesso!</h2>
					<p>
						<button class="submit">Voltar</button>
					</p>
				</form>
			</div>
		</div>
	</div>
</div>
<br />
<br />
<br />

<jsp:include page="/jsp/rodape.jsp"></jsp:include>