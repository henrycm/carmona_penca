package co.com.multinivel.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class GenerarReporte {
	public static void exportarPDF(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper,
			HashMap<Object, Object> map, List<Object> lista) {
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, map, ds);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment;filename=" + nombreReporte);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportarHTMLToBD(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper) {
		try {
			Connection conexion = ConexionReporte.getConexion();
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

			PrintWriter out = response.getWriter();
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportarExceltoBD(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper) {
		try {
			Connection conexion = ConexionReporte.getConexion();
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

			response.setContentType("application/xls");
			response.addHeader("Content-Disposition", "attachment;filename=" + nombreReporte);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			JRExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportarPDFtoBD(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper) {
		try {
			Connection conexion = ConexionReporte.getConexion();
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment;filename=" + nombreReporte);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportarExcel(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper,
			HashMap<Object, Object> map, List<Object> lista) {
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, map, ds);
			response.setContentType("application/xls");
			response.addHeader("Content-Disposition", "attachment;filename=" + nombreReporte);

			ServletOutputStream servletOutputStream = response.getOutputStream();
			JRExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void exportarHTML(HttpServletRequest request, HttpServletResponse response,
			ServletContext contexto, String nombreReporte, String nombreRerpoteJasper,
			HashMap<Object, Object> map, List<Object> lista) {
		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObject(contexto
					.getInitParameter("rutaReportes") + nombreRerpoteJasper);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, map, ds);

			PrintWriter out = response.getWriter();
			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}