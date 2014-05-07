<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/multinivel.css" />
<title>Administración de usuarios</title>
<script language="javascript" src="js/generico.js"></script>
<script language="javascript" src="js/usuario/usuario.js"></script>
<link rel="stylesheet" href="css/jquery/demos.css" />
<link rel="stylesheet" href="css/multinivel.css" />
</head>
<body class="fondo">
	<c:if test="${mensaje!=''}">
		<div class="demo" align="center">
			<table class="tabla">
				<tr>
					<td><c:out value="${mensaje}"></c:out>. <a href="login.jsp">Regresar
							al sistema</a></td>
				</tr>
			</table>
		</div>
	</c:if>
	<c:if test="${error!=''}">
		<div class="demo" align="center">
			<table class="tabla">
				<tr>
					<td><c:out value="${error}"></c:out>. <a href="login.jsp">Regresar
							al sistema</a></td>
				</tr>
			</table>
		</div>
	</c:if>
	<c:if test="${mensaje==''}">
		<div class="demo">
			<form id="form1" name="form1" method="post" action="cambiarClave">
				<input name="accion" type="hidden" id="accion" />
				<div align="right" id="opcion">
					<a href="javascript:cambiarClave('A');">CambiarClave</a> <a
						href="login.jsp">Regresar al sistema</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<table align="center" class="tabla" width="81%" border="1">
					<tr>
						<td colspan="15">Cambiar Clave</td>
					</tr>
					<tr>
						<td class="encabezadoiz" width="17%">&nbsp;Login:</td>
						<td colspan="5"><input type="text" name="usuario"
							id="usuario" /></td>
					</tr>
					<tr>
						<td class="encabezadoiz">&nbsp;Clave Actual:</td>
						<td colspan="2"><input type="text" class="inputtext"
							name="password" id="password" /></td>
					</tr>
					<tr>
						<td class="encabezadoiz">&nbsp;Clave Nueva:</td>
						<td colspan="2"><input type="text" class="inputtext"
							name="passwordNuevo" id="passwordNuevo" /></td>
					</tr>
				</table>
			</form>
		</div>
	</c:if>
</body>
</html>
