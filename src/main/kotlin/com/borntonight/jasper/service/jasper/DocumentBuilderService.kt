package com.borntonight.jasper.service.jasper

import com.borntonight.jasper.dto.cookieJasper.CookieJasperDto
import com.borntonight.jasper.util.enums.JasperFileNamesEnum
import com.borntonight.jasper.util.jasper.ReportFactory
import com.borntonight.jasper.util.jasper.toPDF
import com.borntonight.jasper.util.jasper.toXLSX
import com.fasterxml.jackson.databind.ObjectMapper
import net.sf.jasperreports.engine.JasperPrint
import org.springframework.stereotype.Service
import java.io.File

@Service
class DocumentBuilderService {

    private val objectMapper = ObjectMapper()

    fun buildDocument(
        paths: JasperFileNamesEnum,
        fileName: String,
        documentJasperDTO: CookieJasperDto = CookieJasperDto(),
    ) {
        val jasperPrint =
            ReportFactory().createJasper(
                paths,
                objectMapper.writeValueAsString(documentJasperDTO).toByteArray(),
            )!!
        startDocument(fileName, jasperPrint)
    }

    private fun startDocument(fileName: String, jasperPrint: JasperPrint) {
        val directory = File("C:\\JasperReportTests") // WINDOWS
        if (!directory.exists()) directory.mkdir() // WINDOWS
        File("$directory\\${fileName}.xlsx").writeBytes(toXLSX(jasperPrint)) // WINDOWS
        File("$directory\\${fileName}.pdf").writeBytes(toPDF(jasperPrint)) // WINDOWS
    }
}