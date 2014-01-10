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
	<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	</script>
	<script>
 
 $(function() {
		$( "#fecha" ).datepicker();
		$( "#fecha" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha1" ).datepicker();
		$( "#fecha1" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha2" ).datepicker();
		$( "#fecha2" ).datepicker( "option", "showAnim", "drop" );
		$( "#fecha3" ).datepicker();
		$( "#fecha3" ).datepicker( "option", "showAnim", "drop" );
	});
	</script>
 <style>
 <style>
#accordion{
   height:100px;
    
}
</style>
</head>
<c:if test='${afiliado!=null}'>
<body onload=javascript:seleccionarListaDesplegable('<c:out value="${afiliado.departamento}"/>',document.forma.departamento);>
</c:if>
<c:if test='${afiliado==null}'>
<body>
</c:if>
<div align="center">

<form name="forma" action="AfiliadoFrontController" method="post">

<div align="center">
	CONSULTA DE DATOS DE AFILIADO
</div>
<div align="left">
<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />
<c:if test='${afiliado==null}'>

Código Nuevo Empresario: <input name="codigoEmpresario" type="text" size="15"  maxlength="11" />
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
<a href="javascript:consultarAfiliado();">Consultar</a>


</c:if>




<c:if test='${afiliado!=null}'>
Código Nuevo Empresario: <input name="codigoEmpresario" type="text" size="15" value="<c:out value='${afiliado.cedula}'/>"  readonly="readonly"/>
<a href="javascript:actualizarAfiliadoADistribuidor();">Actualizar A Distribuidor</a>
<input name="actualizar" type="hidden" value="A" />

</c:if>



</div>
<div class="demo">
<c:if test='${afiliado!=null}'>

						<div id="accordion">
							<h3><a href="#">DATOS PERSONALES</a></h3>
	<div "datosPersonales">
						
	<table width="100%"  >
    <tr align="left">
      <td width="25%">Nombre:
      <c:out value='${afiliado.nombre}'/></td>
      <td width="25%">Apellidos Completos:
        <c:out value='${afiliado.apellido}'/>
        </td>
      
      <td width="25%">Identificación:<br>
      
        <c:out value='${afiliado.cedula}'/> 

       </td>
<td width="25%">Fecha de Nac: 
       <c:out value='${afiliado.fechaNacimiento}'/></td>
    </tr>
    <tr align="left">
      <td>
        Lugar de Nac.:
        Ciudad
          <c:out value='${afiliado.ciudad}'/>
        
      </td>
      <td height="26">
        Dpto
        
        <c:out value='${afiliado.departamento}'/>
         
    	</td>
      <td> Profesion u Ocupación:<c:out value='${afiliado.ocupacion}'/></td>
      <td width="18%">Estado Civil:
         <c:out value='${afiliado.estadoCivil}'/>
          
      
    </tr>
 
    <tr align="left">
     <td>Barrio:
        <c:out value='${afiliado.barrio}'/></td>
      
      
    <td height="24" >Dirección Resid. (completa):
        <c:out value='${afiliado.direccion}'/>
      </td>
     

      <td>Ciudad:
       <c:out value='${afiliado.ciudadResidencia}'/></td>
    <td height="51">Departamento:
        <c:out value='${afiliado.departamentoResidencia}'/></td>
    </tr>
    
    <tr align="left">
      
      <td>Teléfono:
        <c:out value='${afiliado.telefono}'/></td>
      <td > 
      Celular o Beeper: 
       <c:out value='${afiliado.celular}'/>
       </td>
        <td>
      E-mail:
      <c:out value='${afiliado.email}'/></td>

        <td width="21%" height="24" align="">Nombre del Conyugue/Tutor:
       <c:out value='${afiliado.nombreConyugue}'/>
      </td>
       
    </tr>
    
  </table>
						 </p>
						</div>
						
							<h3><a href="#">DATOS PARA PAGO DE RECONOCIMIENTOS MONETARIOS.</a></h3>
							<div >
						
				
				<table width="100%"  >
    <tr>
      <td width="50%">Cuenta Número:
	        <c:out value='${afiliado.cuentaNro}'/>
       </td>
       <td width="50%">
	Entidad: <c:out value='${banco.descripcion}'/>
      
    
	 </td>
      
    </tr>
    <tr>
      
      <td>
      Tipo de cta.  Ahorro 
        <c:out value='${afiliado.tipoCuenta}'/>
      
    
	</td><td>	
		&nbsp;
      
    
	 </td>
    </tr>
    <tr>
      <td colspan="2">Titular de la cuenta (Si es diferente al empresario) </td>
      </tr>
     <tr>
      <td>
        Nombre:
		<c:out value='${afiliado.nombreTitularCta}'/>
		</td>
		<td>
		C.C. No.<c:out value='${afiliado.titularCuenta}'/></td>
          </tr>
        <tr>
         <td colspan="2">Autorizo que las bonificaciones generadas en mi red de MULTI - ALOE sean consignadas en esta cuenta.
 		</td>
        </tr>  
  </table>
				
				</div>
							<h3><a href="#">DATOS DEL PATROCINADOR</a></h3>
							<div id="datosPatrocinador">
						
						<table width="917" align="left" >
  <tr>
    <td align="left">Nombres y Apellidos(completos)
     
      <c:out value='${patrocinador.nombre}'/>&nbsp;<c:out value='${patrocinador.apellido}'/>
     </td>
     </tr>
     <tr>
      <td align="left">
     Número de Empresario:
      <c:out value='${afiliado.cedulaPapa}'/>
      
      <input type="hidden" name="red">
      
      </td>
  </tr>
  <tr>
    <td>En mi calidad de Patrocinador postulante que suscribe el presente convenio vengo recomendarla(o) y me consta que se trata de una persona<br />
honorable y que representará adecuadamente la imagen y los productos de la empresa en su actuar como empresario independiente. </td>
  </tr>
</table>
					
					<table width="917" align="left" >
     <tr>
      <td align="left">
      Codigo de Empresario(Distribuidor): <c:out value='${distribuidor.cedula}'/>
     
      </td>
  </tr>
 
  <tr>
    <td align="left">Nombres y Apellidos del distribuidor
     <c:out value='${distribuidor.nombre}'/>&nbsp; <c:out value='${distribuidor.apellido}'/>
     </td>
     </tr>
  </table>	
						</div>
						</div>

						
						
</c:if>


						
</div>
<c:if test="${noExisteAfiliado==true}">
 
   <table class="tabla">
     <tr>
     
     <td>
     No existen los datos del afiliado en el sistema comuniquese con el administrador.
     </td>
     </tr>
    
   </table> 

</c:if>


</form>
</body>
</html>


