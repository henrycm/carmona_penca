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
		$( "#fecha" ).datepicker({maxDate: '-18y'});
		$( "#fecha" ).datepicker( "option", "showAnim", "drop" );
		
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
<body onload="javascript:cargarCombosAfiliado('<c:out value="${afiliado.departamento}"/>','<c:out value="${afiliado.departamentoResidencia}"/>','<c:out value="${afiliado.tipoDocumento}"/>','<c:out value="${afiliado.estadoCivil}"/>','<c:out value="${afiliado.banco}"/>');cargarTipoCta('<c:out value="${afiliado.tipoCuenta	}"/>');">
</c:if>
<c:if test='${afiliado==null}'>
<body>
</c:if>
<div align="center">

<form name="forma" action="AfiliadoFrontController" method="post">

<div align="right" id="opcion">
		<a href="javascript:eliminarAfiliado();">Eliminar</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div align="center" class="titulo">
	BORRAR AFILIADO
</div>
<div align="left">
<input name="accion" type="hidden" value="<c:out value='${accion}'/>" />
<c:if test='${afiliado==null}'>

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
<a href="javascript:consultarAfiliado();">Consultar</a>
</c:if>

<c:if test='${afiliado!=null}'>
			<input type="hidden" name="borrar"  >			

Código Nuevo Empresario: <input name="codigoEmpresario" type="text" size="30" value="<c:out value='${afiliado.cedula}'/>"  readonly="readonly"/>

</c:if>
</div>
<div class="demo">
<c:if test='${afiliado!=null}'>

						<div id="accordion">
							<h3><a href="#">DATOS PERSONALES</a></h3>
	<div "datosPersonales">
			<input type="hidden" name="rol" value="<c:out value='${afiliado.tipoAfiliado}'/>" >			
	<table width="100%"  >
    <tr align="left">
      <td width="25%">Nombre:
      <input name="nombre" type="text" size="20"  maxlength="40" value="<c:out value='${afiliado.nombre}'/>"  /></td>
      <td width="25%">Apellidos Completos:
        <input name="apellido" type="text" maxlength="50" size="15" value="<c:out value='${afiliado.apellido}'/>"/></td>
      
      <td width="25%">Identificación:<br>
      
        <select name="tipoDocumento">
          <option value="C.C.">C.C.</option>
          <option value="Nit">Nit</option>
        </select>
         <input name="numeroIdentificacion" type="text" size="13" value="<c:out value='${afiliado.cedula}'/>" maxlength="13" /> 
       </td>
    <td width="25%">Fecha de Nac: 
        <input name="fechaNacimiento" type="text" class="ui-datepicker-calendar" id="fecha" size="15" value="<c:out value='${afiliado.fechaNacimiento}'/>"/></td>
    </tr>
    <tr align="left">
      <td>
        Lugar de Nac.:
        Ciudad
          <input name="ciudadNacimiento" type="text" size="13" value="<c:out value='${afiliado.ciudad}'/>" />
        
      </td>
      <td height="26">
        Dpto Nac
        
        <select id="departamento" name="departamento">
    
         <option value="">Elija el depart...</option>
    
        <c:forEach var='dept'   items='${listaDepartamentos}'>
     <option value="<c:out value='${dept.descripcion}'/>"><c:out value='${dept.descripcion}'/></option>
        </c:forEach>
       </select> 
         
    	</td>
      <td> Profesion u Ocupación:<input name="profesion" type="text" size="15" value="<c:out value='${afiliado.ocupacion}'/>"/></td>
      <td width="18%">Estado Civil:
         <select name="estadoCivil">
    
            <option value="SOLTERO" >Soltero</option>
            <option value="CASADO" >Casado</option>
            <option value="UNION LIBRE" >Unión Libre</option>
            <option value="VIUDO" >Viudo</option>
            <option value="SEPARADO" >Separado</option>
             <option value="OTRO" >Otro</option>
         </select>
          
      </td>
      
    </tr>
 
    <tr align="left">
     <td>Barrio:<input name="barrio" type="text" size="25" value="<c:out value='${afiliado.barrio}'/>" /></td>
    <td height="24" >Dirección Resid. (completa):
        <input name="direccion" type="text" size="15"  value="<c:out value='${afiliado.direccion}'/>" />
      </td>
     
      <td>Ciudad:
        <input name="ciudadResidencia" type="text" size="15" value="<c:out value='${afiliado.ciudadResidencia}'/>"/></td> 
      <td height="51">Departamento:
        <select id="departamentoResidencia" name="departamentoResidencia" size="1">
         <option value="">Elija el depart...</option>
         <c:forEach var='dept'   items='${listaDepartamentos}'>
     		<option value="<c:out value='${dept.descripcion}'/>"><c:out value='${dept.descripcion}'/></option>
        </c:forEach>
        </select></td>    
   
    </tr>
    
    <tr align="left">
  
      <td>Teléfono:
        <input name="telefono" type="text" size="10" value="<c:out value='${afiliado.telefono}'/>"/></td>
      <td > 
      Celular o Beeper: 
        <input name="celular" type="text" size="10" value="<c:out value='${afiliado.celular}'/>" />
        </td>
         <td>
      E-mail:
      <input name="email" type="text" size="20" maxlength="50"  value="<c:out value='${afiliado.email}'/>" /></td>
    <td colspan="2">Nomb Conyugue/Tutor:
       <input name="nombreConyugue" type="text" size="15"  value="<c:out value='${afiliado.nombreConyugue}'/>"/>
      </td>
 </tr>
  
    
    
  </table>
						 </p>
						</div>

