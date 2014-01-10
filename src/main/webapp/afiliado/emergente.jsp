<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/multinivel.css">
	<script src="../js/generico.js"></script>
	<script src="../js/afiliado/afiliado.js"></script>
    <link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body onload="javascript:document.forms[0].distribuidor.value=opener.document.forms[0].numeroDistribuidor.value;">
<div class="demo">

<form action="BuscadorAfiliado" name="buscarAfiliado" method="get">
<input type="hidden"  name="accion" value="C"/>
<input type="hidden"  name="actualizar"/>
<input type="hidden"  name="distribuidor"/>

<table width="50%" class="tabla" >
  <tr>
    <td width="173"  > Buscar Afliado</td>
    <td width="330"><table width="200"  align="right">
  <tr>
    <td><a href="javascript:buscarAfiliado()">  Consultar </a></td>
  </tr>
</table>
&nbsp;</td>
  </tr>
  <tr>
    <td>C&oacute;digo Empresario:</td>
    <td><input name="documento" type="text" id="codigo" value="<c:out value="${documento}"/>" maxlength="10"/></td>
  </tr>
  <tr>
    <td>Nombre Empresario: </td>
    <td><input name="nombre" type="text" id="nombre" value="<c:out value="${nombre}"/>" maxlength="50"/></td>
  </tr>
</table>
<br/>
<br/>
<div class="tituloLista">
LISTA AFILIADOS
</div>
<c:if test="${listaAfiliados!=null}"> 

<table width="100%" border="1" class="tabla">
  <tr>
    <td width="15%">Documento Empresario </td>
    <td width="60%">Nombre Empresario </td>
    <td width="25%">Seleccionar </td>
  </tr>
  <c:forEach var='Afiliado' items='${listaAfiliados}'>
  <tr>
    <td>  <c:out value='${Afiliado.cedula}'/></td>
    <td><c:out value='${Afiliado.nombre}'/>&nbsp;<c:out value='${Afiliado.apellido}'/></td>
      <td><a href="javascript:retornarAfiliado('<c:out value='${Afiliado.cedula}'/>','<c:out value='${Afiliado.nombre}'/> <c:out value='${Afiliado.apellido}'/>')">Seleccionar</a></td>
  </tr>
  </c:forEach>
</table>
</c:if> 

</form>
</div>
</body>
</html>
