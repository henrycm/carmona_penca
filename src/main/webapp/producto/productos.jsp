<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Maestro Producto</title>
	<link rel="stylesheet" href="../../css/multinivel.css">
	<script src="../../js/generico.js"></script>
	<script src="../../js/producto/producto.js"></script>
    <link rel="stylesheet" href="../../css/jquery/demos.css">

</head>

<body class="fondo" onload="seleccionarListaDesplegable('<c:out value="${producto.tipo}"/>',document.producto.tipo);">
<div class="demo">
<form action="ProductoFrontController" name="producto" method="post">
<input type="hidden" name="accion" value="<c:out value="${accion}"/>" />
<input type="hidden" name="actualizar" />
<div align="center"><BR>
<BR>
<table width="35%" align="right">
	<tr>
		<td height="34">&nbsp;</td>
		<td width="75%" align="right"><a
			href="javascript:ingresarProducto()">Ingresar </a>&nbsp;&nbsp;<a
			href="javascript:actualizarProducto()">Actualizar</a>&nbsp;&nbsp;<a
			href="javascript:eliminarProducto()">Eliminar </a>&nbsp;&nbsp;<a
			href="javascript:consultarProducto()"> Consultar </a></td>
	</tr>
</table>

Administración de Productos
<table width="90%" class="tabla">

	<tr>
		<td width="15%" align="left">Codigo(*):</td>
		<td width="85%" align="left"><input name="codigo" type="text"
			size="15" value="<c:out value="${producto.codigo}"/>" maxlength="3" /></td>
	</tr>
	<tr>
		<td width="15%" align="left">Nombre(*):</td>
		<td width="85%" align="left"><input name="nombre" type="text"
			size="30" value="<c:out value="${producto.nombreProducto}"/>"
			maxlength="50" /></td>

	</tr>

	<tr>
		<td align="left">Precio Afiliado(*):</td>
		<td align="left"><input name="precioAfiliado" type="text"
			size="15" value="<c:out value="${producto.precioAfiliado}"/>"
			maxlength="5" /></td>
	</tr>

	<tr>
		<td align="left">Tipo</td>
		<td align="left"><select name="tipo">
			<option value=''>Seleccione el tipo de producto</option>
			<c:forEach var='tipoProducto' items='${listaTipoProducto}'>
				<option value="<c:out value='${tipoProducto.codigo}'/>"><c:out
					value='${tipoProducto.tipoProducto}' /></option>
			</c:forEach>
		</select> </select></td>
	</tr>
</table>
<br />
<br />


<div class="tituloLista">LISTA DE PRODUCTOS</div>
<c:if test="${listaProductos!=null}">
	<table align="center" class="tabla" width="90%" border="1">
		<tr>
			<td>Codigo</td>
			<td>Nombre</td>
			<td>Tipo</td>
			<td>Precio Distribuidor</td>
			<td>Precio Afiliado</td>

			<td>Seleccionar</td>
		</tr>
		<c:forEach var='producto' items='${listaProductos}'>

			<tr>
				<td><c:out value='${producto.codigo}' /></td>
				<td><c:out value='${producto.nombreProducto}' /></td>
				<td><c:out value='${producto.tipo}' /></td>
				<td><c:out value='${producto.precioAfiliado}' /></td>
				<td><c:out value='${producto.precioDistribuidor}' /></td>
				<td><a
					href="javascript:cargarEntidad('<c:out value='${producto.codigo}'/>','<c:out value='${producto.nombreProducto}'/>','<c:out value='${producto.codigoTipo}'/>','<c:out value='${producto.precioAfiliado}'/>')">Seleccionar</a>
				</td>

			</tr>
		</c:forEach>

	</table>

</c:if>
</form>
</div>
</body>
</html>
