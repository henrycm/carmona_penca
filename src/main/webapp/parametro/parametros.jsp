<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Maestro Parametro</title>
<link rel="StyleSheet" type="text/css"
	href="../../bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="../../css/multinivel.css" />
<script src="../../js/generico.js"></script>
<script src="../../js/parametro/parametro.js"></script>
</head>
<body>
	<div align="center">
		<div class="demo">
			<form action="IndexFrontController" name="parametro" method="post">
				<input type="hidden" name="accion"
					value="<c:out value="${accion}"/>" />
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:actualizarParametro()">Actualizar</a>
				</div>
				<table align="center" width="50%" class="tbl-form" border="1">
					<tr>
						<th colspan="3">Administración de Parámetros</th>
					</tr>
					<tr>
						<td align="left">Paraetro(*):</td>
						<td align="left"><input name="nombre" type="text" size="60"
							value="" maxlength="50" readonly="readonly" /></td>
					</tr>
					<tr>
						<td align="left">Nombre(*):</td>
						<td align="left"><input name="descripcion" type="text"
							size="110" value="" maxlength="100" /></td>
					</tr>
					<tr>
						<td align="left">Valor(*):</td>
						<td align="left"><input name="valor" type="text" size="15"
							value="" /></td>
					</tr>
				</table>
				<c:if test="${not empty mensaje}">
					<table align="center" class="tabla" width="80%" border="1">
						<tr>
							<td width="10%">
								<h5 style="color: blue">
									<c:out value="${mensaje}" />
								</h5>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${not empty error}">
					<table align="center" class="tabla" width="80%" border="1">
						<tr>
							<td width="10%">
								<h5 style="color: blue">
									<c:out value="${mensaje}" />
								</h5>
							</td>
						</tr>
					</table>
				</c:if>
				<table align="center" class="tbl-lista" width="98%" border="1">
					<tr>
						<th colspan="15">Lista de Parámetros</th>
					</tr>
					<tr>
						<th>Parámetro</th>
						<th>Descripcion</th>
						<th>Valor</th>
						<th>Seleccionar</th>
					</tr>
					<c:if test="${listaParametros!=null}">
						<c:forEach var='parametro' items='${listaParametros}'>
							<tr>
								<td align="left"><c:out
										value='${parametro.nombreParametro}' /></td>
								<td align="left"><c:out value='${parametro.descripcion}' /></td>
								<td width="12%"><c:out value='${parametro.valor}' /></td>
								<td><a
									href="javascript:cargarEntidad('<c:out value='${parametro.nombreParametro}'/>','<c:out value='${parametro.descripcion}'/>','<c:out value='${parametro.valor}'/>')">Seleccionar</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
