<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="div_encabezado">
	<sec:authorize access="isAuthenticated()">
		<div>
			<strong>Usuario:</strong>
			<sec:authentication property="principal.username" />
		</div>
		<div>
			<a href="<c:url value="j_spring_security_logout"/>" target="_parent">Salida
				Segura</a>
		</div>
	</sec:authorize>
</div>

