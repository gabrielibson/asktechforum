<%@ include file="cabecalho.jsp"%>

<%
	String tag = request.getParameter("tag");
    
	int i = 1;
%>
<form id="formConsultarPerguntaPorTag" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		&nbsp;&nbsp;<h2><%=tag.toUpperCase()%></h2>
		
		<c:forEach items="${perguntasTags}" var="pergunta">
			<div class="content">
				<h1>
					<form id="formConsultarRespostaTag<%=i%>"></form>
					<%i++; %>
					<form id="formConsultarRespostaTag<%=i%>" action="ServletConsultarRespostaPergunta" method = "post">
					<input type="hidden" value="${pergunta.idPergunta}" name="idPergunta" id="idPergunta" checked="checked" >
					<input type="hidden" value="${pergunta.autor}" name="autor" id="autor" checked="checked" >
					<input type="hidden" value="${pergunta.descricao}" name="descricao" id="descricao" checked="checked">
					<input type="hidden" value="${pergunta.titulo}" name="titulo" id="titulo" checked="checked">
					<a id="formConsultarRespostaTag<%=i%>" onclick="consultarRespostaTag(id)" >
						${pergunta.titulo}
					</a>
				</form>
				
				</h1>
				<div class="content_item">
					<div style="width: 900px;" class="form_settings_cadastro">
										
						<p><c:out value="${pergunta.descricao}"></c:out></p>
						<output style="font-size: 11px; position: right;">Por: <c:out value="${pergunta.autor}"></c:out></output>
						<p><output style="font-size: 9px; position: right;">Em: <c:out value="${pergunta.data}"></c:out> às <c:out value="${pergunta.hora}"></c:out></output><p>
						Respostas:<c:out value=" ${pergunta.qtdResposta}"></c:out>
					
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</form>

<script>
	function consultarRespostaTag(id) {
		var formulario;
		formulario = document.getElementById(id);
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>