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
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body
	onload="javascript:document.forms[0].distribuidor.value=opener.document.forms[0].numeroDistribuidor.value;">
	<div align="center">
		<div class="demo">
			<form action="BuscadorAfiliado" name="buscarAfiliado" method="get">
				<input type="hidden" name="accion" value="C" /> <input
					type="hidden" name="actualizar" /> <input type="hidden"
					name="distribuidor" />

				<table align="center" class="tbl-lista" width="70%" border="1">
					<tr>
						<th colspan="3">Buscar Afiliado</th>
					</tr>
					<tr align="left">
						<td>C&oacute;digo Empresario:<input name="documento"
							type="text" id="codigo" value="<c:out value="${documento}"/>"
							maxlength="10" />
						</td>
					</tr>
					<tr align="left">
						<td>Nombre Empresario:<input name="nombre" type="text"
							id="nombre" value="<c:out value="${nombre}"/>" maxlength="50" />
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="btn-group">
								<a class="btn btn-sm btn-default"
									href="javascript:buscarAfiliado();">Buscar Afiliado</a>
							</div>
						</td>
					</tr>
				</table>
				<table align="center" class="tbl-lista" width="98%" border="1">
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
									href="javascript:retornarAfiliado('<c:out value='${Afiliado.cedula}'/>','<c:out value='${Afiliado.nombre}'/> <c:out value='${Afiliado.apellido}'/>')">Seleccionar</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
