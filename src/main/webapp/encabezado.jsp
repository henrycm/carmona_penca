<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="div_encabezado">
	<sec:authorize access="isAuthenticated()">
		<div align="right">
			<a href="cambiarClave" target="mainFrame">Cambiar Clave</a>
		</div>
		<div>
			<strong>Usuario:</strong>
			<div>
				<strong style="color: blue"> <sec:authentication
						property="principal.username" /> <c:out value="${nombreUsuario}"></c:out>
				</strong>
			</div>
		</div>
		<br />
		<div align="right">
			<a href="<c:url value="j_spring_security_logout"/>" target="_parent">Salida
				Segura</a>
		</div>
	</sec:authorize>
</div>

