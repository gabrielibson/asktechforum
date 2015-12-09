<%@page import="java.util.ArrayList"%>
<%@ include file="cabecalho.jsp"%>

<form id="formConsultarPerguntaPorTag" action="ServletConsultarPerguntaPorTag" method="post">
	<div id="site_content">
		
			<div class="content">	
        		<h1>Tags do Ask TechForum</h1>			
				<div class="content_item">
					<%
							ArrayList<String> tags = (ArrayList<String>) request.getAttribute("tags"); 
							for(String nomeTag : tags){
					%>
						<p class="tags-list">
								<a href="<%=getServletContext().getContextPath()%>/ServletConsultarPerguntaPorTag?tag=<%=nomeTag %>"><%=nomeTag %></a> 
						</p>
						<br>
						<br>
					<% } %>
									
				</div>
			</div>
		
	</div>
</form>

<jsp:include page="rodape.jsp"></jsp:include>

