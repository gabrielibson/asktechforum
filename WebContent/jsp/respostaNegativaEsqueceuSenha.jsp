<%@ include file="cabecalho.jsp"%>

<form id="formEmailInvalido" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Ops! Usu�rio N�o Encontrado!</h1>
			<div class="content_item">
				<div style="width: 700px;" class="form_settings_cadastro">

					<h2>O e-mail informado n�o est� cadastrado no Ask TechForum. Por favor, tente novamente com seu e-mail cadastrado!</h2>
					<p><input class="submit" type="button" onclick="voltar()" value="Voltar" /></p>

				</div>	
			</div>
		</div>
	</div>
</form>

<script>
	function voltar() {
		history.back();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>