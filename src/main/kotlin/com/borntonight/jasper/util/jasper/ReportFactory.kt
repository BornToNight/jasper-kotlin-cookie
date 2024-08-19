package com.borntonight.jasper.util.jasper

import com.borntonight.jasper.util.enums.Constants
import com.borntonight.jasper.util.enums.Constants.classLoader
import com.borntonight.jasper.util.enums.JasperFileNamesEnum
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JsonDataSource
import net.sf.jasperreports.engine.util.JRLoader
import java.io.ByteArrayInputStream
import java.io.IOException

class ReportFactory {

    @Throws(JRException::class, IOException::class)
    fun createJasper(
        jasperFileNamesEnum: JasperFileNamesEnum,
        bytes: ByteArray,
    ): JasperPrint? {
        // Создаём наш отчёт на основе главного файла
        val jasperReport = JRLoader.loadObject(classLoader.getResourceAsStream(jasperFileNamesEnum.filePath)) as JasperReport
        // Передаём в отчёт наш джаспер отчёт, подотчёты в виде мапы и наш data class
        return JasperFillManager.fillReport(
            jasperReport,
            addSubJasperParameter(jasperFileNamesEnum),
            if (bytes.isEmpty()) JREmptyDataSource() else JsonDataSource(ByteArrayInputStream(bytes))
        )
    }

    private fun addSubJasperParameter(jasperFileNamesEnum: JasperFileNamesEnum): MutableMap<String, Any> {
        val parameters: MutableMap<String, Any> = HashMap()
        // Создание мапы подотчётов (название и сам подотчёт)
        for (i in jasperFileNamesEnum.subPaths.indices) {
            try {
                parameters[if (i > 0) Constants.PARAMETER_SUB_REPORT + (i + 1) else Constants.PARAMETER_SUB_REPORT] =
                    ReportFactory().loadReport(jasperFileNamesEnum.subPaths[i])
            } catch (e: JRException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return parameters
    }

    @Throws(JRException::class, IOException::class)
    private fun loadReport(jasperPath: String): JasperReport {
        // загрузка шаблона
        return JRLoader
            .loadObject(classLoader.getResourceAsStream(jasperPath)) as JasperReport
    }
}