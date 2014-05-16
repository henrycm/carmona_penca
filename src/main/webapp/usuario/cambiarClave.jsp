<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Administración de usuarios</title>
<link rel="StyleSheet" type="text/css" href="../css/multinivel.css" />
<link rel="StyleSheet" type="text/css"
	href="bootstrap/css/bootstrap.css" />
<script language="javascript" src="js/generico.js"></script>
<script language="javascript" src="js/usuario/usuario.js"></script>
<link rel="stylesheet" href="css/jquery/demos.css" />
<link rel="stylesheet" href="css/multinivel.css" />
</head>
<body>
	<div class="demo" align="center">
		<form id="form1" name="form1" method="post" action="cambiarClave">
			<input name="accion" type="hidden" id="accion" /> <input
				name="usuario" id="usuario" type="hidden"
				value='<sec:authentication property="principal.username" />' />
			<div class="btn-group">
				<a class="btn btn-sm btn-default"
					href="javascript:cambiarClave('A');">Cambiar Clave</a>
			</div>
			<table align="center" class="tbl-lista" width="30%" border="1">
				<tr>
					<th colspan="15">Cambiar Clave</th>
				</tr>
				<tr>
					<td align="right">Login:</td>
					<td align="left"><input type="text" name="user" id="user"
						value='<sec:authentication property="principal.username" />'
						disabled="disabled" /></td>

				</tr>
				<tr>
					<td align="right">&nbsp;Clave Actual:</td>
					<td align="left"><input type="password" class="inputtext"
						name="password" id="password" /></td>
				</tr>
				<tr>
					<td align="right">&nbsp;Clave Nueva:</td>
					<td align="left"><input type="password" class="inputtext"
						name="passwordNuevo" id="passwordNuevo" /></td>
				</tr>
			</table>
		</form>
		<c:if test="${error!=''}">
			<table align="center" class="tabla" width="80%" border="1">
				<tr>
					<td width="10%">
						<h4 style="color: red">
							<c:out value="${error}" />
						</h4>
					</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${mensaje!=''}">
			<table align="center" class="tabla" width="80%" border="1">
				<tr>
					<td width="10%">
						<h4 style="color: blue">
							<c:out value="${mensaje}" />
						</h4>
					</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>
