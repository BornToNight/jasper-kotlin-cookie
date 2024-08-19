package com.borntonight.jasper.dto.cookieJasper

data class CookieJasperDto(
    var documentNumber: String = "",
    var applicantFullName: String = "",
    var cookie: String = "",
    var applicantPosAndShortName: String = "",
    var applicantSignDate: String = "",
    var approverPosAndShortName: String = "",
    var approverSignDate: String = "",
    var annexCookieJasperDto: AnnexCookieJasperDto = AnnexCookieJasperDto(),
)
