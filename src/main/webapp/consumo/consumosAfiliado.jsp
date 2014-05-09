<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				$(document).ready(function() {
					$("[data-disp]").each(function() {
						if ($(this).attr("data-disp") == "0") {
							$(this).attr("disabled", "disabled");
						} else {
							$(this).change(function() {
								if($(this).val() > $(this).attr("data-disp"))
								{
									alert("Solo se puede pedir la disponibilidad del Distribuidor!");
									$(this).val("");
								}
							});
						}
					});

				});
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
		<a href="javascript:vistaPreliminarPedido();">Vista Preliminar del
			consumo</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	<p>&nbsp;</p>
	<form action="../consumo/VistaPreliminarConsumo" method="post">
		<input name="accion" type="hidden" size="20"
			value="<c:out value="${accion}"/>" /> <input name="distribuidor"
			type="hidden" value="<c:out value="${distribuidor}"/>">

			<table width="53%">

				<tr>
					<td width="27%">Nombre Empresario:</td>
					<td width="26%"><input name="nombre" type="text" size="30"
						value="<c:out value="${afiliado.nombre}"/>" /></td>
					<td width="25%">No. Identificaci&oacute;n:</td>
					<td width="22%"><input name="documento" type="text" size="15"
						value="<c:out value="${afiliado.cedula}"/>" /></td>
				</tr>
				<tr>
					<td>Telefono:</td>
					<td><input name="telefono" type="text" size="15"
						value="<c:out value="${afiliado.telefono}"/>" /></td>
					<td>Ciudad:</td>
					<td><input name="ciudad" type="text" size="20"
						value="<c:out value="${afiliado.ciudad}"/>" /></td>
				</tr>
				<tr>
					<td>Consumo periodo:</td>
					<td><input name="saldo" type="text" value="${saldoAfiliado}"
						readonly="readonly" /></td>
					<td><input name="fechaConsumo" type="hidden" size="20"
						value="<c:out value="${fechaConsumo}"/>" /></td>
				</tr>
			</table> Linea de Alimentos
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>

					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaAlimentos}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>

						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
				</c:forEach>

				</tr>
			</table> L&iacute;nea para la Piel
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaPiel}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>
						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Linea Capilar
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaCapilar}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>

						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Linea de Aseo Personal

			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaAseoPersonal}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>
						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Linea Fisioterapia y Estética
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaFisioterapia}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>

						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Linea Aseo del Hogar
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaAseoHogar}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>
						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Linea Veterinaria
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaVeterinaria}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>

						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table> Extractos
			<table width="80%">
				<tr class="tabla">
					<th width="10%">Codigo Producto</th>
					<th width="60%">Nombre Producto</th>
					<th width="10%">Precio</th>
					<th width="10%">Disp. distribuidor</th>
					<th width="10%">Cantidad</th>
					<th width="10%">Seleccionar</th>

				</tr>
				<c:forEach var='producto' items='${listaExtractos}'>
					<tr>
						<td><c:out value='${producto.codigo}' /></td>
						<td><c:out value='${producto.nombreProducto}' /></td>
						<td><c:out value='${producto.precioAfiliado}' /></td>
						<td>${producto.disponibilidadDist}</td>
						<td><input maxlength="3" size="3"
							data-disp="${producto.disponibilidadDist}"
							name="cantidad_<c:out value='${producto.codigo}'/>"
							onclick="javascript:chequearProducto(this)" /></td>
						<td><input type="checkbox"
							value="<c:out value='${producto.codigo}'/>" name="producto" /> <input
							type="hidden"
							value="<c:out value='${producto.precioAfiliado}'/>_<c:out value='${producto.nombreProducto}'/>"
							name="datos_<c:out value='${producto.codigo}'/>" /></td>
					</tr>
				</c:forEach>

			</table>
	</form>


</body>
</html>
