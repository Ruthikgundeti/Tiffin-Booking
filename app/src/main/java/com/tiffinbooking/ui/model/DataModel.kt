package com.tiffinbooking.ui.model

data class DataModel(
    var image: Int = 0,
    var name: String = "",
    var detail: String = "",
    var priceStandard: Int = 0,
    var priceMini: Int = 0,
    var type: String = "",
) {
    fun getStandardPrice() : String {
        return "Price per meal (Standard): $priceStandard Gbp"
    }
    fun getMiniPrice() : String {
        return "Price per meal (Mini): $priceMini Gbp"
    }
}