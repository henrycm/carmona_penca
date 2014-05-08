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
<body>
	<div align="center">
		<div class="demo">
			<form action="BuscadorDistribuidor" name="buscadorDistribuidor"
				method="get">
				<input type="hidden" name="accion" value="C" /> <input
					type="hidden" name="actualizar" />

				<table align="center" class="tbl-lista" width="70%" border="1">
					<tr>
						<th colspan="3">Buscar Distribuidor</th>
					</tr>
					<tr align="left">
						<td>C&oacute;digo Empresario:<input name="documento"
							type="hidden"><input name="codigoEmpresario" type="text"
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
					<tr align="left">
						<td>Nombre Empresario:<input name="nombre" type="text"
							id="nombre" value="<c:out value="${nombre}"/>" maxlength="11" /></td>
					</tr>
					<tr>
						<td colspan="3">
							<div class="btn-group">
								<a class="btn btn-sm btn-default"
									href="javascript:buscarDistribuidor();">Buscar Distribuidor</a>
							</div>
						</td>
					</tr>
				</table>
				<table align="center" class="tbl-lista" width="98%" border="1">
					<tr>
						<th colspan="3">Lista de Distribuidores</th>
					</tr>
					<tr>
						<th>Documento</th>
						<th>Nombre Empresario</th>
						<th>Seleccionar</th>
					</tr>
					<c:if test="${listaDistribuidor!=null}">
						<c:forEach var='distribuidor' items='${listaDistribuidor}'>
							<tr>
								<td><c:out value='${distribuidor.cedula}' /></td>
								<td><c:out value='${distribuidor.nombre}' />&nbsp;<c:out
										value='${distribuidor.apellido}' /></td>
								<td><a
									href="javascript:retornarDistribuidor('<c:out value='${distribuidor.cedula}'/>','<c:out value='${distribuidor.nombre}'/> <c:out value='${distribuidor.apellido}'/>','<c:out value='${distribuidor.tipoCuenta}'/>','<c:out value='${distribuidor.cuentaNro}'/>','<c:out value='${distribuidor.banco}'/>')">Seleccionar</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
