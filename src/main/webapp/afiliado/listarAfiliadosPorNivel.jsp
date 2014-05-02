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
<link rel="stylesheet" href="../css/multinivel.css">
<script src="../js/afiliado/afiliado.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
	<div align="center">
		<div class="demo">
			<form name="listaAfiliado" method="post">
				<input type="hidden" value="N" name="accion">
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:listarAfiliado();">Generar Reporte</a>
				</div>
				<table align="center" class="tbl-lista" width="70%">
					<tr>
						<th colspan="2">Listar Afiliados Por Nivel</th>
					</tr>
					<tr>
						<td>Tipo Reporte: <input type="radio" name="tipoReporte"
							value="PDF" checked="checked"> PDF <input type="radio"
							name="tipoReporte" value="Excel"> Excel
						</td>
					</tr>
					<tr>
						<td>Distribuidor: <select name="red">
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
	</div>
</body>
</html>