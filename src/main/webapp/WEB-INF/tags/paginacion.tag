<%@tag description="Paginacion generica" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@attribute name="pagina" type="co.com.multinivel.util.Pagina" required="true" %>
<%@attribute name="etiqueta"%>
<%@attribute name="url" required="true" %>

<c:if test="${pagina.totalPages > 1}">
	<c:choose>
		<c:when test="${fn:indexOf(url, '?') gt 0 }">
			<c:set var="url_" value="${url}"/>
		</c:when>
		<c:otherwise>
			<c:set var="url_" value="${url}?1"/>
		</c:otherwise>
	</c:choose>		
		<ul class="pagination">	
			<c:if test="${not empty etiqueta}">
				<li><span>${etiqueta}</span></li>
			</c:if>
			<c:choose>
				<c:when test="${pagina.number == 0}">
					<li class="disabled"><span>&laquo;</span></li>
				</c:when>
				<c:otherwise>
					<li><a href="${url_}&numPagina=0">&laquo;</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="1" end="${pagina.totalPages}">
				<c:choose>
					<c:when test="${pagina.number == i -1}">
						<li class="active"><span><c:out value="${i}" /></span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${url_}&numPagina=${i-1}">${i}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pagina.number == pagina.totalPages -1}">
					<li class="disabled"><span>&raquo;</span></li>
				</c:when>
				<c:otherwise>
					<li><a
						href="${url_}&numPagina=${pagina.totalPages-1}">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>		
	
</c:if>
