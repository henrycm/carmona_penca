<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/generico.js"></script>
<script src="../js/afiliado/afiliado.js"></script>
<script src="../js/consumo/consumo.js"></script>
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body
	onload="javascript:cargaPeriodoConsumo('<c:out value="${periodo}"/>');">
	<div align="center">
		<div class="demo">
			<form action="../administracion/BuscadorAfiliado"
				name="buscarAfiliado" method="get">
				<input type="hidden" name="accion" value="A" /> <input
					name="distribuidor" type="hidden"
					value="<c:out value="${distribuidor}"/>"> <input
					type="hidden" name="actualizar" /> <input type="hidden"
					name="documento" />

				<div class="btn-group">
					<a class="btn btn-sm btn-default"
						href="javascript:buscarAfiliadoConsumo();">Consultar</a>
				</div>
				<table align="center" class="tbl-lista" width="50%">
					<tr>
						<th colspan="2">Buscar Afliado</th>
					</tr>
					<tr>
						<td align="right">C&oacute;digo Empresario:</td>
						<td align="left"><input name="codigoEmpresario" type="text"
							id="codigo" value="<c:out value="${documento}"/>" maxlength="11" />
							<select name="letra">
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
						</select></td>
					</tr>
					<tr>
						<td align="right">Nombre del empresario:</td>

						<td align="left"><input name="nombre" type="text" id="nombre"
							value="<c:out value="${nombre}"/>" maxlength="30" /></td>
					</tr>
					<tr>
						<td align="right">Periodo Consumo:</td>
						<td align="left"><select name="mes">

						</select> <select name="ano">
						</select> <input name="cedula" type="hidden" id="cedula" /> <input
							name="fechaConsumo" type="hidden" id="fechaConsumo" /></td>
					</tr>
				</table>
				<table align="center" class="tbl-lista" width="80%" border="1">
					<tr>
						<th colspan="3">Lista de Afiliados</th>
					</tr>
					<tr>
						<th>Documento</th>
						<th>Nombre Empresario</th>
						<th>Seleccionar</th>
					</tr>
					<c:if test="${listaAfiliados!=null}">
						<c:forEach var='Afiliado' items='${listaAfiliados}'>
							<tr>
								<td><c:out value='${Afiliado.cedula}' /></td>
								<td><c:out value='${Afiliado.nombre}' />&nbsp;<c:out
										value='${Afiliado.apellido}' /></td>
								<td><a
									href="javascript:seleccionarAfiliado('<c:out value='${Afiliado.cedula}'/>')"
									target="mainFrame">Seleccionar</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
