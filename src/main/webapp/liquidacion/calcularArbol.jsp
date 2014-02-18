<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">

<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body class="fondo">
	<c:if test="${not empty mensaje}">
		<table align="center" class="tabla" width="90%" border="1">
			<tr>
				<td width="10%"><c:out value="${mensaje}" /></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${empty mensaje}">
		<table align="center" class="tabla" width="90%" border="1">
			<tr>
				<td width="10%">Este proceso puede durar alrededor de una hora!
				<br>
				Fecha ultimo calculo: 
				<c:if test="${not empty fecha}">
					<c:out value="${fecha}" />
				</c:if>
				<c:if test="${empty fecha}">
					En ejecución!
				</c:if>
				</td>
			</tr>
			<tr>
				<td width="10%">
					<form method="post" action="ControlLiquidacion">					
						<input type="hidden" name="accion" value="C"> <input
							type="submit" value="Calcular" ${not empty fecha ? "" : "disabled"}>
					</form>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>