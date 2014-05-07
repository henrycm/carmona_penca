<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/estilo.css"></link>
<title>Administración de Roles Por Usuario</title>
<script language="javascript" src="../js/generico.js"></script>
</head>
<body>
	<form id="form1" name="form1" method="post">
		<input name="accion" type="hidden" id="accion" />
		<!-- Menú en la parte de arriba -->
		<table align="center">
			<tr>
				<td><a href="../index.jsp"> <img
						src="../images/Menu/MHome2.gif" border="0" width="62" height="60"
						alt="Regresar al Menu"
						onmouseover="this.src='../images/Menu/MHome.gif'"
						onmouseout="this.src='../images/Menu/MHome2.gif'" /></a></td>
				<td><a href="../administracion/IndexUsuario"> <img
						src="../images/Menu/MUsuario2.gif" border="0" width="62"
						height="60" alt="Administrar EPS"
						onmouseover="this.src='../images/Menu/MUsuario.gif'"
						onmouseout="this.src='../images/Menu/MUsuario2.gif'" /></a></td>
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
					de Roles por Usuario</td>
			</tr>
			<tr>
				<td class="encabezadoiz" width="17%">&nbsp;Usuario:</td>
				<td colspan="5"><select name="usuario">
						<option value="">seleccione Usuario</option>
						<c:forEach items="${listaUsuarios}" var="user">
							<option value="${user.username}">${user.username}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="encabezadoiz" width="17%">&nbsp;Rol:</td>
				<td colspan="5"><select name="rol">
						<option value="">seleccione Rol</option>
						<c:forEach items="${listaRoles}" var="rol">
							<option value="${rol.groupId}">${rol.authority}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="center" colspan="6"><input name="guardar"
					type="button" value="Consultar Roles Usuario"
					onclick="enviarRolPorUsuario('C')" /> <input name="guardar"
					type="button" value="Asignar Rol a Usuario"
					onclick="enviarRolPorUsuario('I')" /> <input name="eliminar"
					type="button" value="Borrar Rol a Usuario"
					onclick="enviarRolPorUsuario('B')" /></td>
			</tr>
		</table>
		<c:if test="${listaRolesPorUsuario!=null}">
			<table align="center" class="table" width="81%" border="1">
				<tr>
					<td class="encabezadoiz">Usuario:</td>
					<td>${usuario.username}</td>
				</tr>
			</table>
			<table align="center" class="table" width="81%" border="1">
				<tr>
					<td class="encabezadoiz">Roles Asociados al Usuario</td>
				</tr>
			</table>
			<table align="center" class="table" width="81%" border="1">
				<tr>
					<td class="encabezadoiz">Codigo</td>
					<td class="encabezadoiz">Descripción</td>
				</tr>
				<c:forEach var='rol' items='${listaRolesPorUsuario}'>
					<tr>
						<td><c:out value='${rol.groupId}' /></td>
						<td><c:out value='${rol.authority}' /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form>
</body>
</html>