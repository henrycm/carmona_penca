<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Documento sin t&iacute;tulo</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	<link rel="stylesheet" href="../css/multinivel.css">
		<link rel="stylesheet" href="../css/jquery/demos.css">
			<script src="../js/jquery/jquery-1.5.1.js"></script>
			<script src="../js/jquery/jquery.ui.core.js"></script>
			<script src="../js/jquery/jquery.ui.widget.js"></script>
			<script src="../js/jquery/jquery.effects.core.js"></script>
			<script src="../js/jquery/jquery.effects.drop.js"></script>
			<script src="../js/jquery/jquery.ui.mouse.js"></script>
			<script src="../js/jquery/jquery.ui.draggable.js"></script>
			<script src="../js/jquery/jquery.ui.droppable.js"></script>
			<script src="../js/jquery/jquery.ui.sortable.js"></script>
			<script src="../js/jquery/jquery.ui.accordion.js"></script>
			<script src="../js/jquery/jquery.ui.datepicker.js">
				
			</script>
			<script src="../js/generico.js"></script>
			<script src="../js/pedido/pedido.js"></script>
			<style>
h1 {
	padding: .1em;
	margin: 0;
	font-size: 18px;
}

#products {
	float: left;
	width: 100%;
	margin-right: 2em;
	border-color: #006600;
}

.Estilo12 {
	font-family: Verdana, Arial, sans-serif
}
</style>

			<script>
				$(function() {
					$("#products").accordion({
						autoHeight : false,
						navigation : true
					});
				});
			</script>
</head>
<body>
	<div align="right" id="opcion">
		<a href="javascript:enviarPedido();">Enviar Pedido</a> <a
			href="javascript:history.go(-1);">Cancelar Pedido</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<p>&nbsp;</p>
	<form action="../pedido/VistaPreliminarPedido" method="post">
		<input type="hidden" name="accion" value='<c:out value="${accion}"/>' />
		<table width="80%">
			<tr>
				<td>Fecha:</td>
				<td colspan="3"><c:out value="${fechaActual}"></c:out></td>
			</tr>
			<tr>
				<td width="27%">Nombre Empresario:</td>
				<td width="26%"><input name="nombre" type="text" size="30"
					value="<c:out value="${nombre}"/>" readonly="readonly" /></td>
				<td width="25%">No. Identificaci&oacute;n:</td>
				<td width="22%"><input name="cedula" type="text" size="15"
					value="<c:out value="${cedula}"/>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>Telefono:</td>
				<td><input name="telefono" type="text" size="15"
					value="<c:out value="${telefono}"/>" readonly="readonly" /></td>
				<td>Ciudad:</td>
				<td><input name="ciudad" type="text" size="20"
					value="<c:out value="${ciudad}"/>" readonly="readonly" /></td>
			</tr>
			<tr>
				<td style="color: blue">Saldo disponible:</td>
				<td><input id="saldo" name="saldo" type="text"
					value="${saldosDistribuidor.saldo}" readonly="readonly" /></td>
				<td style="color: red">Saldo Abonos:</td>
				<td><input id="saldoAbonos" name="saldoAbonos" type="text"
					value="${saldosDistribuidor.saldoAbonado}" readonly="readonly" /></td>
			</tr>
		</table>
		Linea de Alimentos
		<table width="80%">
			<tr class="tabla">
				<th width="5%">Cod. Prod</th>
				<th width="50%">Nombre Producto</th>
				<th width="5%">Cantidad</th>
				<th width="5%">Precio</th>
				<th width="15%">Valor Total Producto</th>
			</tr>
			<c:forEach var='pedido' items='${listaPedido}'>
				<tr>
					<td><input name="codigoProducto"
						value="<c:out value='${pedido.codigoProducto}'/>"
						readonly="readonly" size="10" /></td>
					<td><input name="nombreProducto"
						value="<c:out value='${pedido.nombreProducto}'/>"
						readonly="readonly" size="60" maxlength="50" /></td>
					<td><input name="cantidad"
						value="<c:out value='${pedido.cantidad}'/>" maxlength="3"
						readonly="readonly" size="10" /></td>
					<td><input name="valorUnitario"
						value="<c:out value='${pedido.valorUnitario}'/>"
						readonly="readonly" maxlength="5" size="10" /></td>
					<td><input name="totalProducto"
						value="<c:out value='${pedido.totalProducto}'/>"
						readonly="readonly" maxlength="5" /> <input
						name="valorUnitarioAfiliado" type="hidden"
						value="<c:out value='${pedido.valorUnitarioAfiliado}'/>" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">TRANSPORTE PEDIDO : <input
					name="transporte" value="<c:out value='${transporte}'/>"
					readonly="readonly" />&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">TOTAL PEDIDO SIN TRANSPORTE: <input
					name="totalPedido" value="<c:out value='${totalPedido}'/>"
					readonly="readonly" />&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">TOTAL SIN DESCUENTO(PRECIO
					AFILIADO) : <input name="totalPedidoAfiliado"
					value="<c:out value='${totalPedidoAfiliado}'/>" readonly="readonly" />&nbsp;&nbsp;&nbsp;
				</td>

			</tr>
			<tr>
				<td colspan="4" align="right">TOTAL PEDIDO : <input
					id="totPedido" name="totalPedidoTransporte"
					value="<c:out value='${totalPedidoConTransporte}'/>"
					readonly="readonly" />&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
