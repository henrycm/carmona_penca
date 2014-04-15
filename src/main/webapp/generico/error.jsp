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
	<div class="demo" align="center">
		<table align="center" class="tbl-lista" width="80%">
			<tr>
				<th colspan="2">El sistema MULTIALOE presento un error:</th>
			</tr>
			<tr>
				<td width="10%">
					<h4 style="color: blue">
						<c:out value="${error}"></c:out>
						Intente mas tarde o comuniquese con el administrador.
					</h4>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>