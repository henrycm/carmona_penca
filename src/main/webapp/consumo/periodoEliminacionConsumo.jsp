<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/consumo/consumo.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div align="center">
		<div class="demo">
			<form name="liquidacion" method="post" action="ControlLiquidacion">
				<input name="accion" type="hidden"
					value="<c:out value="${accion}"/>"> <input
					name="distribuidor" type="hidden"
					value="<c:out value="${distribuidor}"/>"> <input
					name="periodo" type="hidden"> <input name="pedido"
					type="hidden"> <input name="distribuidor" type="hidden"
					value="<c:out value="${distribuidor}"/>"> <input
					name="totalPedido" type="hidden">

				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:consultarListaConsumosEliminar();">Consultar
						Consumos</a>
				</div>

				<table align="center" class="tbl-lista" width="80%">
					<tr>
						<th colspan="2">Eliminar Consumo periodo</th>
					</tr>
					<tr>
						<td>Periodo: <select name="mes">
						</select> <select name="ano">
						</select>
						</td>
					</tr>
				</table>
				<c:if test="${consumos!=null}">
					<table align="center" class="tbl-lista" width="90%" border="1">
						<tr>
							<th>Codigo</th>
							<th>Fecha</th>
							<th>Nombre</th>
							<th>Total Pedido</th>
							<th>Eliminar Pedido</th>
						</tr>
						<c:forEach var='consumo' items='${consumos}'>
							<tr>
								<td><c:out value='${consumo.codigoConsumo}' /></td>
								<td><fmt:formatDate type="date" value="${consumo.fecha}" /></td>
								<td><c:out value='${consumo.cedulaAfiliado}' />- <c:out
										value='${consumo.nombreAfiliado}' /></td>
								<td><c:out value='${consumo.totalPedido}' /></td>
								<td><A
									href="javascript:eliminarConsumo('<c:out value='${consumo.codigoConsumo}'/>')">Eliminar
										Consumo</A></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>