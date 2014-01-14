<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aplicacion MultiALOE</title>
<link rel="stylesheet" href="../css/multinivel.css">
 <script src="../js/liquidacion/compensacion.js"></script>
  <script src="../js/generico.js"></script>
 
	<link rel="stylesheet" href="../css/jquery/demos.css">


</head>

  


<body class="fondo" onload="javascript:cargarPeriodo('<c:out value="${periodo}"/>')">
 
 <c:if test="${mensaje==null}">
 <form name="liquidacion" method="post" action="ValidarCompensacionDistribuidor">
 <div align="right" id="opcion">
		<a href="javascript:validarCompensacionDistribuidor();">Validar DIstribuidor</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
 <input name="accion" type="hidden" value="<c:out value="${accion}"/>">
  <input name="periodo" type="hidden">
 
 <div class="demo" align="center">
     <div class="titulo">Validar Distribuidor Compensacion Periodo</div>
     <table class="tabla" width="90%">
   
     <tr>
        <td>
         Periodo:
         <select name="mes">
              </select>
         <select name="ano">
         </select>  
  
     
     </td>
    
          </tr>
          <tr>
     
     <td>
       
      Distribuidor: <select name="distribuidor">
     <option value="">Seleccione el distribuidor</option>
    
        <c:forEach var='distribuidor'   items='${listaDistribuidores}'>
     <option value="<c:out value='${distribuidor.cedula}'/>"><c:out value='${distribuidor.nombre}'/>&nbsp;<c:out value='${distribuidor.apellido}'/></option>
        </c:forEach>
      
       </select> 
        
     
     </td>
     </tr> 
     
   </table>  
    </c:if>   
   <c:if test="${mensaje==true}">
	        <table class="tabla">
	     <tr>
	     
	     <td>
	       Se ha registrado la validacion de compensacion  del distribuidor exitosamente.  
	      </td>
	     </tr>
	    
	   </table>  
	</c:if>

   <c:if test="${mensaje==false}">
	        <table class="tabla">
	     <tr>
	     
	     <td>
	       El distribuidor ya estaba registrado anteriormente para ese periodo, si tiene alguna duda por favor cominiquese con el administrador.  
	      </td>
	     </tr>
	    
	   </table>  
	</c:if>
 </form>
</body>


</html>