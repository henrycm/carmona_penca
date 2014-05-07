<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" type="text/css" href="../css/multinivel.css" />
<title>Administración de usuarios</title>
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css"
	href="${ctx}/bootstrap/css/bootstrap.css"></link>
<script src="../js/generico.js"></script>
<script src="../js/usuario/usuario.js"></script>
</head>

<body>
	<div align="center">
		<div class="demo">
			<form id="form1" name="form1" method="post"
				action="UsuarioFrontController">
				<input name="accion" type="hidden" id="accion">

				<c:if test='${listaUsuarios!=null}'>
					<div class="btn-group">
						<!-- a class="btn btn-sm btn-default" href="javascript:enviarUsuaro('I')">Ingresar</a-->
						<a class="btn btn-sm btn-default"
							href="javascript:enviarUsuaro('A')">Actualizar</a> <a
							class="btn btn-sm btn-default"
							href="javascript:enviarUsuaro('B')">Borrar </a>
						<div class="separador"></div>
					</div>
					<table align="center" width="50%" class="tbl-form" border="1">
						<tr>
							<th colspan="15">Administración de Usuarios</th>
						</tr>
						<tr>
							<td align="left" width="17%">Login:</td>
							<td colspan="5"><input type="text" name="usuario"
								id="usuario" /></td>
						</tr>
						<tr>
							<td align="left">Clave:</td>
							<td colspan="2"><input type="text" class="inputtext"
								name="password" id="password" /></td>
						</tr>
						<tr>
							<td align="left">Estado:</td>
							<td align="left"><select name="activado" id="activado">
									<option value=''>Seleccione</option>
									<option value='1'>Activo</option>
									<option value='0'>Inactivo</option>
							</select></td>
						</tr>
						<tr>
							<td align="left">Tol:</td>
							<td align="left"><select name="rol" id="rol">
									<option value=''>--Seleccione--</option>
									<c:forEach items="${roles}" var="r">
										<option value='${r.groupId}'>${r.authority}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</c:if>
			</form>
			<form class="form-inline" action="UsuarioFrontController"
				method="POST">
				<input name="accion" type="hidden" value="C">
				<fieldset>
					<div class="form-group">
						<select name="nomFiltro" class="form-control input-sm">
							<option value="Nombre">Nombre</option>
							<option value="Apellido">Apellido</option>
							<option value="Cedula">Cedula</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" class="form-control input-sm" name="filtro"
							required="required" pattern=".{3,}" title="Minimo 3 caracteres"
							maxlength="50">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-sm" value="Check">Buscar</button>
					</div>
				</fieldset>

				<c:if test='${listaUsuarios!=null}'>
					<table align="center" class="tbl-lista" border="1">
						<tr>
							<th colspan="15">Lista de Usuarios</th>
						</tr>
						<tr>
							<th>Login</th>
							<th>Nombre Usuario</th>
							<th>Clave</th>
							<th>Estado</th>
							<th>Seleccionar</th>
						</tr>
						<c:forEach var='usuario' items='${listaUsuarios}'>
							<tr>
								<td><c:out value='${usuario.username}' /></td>
								<td><c:out value='${usuario.nombreUsuario}' /></td>
								<td><c:out value='${usuario.password}' /></td>
								<td><c:if test='${usuario.enabled==1}'>
										<c:out value='Activo' />
									</c:if> <c:if test='${usuario.enabled==0}'>
										<c:out value='Inactivo' />
									</c:if></td>
								<td><a
									href="javascript:cargarUsuario('${usuario.username}','${usuario.password}','${usuario.enabled}', '${usuario.enabled}');">Seleccionar</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>
