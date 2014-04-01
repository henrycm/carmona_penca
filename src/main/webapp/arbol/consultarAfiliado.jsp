<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>APLICACION MULTIALOE</title>
<meta charset="utf-8">
<title>jQuery UI Accordion - Default functionality</title>
<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/jquery/jquery-1.5.1.js"></script>
<script src="../js/jquery/jquery.ui.core.js"></script>
<script src="../js/jquery/jquery.ui.widget.js"></script>
<script src="../js/jquery/jquery.ui.accordion.js"></script>
<script src="../js/jquery/jquery.effects.core.js"></script>
<script src="../js/jquery/jquery.effects.drop.js"></script>
<script src="../js/jquery/jquery.ui.datepicker.js"></script>

<script src="../js/afiliado/afiliado.js"></script>
<script src="../js/generico.js"></script>

<link rel="stylesheet" href="../css/jquery/demos.css">

<link rel="stylesheet" href="../css/multinivel.css">


</head>
<c:if test='${mensaje!=null}'>
	<table class="tabla">
		<tr>

			<td>Error: <c:out value="${mensaje}"></c:out>
			</td>
		</tr>

	</table>
</c:if>

<body onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
	<div align="center">
		<div class="demo">

			<form name="forma" action="IndexArbolController" method="post">
				<input name="accion" type="hidden"
					value="<c:out value='${accion}'/>" />
				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:consultarJerarquia()">Consultar Jerarquia</a>
				</div>

				<table align="center" class="tbl-lista" width="70%">
					<tr>
						<th colspan="2	">Consulta de Datos de Jerarquia</th>
					</tr>
					<tr>
						<td>Código del Empresario: <input name="codigoEmpresario"
							type="text" size="30" /> <select name="letra">
								<option value="A">A</option>
								<option value="B">B</option>
								<option value="C">C</option>
								<option value="D">D</option>
								<option value="E">E</option>
								<option value="F">F</option>
								<option value="G">G</option>
								<option value="H">H</option>
								<option value="I">I</option>
								<option value="J">J</option>
								<option value="K">K</option>
								<option value="L">L</option>
								<option value="M">M</option>
								<option value="N">N</option>
								<option value="O">O</option>
						</select>
						</td>
						<td>Periodo: <select name="mes">

						</select> <select name="ano">
						</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>


