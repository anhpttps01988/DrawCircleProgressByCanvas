package net.dirox.coreproject.data.source.response

class CountryResponse(var code: String, var name: String, var currency: Currency, var countryCode: String) {

    inner class Currency(var code: String, var name: String, var symbole: String)

}