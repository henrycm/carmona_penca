package co.com.multinivel.backend.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.TreeNode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.frontend.arbol.TreeNodeImpl;
import co.com.multinivel.shared.dto.DatosArbol;

@Repository
@Transactional
public class ArbolDAOImp implements ArbolDAO {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public DatosArbol getArbol(String paramString) throws Exception {		
		StringBuffer localStringBuffer = new StringBuffer();

		localStringBuffer.append("  SELECT CONCAT(A.NOMBRE,' ', A.APELLIDO)NOMBRE_AFILIADO,  ");
		localStringBuffer.append("  AFI.NIVEL,afi.CEDULA,  ");
		localStringBuffer.append("  afi.CEDULA_PAPA,  ");
		localStringBuffer.append("  AFI.TELEFONO,  ");
		localStringBuffer.append("  AFI.EMAIL,  ");
		localStringBuffer.append("  AFI.DIRECCION,  ");
		localStringBuffer.append("  AFI.CELULAR,  ");
		localStringBuffer
				.append("  (SELECT CONCAT(P.NOMBRE,' ', P.APELLIDO) FROM T_AFILIADOS P WHERE P.CEDULA=  afi.CEDULA_PAPA)NOMBRE_PAPA,  ");
		localStringBuffer
				.append("  (SELECT P.TELEFONO FROM T_AFILIADOS P WHERE P.CEDULA=  afi.CEDULA_PAPA)TELEFONO_PAPA,  ");
		localStringBuffer
				.append("  (SELECT P.EMAIL FROM T_AFILIADOS P WHERE P.CEDULA=  afi.CEDULA_PAPA)EMAIL_PAPA,  ");
		localStringBuffer
				.append("  (SELECT P.DIRECCION FROM T_AFILIADOS P WHERE P.CEDULA=  afi.CEDULA_PAPA)DIRECCION_PAPA,  ");
		localStringBuffer
				.append("  (SELECT P.CELULAR FROM T_AFILIADOS P WHERE P.CEDULA=  afi.CEDULA_PAPA)CELULAR_PAPA  ");

		localStringBuffer.append("   FROM  ");
		localStringBuffer
				.append("   (SELECT H.CEDULA,'0' CEDULA_PAPA, 0 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
		localStringBuffer.append("\t\t\t\t \tFROM T_AFILIADOS H ");
		localStringBuffer.append("\t\t\t\t   WHERE H.CEDULA='" + paramString + "' ");

		localStringBuffer.append("        UNION ALL ");

		localStringBuffer
				.append("       SELECT P.CEDULA,P.CEDULA_PAPA, 1 NIVEL, H.CEDULADISTRIBUIDOR,H.TELEFONO,H.EMAIL,H.DIRECCION,H.CELULAR ");
		localStringBuffer.append("\t\t\t \tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \tON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t   UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 2 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR FROM ");
		localStringBuffer.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t \tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \tON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   AND H.CEDULA='" + paramString
				+ "')NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \tON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 3 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t        )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t        ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 4 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 5 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t  ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 6 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t   (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t       (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");
		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 7 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL6 ");
		localStringBuffer.append("\t\t\t INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t UNION ALL ");

		localStringBuffer
				.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 8 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("             FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");
		localStringBuffer
				.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 9 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append(" \t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");
		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 10 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("        (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL9 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("      UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 11 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL9 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL10 ");
		localStringBuffer.append("\t\t\t INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 12 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL9 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL10 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL11 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 13 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA  ");
		localStringBuffer.append("\t\t\t )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL9 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL10 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("       )NIVEL11 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  )NIVEL12 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t  SELECT  A.CEDULA,A.CEDULA_PAPA, 14 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA  ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P  ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA  ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t      )NIVEL1 INNER JOIN T_AFILIADOS A  ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A  ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL9 ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL10 ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL11 ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("       ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  )NIVEL12 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL13 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  UNION ALL ");

