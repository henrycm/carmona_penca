<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/multinivel.css">
<script src="../js/afiliado/afiliado.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body class="fondo">
	<div class="demo" align="center">
		<table class="tabla">
			<tr>
				<td>El pedido realizado presento un error:<c:out
						value="${error}"></c:out>.Intente mas tarde o comuniquese con el
					administrador.
				</td>
			</tr>
		</table>
	</div>
</body>
</html>