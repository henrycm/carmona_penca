<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>APLICACION MULTIALOE</title>
	<meta charset="utf-8">
	<title>jQuery UI Accordion - Default functionality</title>
	<link rel="stylesheet" href="../css/jquery/jquery.ui.all.css">
	<script src="../js/jquery/jquery-1.5.1.js"></script>
	<script src="../js/jquery/jquery.ui.core.js"></script>
	<script src="../js/jquery/jquery.ui.widget.js"></script>
	<script src="../js/jquery/jquery.ui.accordion.js"></script>
	<script src="../js/jquery/jquery.effects.core.js"></script>
    <script src="../js/jquery/jquery.effects.drop.js"></script>
 <script src="../js/jquery/jquery.ui.datepicker.js"></script>
 
 	<script src="../js/afiliado/afiliado.js"></script>
 	<script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">

	<link rel="stylesheet" href="../css/multinivel.css">
	
	
</head>
<c:if test='${mensaje!=null}'>
   <table class="tabla">
     <tr>
     
     <td>
     	
        Error: <c:out value="${mensaje}"></c:out>
     </td>
     </tr>
    
   </table> 
</c:if>

<c:if test='${afiliado!=null}'>
<body onload=javascript:seleccionarListaDesplegable('<c:out value="${afiliado.departamento}"/>',document.forma.departamento);>
</c:if>
<c:if test='${afiliado==null}'>
<body>
</c:if>
<div align="center">

<form name="forma" action="IndexArbolController" method="post">

<div class="titulo">
	CONSULTA DE DATOS DE JERARQUIA
</div>
<div align="left">
<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />

Código Nuevo Empresario: <input name="codigoEmpresario" type="text" size="30" />
<select name="letra">
            <option value="A" >A</option>
           <option value="B" >B</option>
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

           
         </select>
<a href="javascript:consultarJerarquia();">Consultar</a>



						
</div>

</form>
</body>
</html>


