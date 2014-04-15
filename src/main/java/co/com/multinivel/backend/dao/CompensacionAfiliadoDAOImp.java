package co.com.multinivel.backend.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.multinivel.backend.model.Parametro;
import co.com.multinivel.shared.dto.AfiliadoConsumo;
import co.com.multinivel.shared.dto.CompensacionAfiliadoDTO;
import co.com.multinivel.shared.exception.MultinivelDAOException;
import co.com.multinivel.shared.util.ParametrosEnum;

@Repository
@Transactional
public class CompensacionAfiliadoDAOImp implements CompensacionAfiliadoDAO {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ParametroDAO parametroDAO;

	public List<Object> consultar(String cedula, String periodo) throws MultinivelDAOException {
		List<Object> lista = null;
		try {
			lista = new ArrayList<Object>();
			Parametro pconsumoMinimo = this.parametroDAO.obtenerValor("CONSUMO_MINIMO_ABRIR_RED");
			double consumoMinimo = Double.parseDouble(pconsumoMinimo.getValor());

			StringBuffer sql = new StringBuffer();
			sql.append("   SELECT H.NOMBRE_AFILIADO, H.AFILIADO,H.NIVEL, H.COMISION,H.DISTRIBUIDOR,H.CONSUMO, H.AFILIADOCOMISION ");
			sql.append("  FROM ( ");

			sql.append(" SELECT A.NOMBRE+' '+A.APELLIDO NOMBRE_AFILIADO, CALC.AFILIADO,CALC.NIVEL, CALC.COMISION,CALC.DISTRIBUIDOR,CALC.CONSUMO,AFILIADOCOMISION ");
			sql.append(" FROM ");
			sql.append("(SELECT '" + cedula + "' AFILIADOCOMISION,AFILIADO, DISTRIBUIDOR,NIVEL,CONSUMO, ");
			sql.append("\tROUND(CASE WHEN NIVEL=1 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL1'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN  ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL1'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t\tWHEN NIVEL=2 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL2'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL2'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t\tWHEN NIVEL=3 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL3'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL3'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t\tWHEN NIVEL=4 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL4'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL4'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t\tWHEN NIVEL=5 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL5'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_NIVEL5'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t\tWHEN NIVEL>5 THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_OTROS'))/100) - ");
			sql.append("\t  CASE WHEN CONSUMO>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='VALOR_RETEFUENTE') THEN ");
			sql.append("\t\t(((CONSUMO/(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='IVA'))* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='PORCENTAJE_OTROS'))/100)* ");
			sql.append("\t\t(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='RETEFUENTE')/100 ");
			sql.append("\t  ELSE 0 END ");
			sql.append("\t  END,2)COMISION ");
			sql.append("  FROM ");
			sql.append(" (SELECT  CEDULA_PAPA PATROCINADOR,AFI.CEDULA AFILIADO, CEDULADISTRIBUIDOR DISTRIBUIDOR,NIVEL,VALOR, VALOR CONSUMO ");
			sql.append(" FROM ");
			sql.append(" (SELECT P.CEDULA,P.CEDULA_PAPA, 1 NIVEL, H.CEDULADISTRIBUIDOR ");
			sql.append("\tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\tON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("  AND H.CEDULA='" + cedula + "' ");
			sql.append("  UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 2 NIVEL,A.CEDULADISTRIBUIDOR FROM ");
			sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("\tFROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\tON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("  AND H.CEDULA='" + cedula + "')NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\tON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 3 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("       )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("       ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 4 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 5 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append(" FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append(" ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "'  ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append("INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 6 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("  (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("      (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("       FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 7 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append("ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL6 ");
			sql.append("INNER JOIN T_AFILIADOS A ");
			sql.append("ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append("UNION ALL ");

			sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 8 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A  ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");
			sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 9 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(") NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");
			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 10 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");

			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 11 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL10 ");
			sql.append("INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");

			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 12 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL10 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL11 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");

			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 13 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(") NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL10 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL11 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" )NIVEL12 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");

			sql.append(" SELECT  A.CEDULA,A.CEDULA_PAPA, 14 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append(" (SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("     )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL10 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append("ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL11 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" )NIVEL12 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL13 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" UNION ALL ");

			sql.append("SELECT  A.CEDULA,A.CEDULA_PAPA, 15 NIVEL,A.CEDULADISTRIBUIDOR  FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT  A.CEDULA CEDULA_PAPA FROM ");
			sql.append("(SELECT P.CEDULA CEDULA_PAPA ");
			sql.append("      FROM T_AFILIADOS H INNER JOIN T_AFILIADOS P ");
			sql.append("\t     ON H.CEDULA=P.CEDULA_PAPA ");
			sql.append("       AND H.CEDULA='" + cedula + "' ");
			sql.append("      )NIVEL1 INNER JOIN T_AFILIADOS A ");
			sql.append("\t      ON NIVEL1.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append("  ) NIVEL2 INNER JOIN T_AFILIADOS A ");
			sql.append("  ON NIVEL2.CEDULA_PAPA=A.CEDULA_PAPA) NIVEL3 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL3.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" ) NIVEL4 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL4.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL5 INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL5.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL6 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL6.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL7 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL7.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL8 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL8.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL9 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL9.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL10 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL10.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(")NIVEL11 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL11.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(")NIVEL12 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append("ON NIVEL12.CEDULA_PAPA=A.CEDULA_PAPA ");
			sql.append(" )NIVEL13 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL13.CEDULA_PAPA=A.CEDULA_PAPA ");

			sql.append(" )NIVEL14 ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON NIVEL14.CEDULA_PAPA=A.CEDULA_PAPA)AFI ");
			sql.append(" INNER JOIN ( ");
			sql.append("          SELECT P.CEDULA CEDULA, SUM(C.TOTALPEDIDO)VALOR FROM T_AFILIADOS P INNER JOIN T_CONSUMOS C ");
			sql.append("\t        ON P.CEDULA=C.AFILIADO ");
			sql.append("          WHERE P.ACTIVO='SI' AND DATE_FORMAT(C.FECHA,'%m/%Y')='" + periodo + "' ");
			sql.append("\t        GROUP BY C.AFILIADO ");

			sql.append(" )ABRE_RED ");
			sql.append(" ON AFI.CEDULA=ABRE_RED.CEDULA ");
			sql.append(" ORDER BY NIVEL ASC ");
			sql.append(" )CONSUMXNIVEL ) CALC ");
			sql.append(" INNER JOIN T_AFILIADOS A ");
			sql.append(" ON A.CEDULA = CALC.AFILIADO ");
			sql.append(" )H INNER JOIN (SELECT '" + cedula + "' CEDULA, SUM(C.TOTALPEDIDO)VALOR FROM T_AFILIADOS P INNER JOIN T_CONSUMOS C ");
			sql.append("       ON P.CEDULA=C.AFILIADO ");
			sql.append("       WHERE P.ACTIVO='SI' AND DATE_FORMAT(C.FECHA,'%m/%Y')='" + periodo + "' AND C.AFILIADO='" + cedula + "' ");
			sql.append("      GROUP BY C.AFILIADO ");
			sql.append("       HAVING SUM(C.TOTALPEDIDO)>=(SELECT VALOR FROM T_PARAMETROS WHERE NOMBRE_PARAMETRO='CONSUMO_MINIMO')) A ");
			sql.append("        ON H.AFILIADOCOMISION =A.CEDULA ");

			System.err.println("***");
			System.err.println(sql.toString());
			System.err.println("**");
			Query q = this.entityManager.createNativeQuery(sql.toString());
			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				double total = 0.0D;
				for (int i = 0; i < s; i++) {
					Object obj = result.get(i);
					Object[] objectArray = (Object[]) obj;

					String nombre = (String) objectArray[0];
					String cedulaAfiliado = (String) objectArray[1];
					int nivel = ((BigInteger) objectArray[2]).intValue();
					double comision = ((Double) objectArray[3]).doubleValue();
					double consumoTotal = ((BigDecimal) objectArray[5]).doubleValue();
					total += comision;

					AfiliadoConsumo afiliadoConsumo = new AfiliadoConsumo();
					afiliadoConsumo.setNombre(nombre);
					afiliadoConsumo.setAfiliado(cedulaAfiliado);
					afiliadoConsumo.setNivel(nivel);
					afiliadoConsumo.setComision(comision);
					afiliadoConsumo.setConsumoTotal(consumoTotal);
					afiliadoConsumo.setTotal(total);

					double consumoProducto = 0.0D;
					double consumoDinero = 0.0D;
					if (total > 0.0D) {
						consumoProducto = total - consumoMinimo;
						if (consumoProducto < 0.0D) {
							consumoProducto = total;
						} else {
							consumoDinero = consumoProducto;
							consumoProducto = consumoMinimo;
						}
					}
					afiliadoConsumo.setComisionDinero(consumoDinero);
					afiliadoConsumo.setComisionProducto(consumoProducto);
					lista.add(afiliadoConsumo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al listar los afiliados por nivel", getClass());
		}
		return lista;
	}

	public List<Object> consultar(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		List<Object> listaCompensacion = new ArrayList<Object>();
		try {
			String sql = "SELECT afiliado,nivel,consumo,comision,periodo FROM t_compensacion_afiliado_periodo t where periodo=? and afiliado=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				CompensacionAfiliadoDTO compensacionAfiliadoDTOTemp = new CompensacionAfiliadoDTO();
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedula = (String) objectArray[0];
				int nivel = ((Integer) objectArray[1]).intValue();

				double consumo = ((BigDecimal) objectArray[2]).doubleValue();
				double comision = ((BigDecimal) objectArray[3]).doubleValue();
				String periodo = (String) objectArray[4];
				compensacionAfiliadoDTOTemp.setAfiliado(cedula);
				compensacionAfiliadoDTOTemp.setPeriodo(periodo);
				compensacionAfiliadoDTOTemp.setConsumo(consumo);
				compensacionAfiliadoDTOTemp.setComisionTotal(comision);
				compensacionAfiliadoDTOTemp.setNivel(nivel);

				listaCompensacion.add(compensacionAfiliadoDTOTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return listaCompensacion;
	}

	public double totalConsumo(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		double comision = 0.0D;
		try {
			String sql = "SELECT sum(comision) FROM t_compensacion_afiliado_periodo t where periodo=? and afiliado=?";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			if (s > 0) {
				BigDecimal total = (BigDecimal) result.get(0);
				comision = total.doubleValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return comision;
	}

	public List<Object> listarPagoAfiliados(CompensacionAfiliadoDTO compensacionAfiliadoDTO) throws MultinivelDAOException {
		List<Object> listaCompensacion = new ArrayList<Object>();
		try {
			String sql = " SELECT  C.AFILIADO, CONCAT (A.NOMBRE ,' ',IF(A.APELLIDO IS NULL,'',A.APELLIDO)), SUM(C.COMISION), C.PERIODO   FROM T_COMPENSACION_AFILIADO_PERIODO C INNER JOIN T_AFILIADOS A  ON C.AFILIADO=A.CEDULA LEFT JOIN T_AFILIADOS D  ON D.CEDULA=A.CEDULADISTRIBUIDORPAGO  WHERE C.PERIODO= ?  AND A.CEDULADISTRIBUIDORPAGO= ? AND C.COMISION >0  GROUP BY A.CEDULADISTRIBUIDORPAGO,D.NOMBRE,C.AFILIADO, A.NOMBRE,C.PERIODO";

			Query q = this.entityManager.createNativeQuery(sql);
			q.setParameter(1, compensacionAfiliadoDTO.getPeriodo());
			q.setParameter(2, compensacionAfiliadoDTO.getAfiliado());

			List<?> result = q.getResultList();
			int s = result.size();
			for (int i = 0; i < s; i++) {
				CompensacionAfiliadoDTO compensacionAfiliadoDTOTemp = new CompensacionAfiliadoDTO();
				Object obj = result.get(i);
				Object[] objectArray = (Object[]) obj;
				String cedulaAfiliado = (String) objectArray[0];
				String nombreAfiliado = (String) objectArray[1];
				double comision = ((BigDecimal) objectArray[2]).doubleValue();
				String periodo = (String) objectArray[3];

				compensacionAfiliadoDTOTemp.setAfiliado(cedulaAfiliado);
				compensacionAfiliadoDTOTemp.setNombreAfiliado(nombreAfiliado);
				compensacionAfiliadoDTOTemp.setPeriodo(periodo);

				double consumoMinimo = 0.0D;
				double consumoProducto = 0.0D;
				double consumoDinero = 0.0D;
				if (comision > 0.0D) {
					Parametro pconsumoMinimo = this.parametroDAO.obtenerValor("CONSUMO_MINIMO_ABRIR_RED");
					consumoMinimo = Double.parseDouble(pconsumoMinimo.getValor());
					consumoProducto = comision - consumoMinimo;
					if (consumoProducto < 0.0D) {
						consumoProducto = comision;
					} else {
						consumoDinero = consumoProducto;
						consumoProducto = consumoMinimo;
					}
				}
				compensacionAfiliadoDTOTemp.setComisionProducto(consumoProducto);
				compensacionAfiliadoDTOTemp.setComisionDinero(consumoDinero);
				compensacionAfiliadoDTOTemp.setComisionTotal(comision);
				listaCompensacion.add(compensacionAfiliadoDTOTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return listaCompensacion;
	}

	public int liquidar(String distribuidor, String periodo) throws MultinivelDAOException {
		int param1 = 0;
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command1 = "{call Sp_Liquidar(?,?)}";
			CallableStatement cstmt1 = conexion.prepareCall(command1);
			cstmt1.setString(1, distribuidor);
			cstmt1.setString(2, periodo);
			cstmt1.execute();

			cstmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("error al realizar la busqueda", getClass());
		}
		return param1;
	}

	public void calcularArbol(String cedula, String tipoUsuario) throws MultinivelDAOException {
		try {
			Class.forName(ParametrosEnum.DRIVER_DATABASE.getValor());
			Connection conexion = DriverManager.getConnection(ParametrosEnum.URL_DATABASE.getValor(), ParametrosEnum.USUARIO.getValor(),
					ParametrosEnum.PASSWORD.getValor());

			String command = "{call Sp_Arbol(?,?)}";
			CallableStatement cstmt = conexion.prepareCall(command);
			cstmt.setString(1, cedula);
			cstmt.setString(2, tipoUsuario);
			cstmt.execute();

			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new MultinivelDAOException("Error al calcular el arbol", getClass());
		}
	}
}

/*
 * Location: D:\Dllo\multinivel\multinivelEAR.ear\multinivelEJB.jar\
 * 
 * Qualified Name: co.com.multinivel.backend.dao.CompensacionAfiliadoDAOImp
 */