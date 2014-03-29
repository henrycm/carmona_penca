<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Maestro Producto</title>
<link rel="StyleSheet" type="text/css"
	href="../../bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="../../css/multinivel.css" />
<script src="../../js/generico.js"></script>
<script src="../../js/producto/producto.js"></script>

</head>

<body class="fondo"
	onload="seleccionarListaDesplegable('<c:out value="${producto.tipo}"/>',document.producto.tipo);">
	<div class="demo">
		<form action="ProductoFrontController" name="producto" method="post">
			<input type="hidden" name="accion" value="<c:out value="${accion}"/>" />
			<input type="hidden" name="actualizar" />
			<div align="center">
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:ingresarProducto()">Ingresar </a> <a
						class="btn btn-sm btn-default"
						href="javascript:actualizarProducto()">Actualizar</a> <a
						class="btn btn-sm btn-default"
						href="javascript:eliminarProducto()">Eliminar </a> <a
						class="btn btn-sm btn-default"
						href="javascript:consultarProducto()"> Consultar </a>
				</div>
				<div class="titulo">Administración de Productos</div>
				<table width="90%" class="tbl-form">
					<tr>
						<td width="15%" align="left">Codigo(*):</td>
						<td width="85%" align="left"><input name="codigo" type="text"
							size="15" value="<c:out value="${producto.codigo}"/>"
							maxlength="3" /></td>
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
						</select></td>
					</tr>
				</table>
				<br /> <br />
				<div class="titulo">LISTA DE PRODUCTOS</div>
				<c:if test="${listaProductos!=null}">
					<table align="center" class="tbl-lista" width="98%" border="1">
						<tr>
							<th>Codigo</th>
							<th>Nombre</th>
							<th>Tipo</th>
							<th>Precio Distribuidor</th>
							<th>Precio Afiliado</th>

							<th>Seleccionar</th>
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
			</div>
		</form>
	</div>
</body>
</html>
