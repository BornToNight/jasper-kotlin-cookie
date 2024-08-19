package com.borntonight.jasper.util.jasper

import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperPrint
import net.sf.jasperreports.engine.export.JRPdfExporter
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput
import java.io.ByteArrayOutputStream

@Throws(JRException::class)
fun toPDF(jasperPrint: JasperPrint?): ByteArray {
    val output = ByteArrayOutputStream()
    val pdf = JRPdfExporter()
    pdf.setExporterInput(SimpleExporterInput.getInstance(listOf(jasperPrint)))
    pdf.exporterOutput = SimpleOutputStreamExporterOutput(output)
    pdf.exportReport()
    return output.toByteArray()
}

@Throws(JRException::class)
fun toXLSX(jasperPrint: JasperPrint?): ByteArray {
    val output = ByteArrayOutputStream()
    val xlsx = JRXlsxExporter()
    xlsx.setExporterInput(SimpleExporterInput.getInstance(listOf(jasperPrint)))
    xlsx.exporterOutput = SimpleOutputStreamExporterOutput(output)
    xlsx.exportReport()
    return output.toByteArray()
}