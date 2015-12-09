<%@ include file="cabecalho.jsp"%>

<%
String tag = request.getParameter("tag");
%>

<%
if((Boolean)session.getAttribute("stop")) {
%>
	<body onload="carregarTag()" >    	
		<form id="formTagAll" action="ServletConsultarPerguntaPorTag" method="post">
			<input type="hidden" name="tag" id="tag" value="all" checked="checked" />
		</form>
	</body>
<%
}
int i = 1;
%>

<form id="formConsultarPerguntaPorTodasTags" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		<c:if test="${not empty perguntasTags}">
			<c:forEach items="${perguntasTags}" var="pergunta">
				<c:if test="${not empty pergunta.titulo}" >
					<div class="content">
						<h1>
						<form id="formConsultarRespostaPergunta<%=i%>"></form>
						<%i++; %>
						<form id="formConsultarRespostaPergunta<%=i%>" action="ServletConsultarRespostaPergunta" method = "post">
							<input type="hidden" value="${pergunta.idPergunta}" name="idPergunta" id="idPergunta" checked="checked" />
							<input type="hidden" value="${pergunta.autor}" name="autor" id="autor" checked="checked" />
							<input type="hidden" value="${pergunta.descricao}" name="descricao" id="descricao" checked="checked" />
							<input type="hidden" value="${pergunta.titulo}" name="titulo" id="titulo" checked="checked" />
							<a id="formConsultarRespostaPergunta<%=i%>" onclick="consultarRespostaPergunta(id)" >
								${pergunta.titulo}
							</a>
						</form>
		
						</h1>
						<div class="content_item">
							<div style="width: 900px;" class="form_settings_cadastro">
															
								<p><c:out value="${pergunta.descricao}"></c:out></p>
							 	<c:forEach var="tag" items="${pergunta.listTags}">
							    	<a class="tags-list" href="ServletConsultarPerguntaPorTag?tag=${tag}">${tag}</a>&nbsp;
							    </c:forEach>
							    <br>
							    <br>
								<output style="font-size: 11px; position: right;">Por: <c:out value="${pergunta.autor}"></c:out></output>
								<p><output style="font-size: 9px; position: right;">Em: <c:out value="${pergunta.data}"></c:out> às <c:out value="${pergunta.hora}"></c:out></output><p>
								Respostas:<c:out value=" ${pergunta.qtdResposta}"></c:out>
								
								<input type="hidden" value="${pergunta.idPergunta}" name="idPergunta" id="idPergunta" checked="checked" />
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
	</div>
</form>

<script>
	function carregarTag() {
		var formulario;
		formulario = document.getElementById("formTagAll");
		formulario.submit();
	}
</script>

<script>
	function consultarRespostaPergunta(id) {
		var formulario;
		formulario = document.getElementById(id);
		formulario.submit();
	}
</script>

<jsp:include page="rodape.jsp"></jsp:include>