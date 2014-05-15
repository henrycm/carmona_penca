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
<link rel="stylesheet" href="${ctx}/css/jquery/jquery.ui.all.css">
<link rel="StyleSheet" href="${ctx}/bootstrap/css/bootstrap.css"></link>
<link rel="stylesheet" href="${ctx}/css/multinivel.css">
<link rel="stylesheet" href="${ctx}/css/jquery/demos.css">
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
	<div align="center" class="demo">
		<div align="center" class="titulo">Consulta de Inventario
			Distribuidor Producto.</div>
		<form:form class="form-inline" action="guardar" method="POST"
			commandName="inventario">
			<input name="accion" type="hidden" value="M">
			<fieldset>
				<div class="form-group">
					<label>Distribuidor</label>
				</div>
				<div class="form-group">
					<c:if test="${not empty listaDistribuidores}">
						<form:select path="distribuidor" class="form-control input-sm"
							required="required">
							<option value="">--Seleccionar--</option>
							<c:forEach items="${listaDistribuidores}" var="d">
								<c:if test="${inventario.distribuidor eq d.cedula}">
									<option value="${d.cedula}" selected="selected">${d.nombre}
										${d.apellido} -- ${d.cedula}</option>
								</c:if>
								<c:if test="${inventario.distribuidor ne d.cedula}">
									<option value="${d.cedula}">${d.nombre}${d.apellido}
										-- ${d.cedula}</option>
								</c:if>
							</c:forEach>
						</form:select>
					</c:if>
				</div>
			</fieldset>
		</form:form>
		<div align="center">
			<table class="tbl-lista" border="1">
				<tr>
					<th>Codigo</th>
					<th>Producto</th>
					<th>Cantidad</th>
					<th>Val.Afiliado</th>
					<th>Tot.Afiliado</th>
					<th>Val.Distribuidor</th>
					<th>Tot.Distribuidor</th>
				</tr>
				<c:if test="${not empty inventarios}">
					<c:forEach var="i" items="${inventarios}">
						<tr>
							<td align="center">${i.producto.codigo}</td>
							<td>${i.producto.nombreProducto}</td>
							<td align="center">${i.producto.cantidad}</td>
							<td align="center">${i.producto.precioAfiliado}</td>
							<td align="center">${i.valorTotalAfiliado}</td>
							<td align="center">${i.producto.precioDistribuidor}</td>
							<td align="center">${i.valorTotalDistribuidor}</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
</body>
</html>