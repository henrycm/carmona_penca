<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>MULTINIVEL - ALOE DE COLOMBIA</title>
<link rel="StyleSheet" type="text/css" href="bootstrap/css/bootstrap.css"></link>
<link rel="stylesheet" href="css/multinivel.css">
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="form_caja">
		<jsp:include page="encabezado.jsp"></jsp:include>
		<c:if test="${rol=='1'}">
			<jsp:include page="menu.jsp" />
		</c:if>
		<c:if test="${rol=='2'}">
			<jsp:include page="menuDistribuidor.jsp" />
		</c:if>
		<c:if test="${rol=='3'}">
			<jsp:include page="menuAfiliado.jsp" />
		</c:if>
		<iframe src="contenido.html" name="mainFrame"
			style="width: 100%; border: none; height: 70%"></iframe>
	</div>
</body>
</html>