<h3><a href="#">DATOS PARA PAGO DE RECONOCIMIENTOS MONETARIOS.</a></h3>
<div >
						
				
				<table width="100%"  >
    <tr>
     <td colspan="2">
       PAGAR A DISTRIBUIDOR 
        <input name="pagarADistribuidor" type="checkbox"  onclick="javascript:validarChequeCta()" />
		
	</td>
    
    
      <td width="50%">Cuenta Número:
	        <input name="numeroCuenta" type="text" size="20"  value="<c:out value='${afiliado.cuentaNro}'/>" maxlength="20"/>
       </td>
       <td width="50%">
	Entidad:<select id="entidadBancaria" name="entidadBancaria">
             <option value="">Elija el banco</option>
            <c:forEach var='banco'   items='${listaBancos}'>
     			<option value="<c:out value='${banco.codigo}'/>"><c:out value='${banco.descripcion}'/></option>
        	</c:forEach>
       	   </select> 
	 </td>
      
    </tr>
    <tr>
      
      <td width="50%">
      Tipo de cta.  Ahorro 
        <input name="tipoCuenta" type="radio" value="AHORRO" checked="checked" />
		Cte.
		<input name="tipoCuenta" type="radio" value="CRTE" />
	</td><td width="50%">	
		&nbsp;
	 </td>
    </tr>
    <tr>
      <td colspan="2">Titular de la cuenta (Si es diferente al empresario) </td>
      </tr>
     <tr>
      <td width="50%">
        Nombre:
		<input name="nombreTitular" type="text" size="30" maxlength="50" value="<c:out value='${afiliado.nombreTitularCta}'/>"/>
		</td>
		<td width="50%">
		C.C. No.<input name="documentoTitular" type="text" size="15"  maxlength="15"  value="<c:out value='${afiliado.titularCuenta}'/>" /></td>
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
      <c:out value='${patrocinador.nombre}'/> &nbsp;<c:out value='${patrocinador.apellido}'/>
     </td>
     </tr>
     <tr>
      <td align="left">
      Número de Empresario: <input name="numeroEmpresario" type="hidden"  value="<c:out value='${afiliado.cedulaPapa}'/>" /> 
      <c:out value='${afiliado.cedulaPapa}'/>
      
      <input type="hidden" name="red"  value="<c:out value='${afiliado.red}'/>" >
      
      </td>
  </tr>
  <tr>
    <td>En mi calidad de Patrocinador postulante que suscribe el presente convenio vengo recomendarla(o) y me consta que se trata de una persona<br />
honorable y que representará adecuadamente la imagen y los productos de la empresa en su actuar como empresario independiente. </td>
  </tr>
</table>

<table width="917" align="left" >
  <tr>
    <td align="left">Nombres y Apellidos del distribuidor
      <input name="nombreDistribuidor" type="text" size="50"  value="<c:out value='${distribuidor.nombre}'/> <c:out value='${distribuidor.apellido}'/>" readonly="readonly" onclick="javascript:document.forma.nombrePatrocinador.readOnly =true;" />
     </td>
     </tr>
     <tr>
      <td align="left">
      Codigo de Empresario(Distribuidor): <input name="numeroDistribuidor"  value="<c:out value='${distribuidor.cedula}'/>" type="text" size="15"  readonly="readonly" onclick="javascript:document.forma.numeroEmpresario.readOnly =true;"/> <a href="javascript:abrirBuscarDistribuidor()" >Buscar Distribuidor</a>
      <input type="hidden" name="numeroCuentaDistribuidor" value="<c:out value='${distribuidor.cuentaNro}'/>">
         <input type="hidden" name="tipoCuentaDistribuidor" value="<c:out value='${distribuidor.tipoCuenta}'/>"> 
         <input type="hidden" name="bancoDistribuidor" value="<c:out value='${distribuidor.banco}'/>"> 
         
      
      </td>
  </tr>
  </table>

						</div>
						</div>

						
						
</c:if>


						
</div>

</form>
</div>
</body>
</html>


