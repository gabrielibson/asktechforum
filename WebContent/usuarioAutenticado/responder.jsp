<%@ include file="/cabecalho.jsp"%>

<br />
<br />
<br />

<form id="formconsultaPerguntaPorTag" action="<%=getServletContext().getContextPath()%>/ServletCadastroResposta" method="post">

	<div id="site_content">
		<div class="content">
			<h1>Responder</h1>
			<div class="content_item">
				<div style="height: 320px; width: 300px;" class="form_settings_cadastro">
					<p>
						<span>Descri��o </span>
						<textarea class="contact" name="descricao" rows="8" cols="20" ></textarea>
						
						<c:if test="${empty erroCadastroResposta}">
						<% session.setAttribute("erroCadastroResposta", ""); %>
						</c:if>
					</p>
					
					<p style="font-size: 12px; color: red;" id= msg><%=session.getAttribute("erroCadastroResposta")%></p>
					
					<input type="hidden" value="cadastrar" name="acao">
					
					<p style="color:red; font-size:12px;">O campo Descri��o � obrigat�rio.</p> 
					<input class="submit" value="Enviar" type="submit"  /> 
					<% session.setAttribute("erroCadastroResposta", ""); %>
				</div>	
			</div>
		</div>
	</div>
</form>
<jsp:include page="/rodape.jsp"></jsp:include>