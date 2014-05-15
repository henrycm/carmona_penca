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
<script src="../js/pedido/pedido.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
	<div class="demo" align="center">
		<table class="tabla">
			<tr>
				<th><c:out
						value="El consumo no fue eliminado del sistema presento un error."></c:out>
				</th>
			</tr>
			<tr>
				<td>
					<h5 style="color: red">
						<c:out value="${error}" />
					</h5>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>