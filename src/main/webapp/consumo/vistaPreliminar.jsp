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
			<script src="../js/consumo/consumo.js"></script>


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
		<c:if test="${mensajeSaldoAbonadoDistribuidor!='true'}">
			<c:if test="${mensajeSaldoDistribuidor!='true'}">
				<a href="javascript:enviarPedido();">Enviar Consumo</a>
			</c:if>
		</c:if>
		<a href="javascript:history.go(-1);">Cancelar Consumo</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<p align="center">
		<strong> REALIZAR CONSUMO </strong>
	</p>
	<form action="../consumo/VistaPreliminarConsumo" method="post">
		<input type="hidden" name="accion" value="<c:out value="${accion}"/>" />
		<input name="distribuidor" type="hidden"
			value="<c:out value="${distribuidor}"/>" /> <input
			name="fechaConsumo" type="hidden"
			value="<c:out value="${fechaActual}"/>" />
		<table width="53%">
			<tr>
				<td>Periodo de Consumo:</td>
				<td><c:out value="${fechaActual}" /></td>
				<td colspan="2">Consumo total del Afiliado en el periodo:</td>
				<td><input name="saldo" type="text" value="${saldoAfiliado}"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td width="27%">Nombre Empresario:</td>
				<td width="26%"><input name="nombre" type="text" size="30"
					value="<c:out value="${nombre}"/>" readonly="readonly"/></td>
				<td width="25%">No. Identificaci&oacute;n:</td>
				<td width="22%"><input name="cedula" type="text" size="15"
					value="<c:out value="${cedula}"/>" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Telefono:</td>
				<td><input name="telefono" type="text" size="15"
					value="<c:out value="${telefono}"/>" readonly="readonly"/></td>
				<td>Ciudad:</td>
				<td><input name="ciudad" type="text" size="20"
					value="<c:out value="${ciudad}"/>" readonly="readonly"/></td>
			</tr>
			<tr>
				<td style="color: blue">Saldo disponible:</td>
				<td><input name="saldo" type="text"
					value="${saldosDistribuidor.saldo}" readonly="readonly" /></td>
				<td style="color: red">Saldo Abonos:</td>
				<td><input name="saldo" type="text"
					value="${saldosDistribuidor.saldoAbonado}" readonly="readonly" /></td>
			</tr>
		</table>
		Linea de Productos
		<table width="90%">
			<tr class="tabla">
				<th width="8%">Codigo Producto</th>
				<th width="48%">Nombre Producto</th>
				<th width="8%">Cantidad</th>
				<th width="8%">Precio</th>
				<th width="8%">Valor Total Producto</th>
			</tr>
			<c:forEach var='pedido' items='${listaConsumo}'>
				<tr>
					<td><input name="codigoProducto"
						value="<c:out value='${pedido.codigoProducto}'/>"
						readonly="readonly" /></td>
					<td><input name="nombreProducto"
						value="<c:out value='${pedido.nombreProducto}'/>"
						readonly="readonly" size="50" maxlength="50" /></td>
					<td><input name="cantidad"
						value="<c:out value='${pedido.cantidad}'/>" maxlength="3"
						readonly="readonly" /></td>
					<td><input name="valorUnitario"
						value="<c:out value='${pedido.valorUnitario}'/>"
						readonly="readonly" maxlength="5" /></td>
					<td><input name="totalProducto"
						value="<c:out value='${pedido.totalProducto}'/>"
						readonly="readonly" maxlength="5" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">TOTAL CONSUMO PERIODO : <input
					name="totalConsumo" value="<c:out value='${saldoAfiliado}'/>"
					readonly="readonly" />&nbsp;&nbsp;&nbsp; TOTAL PEDIDO : <input
					name="totalPedido" value="<c:out value='${totalConsumo}'/>"
					readonly="readonly" />&nbsp;&nbsp;&nbsp; <c:if
						test="${mensajeSaldoDistribuidor!='true'}">
		SALDO RESTANTE DEL DISTRIBUIDOR :
		<fmt:formatNumber currencySymbol="$" type="currency"
							value="${saldoDistribuidor}" />
					</c:if> <c:if test="${mensajeSaldoDistribuidor=='true'}">
						<br />
						<span style="color: red">El Saldo del Distribuidor es menor
							que el valor del consumo. </span>
					</c:if> <c:if test="${mensajeSaldoAbonadoDistribuidor=='true'}">
						<br />
						<span style="color: red">El Saldo de los Abonos del
							Distribuidor es menor que el valor del consumo. </span>
					</c:if>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
