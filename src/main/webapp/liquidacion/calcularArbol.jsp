<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<link rel="StyleSheet" type="text/css" href="../bootstrap/css/bootstrap.css" />
<script src="../js/generico.js"></script>
<script src="../js/liquidacion.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body>
	<div align="center">
		<div class="demo">
			<c:if test="${not empty mensaje}">
				<table align="center" class="tbl-lista" width="90%" border="1">
					<tr>
						<td width="10%"><c:out value="${mensaje}" /></td>
					</tr>
				</table>
			</c:if>
			<c:if test="${empty mensaje}">
				<form name="calcular" method="post" action="ControlLiquidacion">
					<input type="hidden" name="accion" value="C"> <input
						type="submit" value="Calcular"
						${not empty fecha ? "" : "disabled"}>
					<div class="btn-group">
						<a class="btn btn-sm btn-default"
							href="javascript:calcularArbol()">Calcular</a>
					</div>
					<input name="accion" type="hidden" value="P"> <input
						name="periodo" type="hidden">

					<table align="center" class="tbl-lista" width="70%">
						<tr>
							<th colspan="2	">Calcular Arbol Multi-Aloe</th>
						</tr>
						<tr>
							<td>
								<p>
									Este proceso puede durar alrededor de una hora! <br /> Fecha
									ultimo calculo:
									<c:if test="${not empty fecha}">
										<c:out value="${fecha}" />
									</c:if>
									<c:if test="${empty fecha}">
					En ejecución!
				</c:if>
								</p>
							</td>
						</tr>
					</table>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>