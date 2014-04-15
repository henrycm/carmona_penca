<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/liquidacion/liquidacion.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div align="center">
		<div class="demo">
			<c:if test="${liquidacionExitosa==null || liquidacionExitosa==''}">
				<form name="liquidacion" method="post" action="ControlLiquidacion">
					<input name="accion" type="hidden" value="M">
					<c:if test="${empty mensaje}">
						<div class="btn-group">
							<a class="btn btn-sm btn-default"
								href="javascript:enviarMasivo()">Generar Liquidacion</a>
						</div>
					</c:if>
					<table align="center" class="tbl-lista" width="80%">
						<tr>
							<th colspan="2">Liquidación Masiva.</th>
						</tr>
						<tr>
							<td>Periodo: <select name="mes">
							</select> <select name="ano">
							</select>
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='1'}">
				<table align="center" class="tabla" width="80%" border="1">
					<tr>
						<td width="10%">
							<h4 style="color: blue">La liquidacion fue Exitosa.</h4>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='2'}">
				<table align="center" class="tabla" width="80%" border="1">
					<tr>
						<td width="10%">La liquidacion ya habia sido realizada.</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${liquidacionExitosa!=null && liquidacionExitosa=='0'}">
				<table align="center" class="tabla" width="80%" border="1">
					<tr>
						<td width="10%">
							<h4 style="color: red">La liquidacion presento errores por
								favor comuniquese con el administrador.</h4>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${not empty mensaje}">
				<table align="center" class="tabla" width="80%" border="1">
					<tr>
						<td width="10%">
							<h4 style="color: red">
								<c:out value="${mensaje}"></c:out>
							</h4>
						</td>
					</tr>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>