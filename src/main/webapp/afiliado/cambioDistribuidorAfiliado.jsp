<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.css"></link>
</head>
<body>
	<div style="width: 40%; margin-left: 100px;">
		<form class="" action="AfiliadoFrontController" method="POST">
			<input name="accion" type="hidden" value="Q"> <input
				name="cedula" type="hidden" value="${afiliado.cedula}"> <input
				name="cedulaDist" type="hidden" value="${distribuidor.cedula}">
			<fieldset>
				<c:if test="${not empty mensaje}">
					<div class="form-group">
						<div class="alert alert-danger">
							<p>${mensaje}</p>
						</div>
					</div>
					<div class="separador"></div>
				</c:if>
				<div class="form-group">
					<label>Afiliado:</label> <input type="text"
						class="form-control input-sm" name="afiliado" readonly="readonly"
						value="${afiliado.nombre} ${afiliado.apellido}" />
				</div>
				<div class="form-group">
					<label>Distribuidor:</label> <input type="text"
						class="form-control input-sm" name="distribuidor"
						readonly="readonly" value="${distribuidor.nombre} ${distribuidor.apellido}">
				</div>
				<div class="form-group">
					<label>Nuevo distribuidor:</label> <select name="nuevoDistribuidor"
						class="form-control input-sm" required="required">
						<option value="">-- Seleccionar --</option>
						<c:forEach items="${distribuidores}" var="r">
							<option value='${r.cedula}'>${r.nombre} ${r.apellido}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-sm" value="Check">Guardar</button>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>