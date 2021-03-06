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
<script src="../js/generico.js"></script>
<script src="../js/arbol/arbol.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body>
	<div align="center">
		<div class="demo">
			<c:if test="${not empty mensaje}">
				<table align="center" class="tbl-lista" width="90%" border="1">
					<tr>
						<td>
							<h5 style="color: blue">
								<c:out value="${mensaje}" />
							</h5>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${empty mensaje}">
				<form name="calcular" method="post" action="ControlLiquidacion">
					<input type="hidden" name="accion" value="C">
					<c:if test="${fecha!='1900-01-01'}">
						<div class="btn-group">
							<a class="btn btn-sm btn-default"
								href="javascript:calcularArbol()">Calcular</a>
						</div>
					</c:if>
					<input name="accion" type="hidden" value="P"> <input
						name="periodo" type="hidden">

					<table align="center" class="tbl-lista" width="70%">
						<tr>
							<th colspan="2	">Calcular �rbol Multi-Aloe</th>
						</tr>
						<tr>
							<td>
								<h5 style="color: blue">
									Este proceso puede durar alrededor de una hora! <br /> Fecha
									ultimo calculo.
									<c:if test="${fecha!='1900-01-01'}">
										<h4 style="color: red">
											<c:out value="${fecha}" />
										</h4>
									</c:if>
									<c:if test="${fecha=='1900-01-01'}">
										<h4 style="color: red">
											<c:out value="En Ejecuci�n!"></c:out>
										</h4>
									</c:if>
								</h5>
							</td>
						</tr>
					</table>
				</form>
			</c:if>
		</div>
	</div>
</body>
</html>