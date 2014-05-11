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
<script src="../js/pedido/pedido.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div align="center">
		<div class="demo">
			<form name="liquidacion" method="post" action="ControlLiquidacion">
				<input name="accion" type="hidden"
					value="<c:out value="${accion}"/>"> <input name="periodo"
					type="hidden"> <input name="pedido" type="hidden">
				<input name="totalPedido" type="hidden">
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:consultarListaPedidosEliminar();">Consultar
						Pedidos</a>
				</div>
				<table align="center" class="tbl-lista" width="70%">
					<tr>
						<th>Eliminar pedidos periodo</th>
					</tr>
					<tr>
						<td>Periodo: <select name="mes">

						</select> <select name="ano">
						</select>
						</td>
					</tr>
				</table>
				<table align="center" class="tbl-lista" width="90%" border="1">
					<tr>
						<th colspan="4">Lista de Pedidos</th>
					</tr>
					<tr>
						<th>Codigo</th>
						<th>Fecha</th>
						<th>Total Pedido</th>
						<th>Eliminar Pedido</th>
					</tr>
					<c:if test="${pedidos!=null}">
						<c:forEach var='pedido' items='${pedidos}'>
							<tr>
								<td><c:out value='${pedido.codigoPedido}' /></td>
								<td><fmt:formatDate type="date" value="${pedido.fecha}" /></td>
								<td><c:out value='${pedido.totalPedido}' /></td>
								<td><a
									href="javascript:eliminarPedido('<c:out value='${pedido.codigoPedido}'/>','<c:out
											value='${pedido.totalPedido}' />');">Eliminar
										Pedido </a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>