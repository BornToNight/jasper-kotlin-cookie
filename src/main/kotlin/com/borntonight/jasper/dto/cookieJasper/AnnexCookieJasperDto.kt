package com.borntonight.jasper.dto.cookieJasper

data class AnnexCookieJasperDto(
    var documentNumber: String = "",
    var annexRowList: List<AnnexCookieJasperSubDto> = emptyList()
)
