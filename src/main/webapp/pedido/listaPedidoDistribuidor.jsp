<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Lista de Pedidos Proveedor</title>
<link rel="stylesheet" href="../css/multinivel.css" />
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/generico.js"></script>
<script src="../js/pedido/pedido.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css" />
</head>
<body>
	<div class="demo" align="center">
		<form action="ListaPedidoController" name="pedido" method="post">
			<input type="hidden" name="accion" value="<c:out value="${accion}"/>" />
			<input type="hidden" name="codigoPedido" /> <input type="hidden"
				name="distribuidor" />
			<div align="center">

				<table align="center" class="tbl-lista" width="90%" border="1">
					<tr>
						<th colspan="6">Lista de Pedidos Distribuidor.</th>
					</tr>
					<tr>
						<th>Distribuidor</th>
						<th>Codigo</th>
						<th>Fecha</th>
						<th>Valor Pedido</th>
						<th>Transporte</th>
						<th>Eliminar</th>
					</tr>
					<c:if test="${listaPedido!=null}">
						<c:forEach var='pedido' items='${listaPedido}'>
							<tr>
								<td><c:out value='${pedido.distribuidor}' /></td>
								<td align="center"><c:out value='${pedido.codigoPedido}' /></td>
								<td align="center"><fmt:formatDate pattern='yyyy-MM-dd'
										value='${pedido.fecha}' /></td>
								<td align="center"><c:out value='${pedido.totalPedido}' /></td>
								<td align="center"><c:out value='${pedido.transporte}' /></td>
								<td align="center"><a
									href="javascript:actualizarTransporte('<c:out value='${pedido.codigoPedido}'/>','<c:out value='${pedido.transporte}'/>','<c:out value='${pedido.distribuidor}'/>')">Eliminar
										Transporte</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
