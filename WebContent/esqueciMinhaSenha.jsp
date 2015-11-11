<%@ include file="cabecalho.jsp"%>

<form id="formRecuperarSenha" action="ServletRecuperarSenha" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Esqueceu sua Senha?</h1>
			<div class="content_item">
				<div style="width: 700px;" class="form_settings_cadastro">

					<p>
						<h2>Insira abaixo seu e-mail cadastrado, para que o Ask TechForum encaminhe sua senha:</h2>
						<span>E-mail </span>
						<input type="email" name="email" placeholder="nome@exemplo.com" /><br/><br/>
						<input class="submit" type="submit" />
					</p>
					
				</div>	
			</div>
		</div>
	</div>
</form>

<jsp:include page="rodape.jsp"></jsp:include>