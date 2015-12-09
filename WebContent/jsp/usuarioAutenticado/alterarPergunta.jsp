<%@ include file="/jsp/cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formconsultaPerguntaPorTag" action="<%=getServletContext().getContextPath()%>/ServletCadastroPergunta" method="post">
	<input type="hidden" value="cadastrar" id="flag">
	<div id="site_content">
		<div class="content">
			<h1>Altere a sua pergunta</h1>
			<div class="content_item">
				<div style="height: 450px; width: 300px;" class="form_settings_cadastro">
					<p>
						<span>Pergunta* </span>
						<input class="contact"  value="${pergunta.titulo}" type="text" name="titulo" id="titulo" />
					</p>
					<p>
						<span>Descri��o* </span>
						<textarea class="contact"  name="descricao" rows="8" cols="20" >${pergunta.descricao}</textarea>
					</p>
					<p>
						<span>Assuntos relacionados*</span>
						<input class="contact" value="${pergunta.tag}" type="text" name="tag" id="tag" />
						<span style="color:rgb(10,122,146); font-size:12px;">Separe os assuntos por apenas um espa�o em branco</span> 
					</p>
					
					<c:if test="${empty erroCadastroPergunta}">
						<% session.setAttribute("erroCadastroPergunta", ""); %>
					</c:if>
					
					<input type="hidden" value="alterar" name="acao">
					
					<p style="font-size: 12px; color: red;" id= msg><%=session.getAttribute("erroCadastroPergunta")%></p>
					
					<p style="color:red; font-size:12px;">Os campos com * s�o obrigat�rios.</p> 
					<input class="submit" value="Cadastrar" type="submit" /> 
				     <% session.setAttribute("erroCadastroPergunta", ""); %>   
 				</div>	
			</div>
		</div>
	</div>
</form>
<jsp:include page="/jsp/rodape.jsp"></jsp:include>