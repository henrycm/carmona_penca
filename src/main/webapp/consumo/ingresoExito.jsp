<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/consumo/consumo.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
	<div class="demo" align="center">
		<table align="center" class="tbl-lista" width="80%">
			<tr>
				<th colspan="2">El pedido a la empresa MULTIALOE fue exitoso.
					Desea Imprimir la constancia.</th>
			</tr>
			<tr>
				<td>
					<form name="imprimirReporte" method="post">
						<input type="hidden" name="codigoConsumo"
							value='<c:out value="${codigoConsumo}"></c:out>'>
						<div class="btn-group">
							<a class="btn btn-sm btn-default"
								href="javascript:imprimirConstanciaPedido()">Imprimir
								Constancia Consumo</a>
						</div>
					</form>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>