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
 <form name="listaAfiliado" method="post">
 
   <div align="right"> <br><a href="javascript:listarAfiliado()">LISTAR AFILIADOS</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
 <div class="demo" align="center">
     <div class="titulo">LISTAR AFILIADOS POR NIVEL</div>
   <table class="tabla" width="90%">
     <tr>
     
     <td>
     Listar afiliados en formato  PDF <input type="radio" name="formato" value="P" checked="checked"> 
     Excel <input type="radio" name="formato" value="E">   
     </td>
     </tr>
     <tr>
     
     <td>
       
     Red  de distribución a consultar: <select name="red">
     <option value="">Seleccione el distribuidor</option>
    
        <c:forEach var='distribuidor'   items='${listaDistribuidores}'>
     <option value="<c:out value='${distribuidor.cedula}'/>"><c:out value='${distribuidor.nombre}'/>&nbsp;<c:out value='${distribuidor.apellido}'/></option>
        </c:forEach>
      
       </select> 
         
     
    
     
     </td>
     </tr>  
   </table>  
    
     
    
     <input type="hidden" value="N" name="accion"> 
    
      
   </div>
   
    
   
<c:if test="${listaAfiliados!=null}"> 
      <div class="tituloLista"  >
      LISTA DE AFILIADOS POR NIVEL 
      </div>
   <table align="center" class="tabla" width="90%" border="1">
     <tr>
       <td width="10%">
        Documento de Ident.
       </td>
       <td width="35%">
          Nombre Afiliado
       </td>
       <td width="10">
         Documento de Id. Patrocinador. 
       </td>
       <td  width="35%">
        Nombre de Patrocinador
       </td>
      
      
          
     </tr>
     <c:forEach var='afiliado'   items='${listaAfiliados}'>
     
          <tr>
       <td>
         <c:out value='${afiliado.cedula}'/>
       </td>
       <td>
            <c:out value='${afiliado.nombre}'/>
       </td>
       <td>
         <c:out value='${afiliado.cedulaPapa}'/>
       </td>
       <td>
         <c:out value='${afiliado.nombrePadre}'/>
       </td>
      
      
     </tr>
     </c:forEach>
     
   </table>

</c:if>  
 </form>
</body>
</html>