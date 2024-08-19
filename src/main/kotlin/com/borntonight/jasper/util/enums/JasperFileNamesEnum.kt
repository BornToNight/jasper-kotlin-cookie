package com.borntonight.jasper.util.enums

enum class JasperFileNamesEnum(val filePath: String, val subPaths: List<String>) {
    COOKIE(
        "template/cookie.jasper",
        listOf("template/annexCookie.jasper", "template/annexCookieSub.jasper"),
    ),
}