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
<script src="../js/afiliado/afiliado.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div align="center">
		<div class="demo">
			<form name="liquidacion" method="post" action="ControlLiquidacion">
				<input name="accion" type="hidden"
					value="<c:out value="${accion}"/>"> <input name="periodo"
					type="hidden">
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:generarAfiliacionesDistribuidorPeriodo();">Generar
						Reporte</a>
				</div>
				<table align="center" class="tbl-lista" width="70%">
					<tr>
						<th colspan="2">Listar Afiliaciones Por Distribuidor Por
							Periodo</th>
					</tr>
					<tr>
						<td>Periodo: <select name="mes">

						</select> <select name="ano">
						</select>
						</td>
					</tr>
					<tr>
						<td>Tipo Reporte: <input type="radio" name="tipoReporte"
							value="PDF" checked="checked"> PDF <input type="radio"
							name="tipoReporte" value="Excel"> Excel
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>