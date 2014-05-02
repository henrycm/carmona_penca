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
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
	<div align="center">
		<div class="demo">
			<form name="liquidacion" method="post">
				<input name="accion" type="hidden" value="W"> <input
					name="periodo" type="hidden">

				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:validarAfiliacionesDistribuidor();">Activar
						Afiliaciones</a>
				</div>
				<table align="center" class="tbl-lista" width="70%">
					<tr>
						<th colspan="2">Activar Afiliaciones por Distribuidor</th>
					</tr>
					<tr>
						<td>Distribuidor: <select name="distribuidor">
								<option value="">Seleccione el distribuidor</option>
								<c:forEach var='distribuidor' items='${listaDistribuidores}'>
									<option value="${distribuidor.cedula}">${distribuidor.nombre}&nbsp;${distribuidor.apellido}
										- ${distribuidor.numeroAfiliados}
										<c:out value="-de-" /> ${distribuidor.nmAfiliadosPermitidos}
									</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td>Cantidad: <input name="cantidad" maxlength="5">
						</td>
					</tr>
				</table>
				<c:if test="${not empty mensaje}">
					<table align="center" class="tabla" width="80%" border="1">
						<tr>
							<td width="10%">
								<h4 style="color: blue">
									<c:out value="${mensaje}" />
								</h4>
							</td>
						</tr>
					</table>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>