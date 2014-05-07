<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/estilo.css"></link>
<title>Administración de Roles</title>
<script language="javascript" src="../js/generico.js"></script>
</head>

<body>
	<form id="form1" name="form1" method="post" action="RolFrontController">
		<input name="accion" type="hidden" id="accion" />

		<!-- Menú en la parte de arriba -->
		<table align="center">
			<tr>
				<td><a href="administracion/ColegioFrontController"> <img
						src="../images/Menu/MComisaria2.gif" border="0" width="62"
						height="60" alt="Administrar Colegios"
						onmouseover="this.src='../images/Menu/MComisaria.gif'"
						onmouseout="this.src='../images/Menu/MComisaria2.gif'" /></a></td>

				<td><a href="administracion/EpsFrontController"> <img
						src="../images/Menu/MSalud2.gif" border="0" width="62" height="60"
						alt="Administrar EPS"
						onmouseover="this.src='../images/Menu/MSalud.gif'"
						onmouseout="this.src='../images/Menu/MSalud2.gif'" /></a></td>

				<td><a href="aplicacion/Index"> <img
						src="../images/Menu/MEducacion2.gif" border="0" width="62"
						height="60" alt="Regresar al Menu"
						onmouseover="this.src='../images/Menu/MEducacion.gif'"
						onmouseout="this.src='../images/Menu/MEducacion2.gif'" /></a></td>

				<td><a href="../login.jsp"> <img
						src="../images/Menu/MSalida2.gif" border="0" width="62"
						height="60" alt="Salir del sistema"
						onmouseover="this.src='../images/Menu/MSalida.gif'"
						onmouseout="this.src='../images/Menu/MSalida2.gif'" /></a></td>



			</tr>
		</table>
		<br />
		<!-- Fin Menú en la parte de arriba -->

		<table align="center" class="table" width="81%" border="1">
			<tr>
				<td colspan="15" height="99" bgcolor="#42475D">Administración
					de Roles</td>
			</tr>
			<tr>
				<td class="encabezadoiz" width="17%">&nbsp;Código:</td>
				<td colspan="5"><input type="text" name="codigo" id="codigo" /></td>
			</tr>
			<tr>
				<td class="encabezadoiz">&nbsp;Descripci&oacute;n:</td>
				<td colspan="2"><input type="text" class="inputtext"
					name="descripcion" id="descripcion" /></td>
			</tr>


			<tr>
				<td align="center" colspan="2"><input name="guardar"
					type="button" value="Guardar" onclick="enviarRol('I')" /> <input
					name="actualizar" type="button" value="Actualizar"
					onclick="enviarRol('A')" /> <input name="eliminar" type="button"
					value="Borrar" onclick="enviarRol('B')" /> <input name="limpiar"
					type="button" value="Limpiar" onclick="limpiarRol()" /></td>

			</tr>
		</table>

		<c:if test="${listaRoles!=null}">
			<table align="center" class="table" width="81%" border="1">
				<tr>
					<td class="encabezadoiz">Codigo</td>
					<td class="encabezadoiz">Descripción</td>
				</tr>
				<c:forEach var='rol' items='${listaRoles}'>

					<tr>
						<td><c:out value='${rol.groupId}' /></td>
						<td><c:out value='${rol.authority}' /></td>

						<td><a
							href="javascript:cargarRol('<c:out value='${rol.groupId}'/>','<c:out value='${rol.authority}'/>')">Seleccionar</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form>
</body>
</html>