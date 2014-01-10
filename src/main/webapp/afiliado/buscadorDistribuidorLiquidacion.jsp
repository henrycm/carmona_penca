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
		<script src="../js/consumo/consumo.js"></script>
	
    <link rel="stylesheet" href="../css/jquery/demos.css">
</head>
<body>
<div class="demo">

<form action="../administracion/BuscadorAfiliado" name="buscarAfiliado" method="get">
<input type="hidden"  name="accion" value="A"/>
<input type="hidden"  name="actualizar"/>
<input type="hidden"  name="documento"/>

<table width="50%" class="tabla" >
  <tr>
    <td width="173" > Buscar Distribuidor</td>
    <td width="330"><table width="200"  align="right">
  <tr>
    <td><a href="javascript:if(asignarDocumento()){buscarDistribuidorLiquidacion();}">  Consultar </a></td>
  </tr>
</table>
&nbsp;</td>
  </tr>
  <tr>
    <td>C&oacute;digo Empresario:</td>
    
    <td><input name="codigoEmpresario" type="text" id="codigo" value="<c:out value="${documento}"/>" maxlength="11"/>
    <select name="letra">
            <option value="A" >A</option>
<!--       <option value="B" >B</option>
           <option value="C" >C</option>
           <option value="D" >D</option>
           <option value="E" >E</option>
           <option value="F" >F</option>
           <option value="G" >G</option>
           <option value="H" >H</option>
           <option value="I" >I</option>
           <option value="J" >J</option>
           <option value="K" >K</option>
           <option value="L" >L</option>
           <option value="M" >M</option>
           <option value="N" >N</option>
           <option value="O" >O</option>
-->
           
         </select>
    </td>
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
     <c:if test="${accion=='D'}">
      	<td><a  href="../compensacion/ListarAfiliadosPagoPremiacion?accion=A&cedula=<c:out value='${Afiliado.cedula}'/>"  target="mainFrame">Seleccionar</a></td>
      </c:if>
      <c:if test="${accion=='E'}">
      	<td><a  href="../compensacion/ListarAfiliadosPagoCompensacion?accion=A&cedula=<c:out value='${Afiliado.cedula}'/>"  target="mainFrame">Seleccionar</a></td>
      </c:if>
 
  </tr>
  </c:forEach>
</table>
</c:if> 

</form>
</div>
</body>
</html>
