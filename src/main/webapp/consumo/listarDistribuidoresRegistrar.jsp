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
<script src="../js/consumo/consumo.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
<link rel="stylesheet" href="../css/multinivel.css">
</head>
<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div class="demo" align="center">
		<form name="liquidacion" method="post" action="IndexConsumo">
			<input name="accion" type="hidden" value="E"> <input
				name="periodo" type="hidden">
			<div class="btn-group">
				<a class="btn btn-sm btn-default"
					href="javascript:registrarConsumoDistribuidor();">Buscar
					Periodo</a>
			</div>
			<table align="center" class="tbl-lista" width="80%">
				<tr>
					<th>Registrar Consumos Afiliado</th>
				</tr>
				<tr>
					<td>Distribuidor a consultar: <select name="distribuidor">
							<option value="">Seleccione el distribuidor</option>
							<c:forEach var='distribuidor' items='${listaDistribuidores}'>
								<option value="<c:out value='${distribuidor.cedula}'/>"><c:out
										value='${distribuidor.nombre}' />&nbsp;
									<c:out value='${distribuidor.apellido}' /></option>
							</c:forEach>
					</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>