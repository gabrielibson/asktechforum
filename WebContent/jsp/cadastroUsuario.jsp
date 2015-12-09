<%@ include file="cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formCadastroUsuario" action="ServletCadastroUsuario" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Cadastro</h1>
			<div class="content_item">
				<div style="width: 700px;" class="form_settings_cadastro">
					<p>
						<span>Nome* </span>
						<br/>
						<input class="contact" value="${usuario.nome}" type="text" name="nome" id="nome" />
					</p>
					<p>
						<span>Data de Nascimento </span>
						<br/>
						<input class="contact" type="text" value="${usuario.dataString}" name="dataNascimento" id="dataNascimento" placeholder="dd/mm/aaaa" />
					</p>
					<p>
						<span>E-mail* </span>
						<br/>
						<input class="contact" value="${usuario.email}" type="email" name="email" id="email" placeholder="nome@exemplo.com" />
					</p>
					<p>
						<span>Localização </span>
						<br/>
						<input class="contact" value="${usuario.localizacao}" type="text" name="localizacao" id="localizacao" placeholder="Cidade/Estado" />
					</p>
					<p>
						<span>Senha* </span>
						<br/>
						<input class="contact" value="${usuario.senha}" type="password" name="senha" id="senha" placeholder="Senha" />
						<span style="color:rgb(10,122,146); font-size:12px;">O campo 'Senha' deve conter no máximo 8 caracteres</span> 
					</p>
				    <p>
				    	<span>Confirmar Senha* </span>
						<br/>
				    	<input class="contact" value="${usuario.confSenha}" type="password" name="confsenha" id="confsenha" placeholder="Repetir Senha" />
						<span style="color:rgb(10,122,146); font-size:12px;">Os campos 'Senha' e 'Confirmar Senha'
							devem ser iguais</span> 
				    </p>
					<br/>
					<p style="color:red; font-size:12px;">Os campos com * são obrigatórios, preencha-os com dados válidos</p> 
					
					<c:if test="${emailExistente == true}">
						<p style="color:red; font-size:12px;">Este Email já está cadastrado no AskTechForum.<br/>Tente outro ;)</p> 
					</c:if>

					<input class="submit" value="Cadastrar" type="submit" /> 
					<input class="submit" value="Voltar" type="button" onclick="voltar()" />
					<br />
					<br />
					<br />
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