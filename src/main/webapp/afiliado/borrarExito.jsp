<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/multinivel.css">
<link rel="StyleSheet" type="text/css"
	href="../bootstrap/css/bootstrap.css" />
<script src="../js/afiliado/afiliado.js"></script>
<link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
	<div align="center">
		<div class="demo">

			<table align="center" class="tabla" width="80%" border="1">
				<tr>
					<td width="10%">
						<h4 style="color: blue">
							<c:out value="${mensaje}" />
						</h4>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>