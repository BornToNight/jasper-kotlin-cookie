package com.borntonight.jasper

import com.borntonight.jasper.dto.cookieJasper.AnnexCookieJasperDto
import com.borntonight.jasper.dto.cookieJasper.AnnexCookieJasperSubDto
import com.borntonight.jasper.dto.cookieJasper.CookieJasperDto
import com.borntonight.jasper.service.jasper.DocumentBuilderService
import com.borntonight.jasper.util.enums.JasperFileNamesEnum.COOKIE
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JasperApplicationTests {

    private val documentBuilderService = DocumentBuilderService()

    @Test
    fun makeCookieDocument1() {
        documentBuilderService.buildDocument(
            COOKIE,
            "cookie",
            CookieJasperDto(
                "42",
                "Исаковский Вадим Дмитриевич",
                "Любятово - 3шт., Яшкино - 2 шт., Посиделкино - 2 шт.",
                "Ведущий разработчик, Исаковский В.Д.",
                "01.01.2024",
                "Главный по печенькам, Яшкин Л.П.",
                "02.01.2024",
            )
        )
    }

    @Test
    fun makeCookieDocument2() {
        documentBuilderService.buildDocument(
            COOKIE,
            "cookie2",
            CookieJasperDto(
                "42",
                "Исаковский Вадим Дмитриевич",
                "см. в приложении",
                "Ведущий разработочик, Исаковский В.Д.",
                "01.01.2024",
                "Главный по печенькам, Яшкин Л.П.",
                "02.01.2024",
                AnnexCookieJasperDto(
                    "42",
                    listOf(
                        AnnexCookieJasperSubDto("Любятово", "3"),
                        AnnexCookieJasperSubDto("Яшкино", "2"),
                        AnnexCookieJasperSubDto("Посиделкино", "2"),
                        AnnexCookieJasperSubDto("Юбилейное", "1"),
                    )
                )
            )
        )
    }
}
