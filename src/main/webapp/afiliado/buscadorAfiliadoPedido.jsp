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
<body>
<div class="demo">

<form action="../administracion/BuscadorAfiliado" name="buscarAfiliado" method="get">
<input type="hidden"  name="accion" value="P"/>
<input type="hidden"  name="actualizar"/>

<table width="50%" class="tabla" >
  <tr>
    <td width="173"  > Buscar Afliado</td>
    <td width="330"><table width="200"  align="right">
  <tr>
    <td><a href="javascript:buscarAfiliadoPedido()">  Consultar </a></td>
  </tr>
</table>
&nbsp;</td>
  </tr>
  <tr>
    <td>C&oacute;digo Empresario:</td>
    <td><input name="documento" type="text" id="codigo" value="<c:out value="${documento}"/>" maxlength="10"/></td>
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
    <td width="25%">Red </td>
    <td width="25%">Seleccionar </td>
  </tr>
  <c:forEach var='Afiliado' items='${listaAfiliados}'>
  <tr>
    <td>  <c:out value='${Afiliado.cedula}'/></td>
    <td><c:out value='${Afiliado.nombre}'/>&nbsp;<c:out value='${Afiliado.apellido}'/></td>
     <td><c:out value='${Afiliado.red}'/></td>
      <td><a  href="../pedido/IndexPedido?accion=A&cedula=<c:out value='${Afiliado.cedula}'/>"  target="mainFrame">Seleccionar</a></td>
  </tr>
  </c:forEach>
</table>
</c:if> 

</form>
</div>
</body>
</html>
