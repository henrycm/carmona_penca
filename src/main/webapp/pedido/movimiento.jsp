<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos COntables</title>
<script src="${ctx}/js/jquery/jquery-1.5.1.js"></script>
<link rel="StyleSheet" href="${ctx}/bootstrap/css/bootstrap.css"></link>
<link rel="stylesheet" href="${ctx}/css/multinivel.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#distribuidor").change(function() {
			$(this).closest("form").attr("action", "inicio");
			$(this).closest("form").submit();
		});
	});
</script>
</head>
<body>
	<div align="center">
		<div align="center" class="titulo">Ingreso de Abonos Distribuidor</div>
		<form:form class="form-inline" action="guardar" method="POST"
			commandName="movimiento">
			<input name="accion" type="hidden" value="M">
			<fieldset>
				<div class="form-group">
					<c:if test="${not empty listaDistribuidores}">
						<form:select path="distribuidor" class="form-control input-sm"
							required="required">
							<option value="">--Seleccionar--</option>
							<c:forEach items="${listaDistribuidores}" var="d">
								<c:if test="${movimiento.distribuidor eq d.cedula}">
									<option value="${d.cedula}" selected="selected">${d.nombre}
										${d.apellido} -- ${d.cedula}</option>
								</c:if>
								<c:if test="${movimiento.distribuidor ne d.cedula}">
									<option value="${d.cedula}">${d.nombre}${d.apellido}
										-- ${d.cedula}</option>
								</c:if>
							</c:forEach>
						</form:select>
					</c:if>
				</div>
				<div class="form-group">
					<form:input class="form-control input-sm" path="saldoAbonado"
						required="required" pattern="\d*" title="Solo numeros"
						maxlength="8" />
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-sm" value="Check">Guardar
						Abono</button>
				</div>
			</fieldset>
		</form:form>

		<c:if test="${not empty movimientos}">
			<div style="width: 60%">
				<table class="table table-bordered table-condensed">
					<thead>
						<tr class="enc-tabla">
							<th>Fecha</th>
							<th>Valor Abono</th>
							<th>Usuario</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="h" items="${movimientos}">
							<tr>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${h.fecha}" /></td>
								<td>${h.saldoAbonado}</td>
								<td>${h.usuario}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>