		localStringBuffer
				.append("\t\t\t SELECT  A.CEDULA,A.CEDULA_PAPA, 15 NIVEL,A.CEDULADISTRIBUIDOR,A.TELEFONO,A.EMAIL,A.DIRECCION,A.CELULAR  FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT  A.CEDULA CEDULA_PAPA FROM ");
		localStringBuffer.append("\t\t\t (SELECT P.CEDULA CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
		localStringBuffer.append("\t\t\t \t     ON H.CEDULA=P.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t        AND H.CEDULA='" + paramString + "' ");
		localStringBuffer.append("\t\t\t       )NIVEL1 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t \t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t   ) NIVEL2 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t   ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  ) NIVEL4 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL5 INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL6 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL7 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL8 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL9 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL10 ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("       ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t )NIVEL11 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t )NIVEL12 ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
		localStringBuffer.append("\t\t\t  )NIVEL13 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

		localStringBuffer.append("\t\t\t  )NIVEL14 ");
		localStringBuffer.append("\t\t\t  INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("\t\t\t  ON NIVEL14.CEDULA_PAPA=A.CEDULA_PAPA)AFI ");
		localStringBuffer.append("       INNER JOIN T_AFILIADOS A ");
		localStringBuffer.append("       ON A.CEDULA =AFI.CEDULA ");
		

		Query localQuery = entityManager.createNativeQuery(localStringBuffer.toString());
		TreeNodeImpl localTreeNodeImpl1 = null;
		Object localObject1 = "";
		HashMap localHashMap1 = new HashMap();
		HashMap localHashMap2 = new HashMap();
		HashMap localHashMap3 = new HashMap();
		HashMap localHashMap4 = new HashMap();
		HashMap localHashMap5 = new HashMap();
		HashMap localHashMap6 = new HashMap();
		HashMap localHashMap7 = new HashMap();
		HashMap localHashMap8 = new HashMap();
		HashMap localHashMap9 = new HashMap();
		HashMap localHashMap10 = new HashMap();
		HashMap localHashMap11 = new HashMap();
		HashMap localHashMap12 = new HashMap();
		HashMap localHashMap13 = new HashMap();
		HashMap localHashMap14 = new HashMap();
		HashMap localHashMap15 = new HashMap();

		List localList = localQuery.getResultList();
		int i = localList.size();
		if (i > 0) {
			double d = 0.0D;
			for (int j = 0; j < i; j++) {
				Object localObject2 = localList.get(j);
				Object[] arrayOfObject = (Object[]) localObject2;

				String str1 = (String) arrayOfObject[0];
				int k = ((BigInteger) arrayOfObject[1]).intValue();
				String str2 = (String) arrayOfObject[2];
				String str3 = (String) arrayOfObject[3];
				String str4 = ((String) arrayOfObject[4] == null)
						|| ("".equals(((String) arrayOfObject[4]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[4];
				String str5 = ((String) arrayOfObject[5] == null)
						|| ("".equals(((String) arrayOfObject[5]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[5];
				String str6 = ((String) arrayOfObject[6] == null)
						|| ("".equals(((String) arrayOfObject[6]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[6];
				String str7 = ((String) arrayOfObject[7] == null)
						|| ("".equals(((String) arrayOfObject[7]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[7];
				String str8 = (String) arrayOfObject[8];

				String str9 = ((String) arrayOfObject[9] == null)
						|| ("".equals(((String) arrayOfObject[4]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[9];
				String str10 = ((String) arrayOfObject[10] == null)
						|| ("".equals(((String) arrayOfObject[10]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[10];
				String str11 = ((String) arrayOfObject[11] == null)
						|| ("".equals(((String) arrayOfObject[11]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[11];
				String str12 = ((String) arrayOfObject[12] == null)
						|| ("".equals(((String) arrayOfObject[12]).trim())) ? "NO DISPONIBLE"
						: (String) arrayOfObject[12];

				String str13 = k + "," + str2 + "," + str1;
				String str14 = k - 1 + "," + str3 + "," + str8;
				TreeNodeImpl localTreeNodeImpl2 = null;
				TreeNode localTreeNode = null;
				switch (k) {
				case 0:
					localObject1 = str1;
					localTreeNodeImpl1 = new TreeNodeImpl(str13, null);
					break;
				case 1:
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNodeImpl1);
					localHashMap1.put(str13, localTreeNodeImpl2);
					break;
				case 2:
					localTreeNode = buscarPadre(localHashMap1, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap2.put(str13, localTreeNodeImpl2);
					break;
				case 3:
					localTreeNode = buscarPadre(localHashMap2, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap3.put(str13, localTreeNodeImpl2);
					break;
				case 4:
					localTreeNode = buscarPadre(localHashMap3, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap4.put(str13, localTreeNodeImpl2);
					break;
				case 5:
					localTreeNode = buscarPadre(localHashMap4, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap5.put(str13, localTreeNodeImpl2);
					break;
				case 6:
					localTreeNode = buscarPadre(localHashMap5, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap6.put(str13, localTreeNodeImpl2);
					break;
				case 7:
					localTreeNode = buscarPadre(localHashMap6, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap7.put(str13, localTreeNodeImpl2);
					break;
				case 8:
					localTreeNode = buscarPadre(localHashMap7, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap8.put(str13, localTreeNodeImpl2);
					break;
				case 9:
					localTreeNode = buscarPadre(localHashMap8, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap9.put(str13, localTreeNodeImpl2);
					break;
				case 10:
					localTreeNode = buscarPadre(localHashMap9, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap10.put(str13, localTreeNodeImpl2);
					break;
				case 11:
					localTreeNode = buscarPadre(localHashMap10, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap11.put(str13, localTreeNodeImpl2);
					break;
				case 12:
					localTreeNode = buscarPadre(localHashMap11, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap12.put(str13, localTreeNodeImpl2);
					break;
				case 13:
					localTreeNode = buscarPadre(localHashMap12, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap13.put(str13, localTreeNodeImpl2);
					break;
				case 14:
					localTreeNode = buscarPadre(localHashMap13, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap14.put(str13, localTreeNodeImpl2);
					break;
				case 15:
					localTreeNode = buscarPadre(localHashMap14, str14);
					localTreeNodeImpl2 = new TreeNodeImpl(str13, localTreeNode);
					localHashMap15.put(str13, localTreeNodeImpl2);
				}
			}
		}
		DatosArbol localDatosArbol = new DatosArbol(i, localTreeNodeImpl1, (String) localObject1);
		return localDatosArbol;
	}
	
	  private TreeNode buscarPadre(Map<String, TreeNode> paramMap, String paramString)
	  {
	    TreeNode localTreeNode = null;
	    if (paramMap.containsKey(paramString)) {
	      localTreeNode = (TreeNode)paramMap.get(paramString);
	    }
	    return localTreeNode;
	  }	
}
