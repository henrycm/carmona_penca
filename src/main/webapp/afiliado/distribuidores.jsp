<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Maestro Producto</title>
<link rel="stylesheet" href="../css/multinivel.css" />
<script src="../js/generico.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css" />
</head>
<body>
	<div align="center">
		<div class="demo">
			<form>
				<input type="hidden" name="accion"
					value="<c:out value="${accion}"/>" />
				<c:if test="${listaDistribuidores!=null}">
					<table align="center" class="tbl-lista" width="70%" border="1">
						<tr>
							<th colspan="2">LISTA DE DISTRIBUIDORES</th>
						</tr>
						<tr>
							<th>Doc. Distribuidor</th>
							<th>Nombre Distribuidor</th>
						</tr>
						<c:forEach var='distribuidor' items='${listaDistribuidores}'>
							<tr>
								<td><c:out value='${distribuidor.cedula}' /></td>
								<td><c:out value='${distribuidor.nombre}' />
									<c:out value='${distribuidor.apellido}' /></td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</form>
		</div>
	</div>
</body>
</html>
